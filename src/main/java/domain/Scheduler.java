package domain;

import javax.lang.model.type.NullType;
import java.util.*;

public class Scheduler {


    private int numBundles;
    private ArrayList<ClassBundle> bundleList;
    private TreeMap<Integer, Integer> sortedCumPriorityMap;
    private String output;
    private int numPlans;

    public Scheduler(ArrayList<ClassBundle> bundleList){
        this.numBundles = bundleList.size();
        this.bundleList = bundleList;

        for(int i=0; i<numBundles;i++) {
            generateOptimumPlanForGivenDay(bundleList, i);
        }
    }

    private void generateOptimumPlanForGivenDay(ArrayList<ClassBundle> bList, int day) {
        // Initializing:
        ArrayList<domain.Class> classList = new ArrayList<>();
        for(int i=0; i<numBundles;i++) {
            classList.add(null);
            ClassBundle curBundle = bList.get(i);
            Class curClass = curBundle.getRegularClass(day); //TODO
            classList.set(i, curClass);
        }
        sortClassList(classList);

        ArrayList<Bucket> buckets = new ArrayList<>();
        for(int i=0;i<numBundles;i++){
            buckets.add(null);
            Class curClass = classList.get(i);
            ArrayList<Class> cList = new ArrayList<>();
            cList.add(curClass);
            Plan p = new Plan(cList);
            ArrayList<Plan> pList = new ArrayList<>();
            pList.add(p);
            Bucket b = new Bucket(curClass.getCourse().getPriority(), pList);
            buckets.set(i, b);
        }
        // Applying weighted job scheduling wrt "day":
        for(int i=1;i<numBundles;i++){
            for(int j=0;j<i;j++){
                Bucket b1 = buckets.get(j);
                Bucket b2 = buckets.get(i);

                Class c1 = classList.get(j);
                Class c2 = classList.get(i);
                // Find bundle given class:
                ClassBundle cb1 = new ClassBundle(); //TODO: ClassBundle needs a default constructor
                for(int k=0;k<numBundles;k++) {
                    ClassBundle curBundle = bundleList.get(k);
                    if (bundleList.get(k).getRegularClass().equals(c1))
                        cb1 = curBundle;
                }
                ClassBundle cb2 = new ClassBundle();
                for(int k=0;k<numBundles;k++) {
                    ClassBundle curBundle = bundleList.get(k);
                    if (bundleList.get(k).getRegularClass().equals(c2))
                        cb2 = curBundle;
                }


                if(!cb1.isOverlappingWith(cb2)){
                    int cumBucketPriority = b1.getCumulativeBucketPriority();
                    int prioritySum = cumBucketPriority + c2.getCourse.getPriority();
                    ArrayList<Plan> newPlanList = new ArrayList<>(b1.getPlans().size());
                    for(Plan p:b1.getPlans()){
                        Plan newPlan = new Plan(new ArrayList<Class>(p.getClassList()));
                        newPlan.addClass(c2);
                        newPlanList.add(newPlan);
                    }
                    Bucket newBucket = new Bucket(prioritySum, newPlanList);
                    if(prioritySum > b2.getCumulativeBucketPriority()){
                        buckets.set(i, newBucket);
                    }else if(prioritySum == b2.getCumulativeBucketPriority()){
                        b2.getPlans().addAll(newPlanList);
//                        ArrayList<Plan> mergedPlanList = b2.getPlans();
//                        Bucket mergedBucket = new Bucket(prioritySum, mergedPlanList);
//                        buckets.set(i, mergedBucket);
                    }
                }
            }
        }
        int index = 0;
        int max = buckets.get(index).getCumulativeBucketPriority();

        for(int i=1;i<buckets.size();i++){
            int curPriority = buckets.get(i).getCumulativeBucketPriority();
            if(curPriority>max){
                max = curPriority;
                index = i;
            }
        }

        output = "";

        Bucket winnerBucket = buckets.get(index);
        System.out.println("WINNER IS: bucket #" + (index+1) + "/" + buckets.size() + "\n\n");
        System.out.println(winnerBucket+"\n\n");

        System.out.println("OTHER BUCKETS are as follows: \n\n");

        output += "WINNER IS: bucket #" + (index+1) + "/" + buckets.size() + "\n\n\n";
        output += winnerBucket+"\n\n\n";
        numPlans += winnerBucket.getPlans().size();
        output += "OTHER BUCKETS are as follows: \n\n\n";

        Map<Integer, Integer> unSortedCumPriorityMap = new HashMap<Integer, Integer>();

        for(Bucket b:buckets){
            int currentIndex = buckets.indexOf(b);
            if(currentIndex != index){
                unSortedCumPriorityMap.put(currentIndex, b.getCumulativeBucketPriority());
            }
        }

        DescSortComparator descSortComparator = new DescSortComparator(unSortedCumPriorityMap);

        sortedCumPriorityMap = new TreeMap(descSortComparator);

        sortedCumPriorityMap.putAll(unSortedCumPriorityMap);

        for(Integer ind:sortedCumPriorityMap.keySet()){
            Bucket curMaxBucket = buckets.get(ind);
            System.out.println("Bucket #" + (ind+1) + "\n\n" + curMaxBucket + "\n");
            output += "Bucket #" + (ind+1) + "\n\n" + curMaxBucket + "\n\n";
            numPlans += curMaxBucket.getPlans().size();
        }

//        for(Bucket b:buckets){
//            int currentIndex = buckets.indexOf(b);
//            if(currentIndex != index){
//                System.out.println("Bucket #" + (currentIndex+1) + "\n\n" + b + "\n");
//                output += "Bucket #" + (currentIndex+1) + "\n\n" + b + "\n\n";
//            }
//            numPlans += b.getPlans().size();
//
//        }

        //System.out.println(winnerBucket);

    }
    private void sortClassList(ArrayList<Class> classList){
        Collections.sort(classList, new ClassComparator());
    }

    public String getOutput(){
        return output;
    }

    public int getNumPlans(){
        return numPlans;
    }

    //gets a list of courses from user and returns a valid course plan
    public ValidCoursePlan PlanCourses(List<Course> courses){

        ValidCoursePlan coursePlan= new ValidCoursePlan();
        return  coursePlan;
    }
}

class ClassComparator implements Comparator {
    public int compare(Object o1,Object o2){
        int s1 = Parser.ParseMtgTimeStr2IntegerTimeStamp((Class)o1.getEndTime());
        int s2 = Parser.ParseMtgTimeStr2IntegerTimeStamp((Class)o2.getEndTime());

        if(s1 == s2){
            return 0;
        }else if(s1<s2){
            return -1;
        }
        return 1;
    }
}
class DescSortComparator implements Comparator {

    Map map;

    public DescSortComparator(Map map) {
        this.map = map;
    }

    public int compare(Object o1, Object o2) {

        int int1 = (int) map.get(o2);
        int int2 = (int) map.get(o1);

        if(int1>=int2){
            return 1;
        }

        return -1;

    }
}