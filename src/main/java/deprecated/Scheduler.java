package deprecated;

import model.Course;

import java.util.*;

public class Scheduler {


    private int numCourse;
    private ArrayList<Course> sortedCourseList;
    private ArrayList<Bucket> buckets;
    private TreeMap<Integer, Integer> sortedCumPriorityMap;
    private String output;
    private int numPlans;

    public Scheduler(ArrayList<Course> courseList){
        this.numCourse = courseList.size();
        this.sortedCourseList = sortCourseList(courseList);
        buckets = new ArrayList<Bucket>();
        for(int i=0; i<numCourse;i++)
            buckets.add(null);
        for(int i=0;i<numCourse;i++){
            Course curCourse = sortedCourseList.get(i);
            ArrayList<Course> cList = new ArrayList<Course>();
            cList.add(curCourse);
            Plan p = new Plan(cList);
            ArrayList<Plan> pList = new ArrayList<Plan>();
            pList.add(p);
            Bucket b = new Bucket(curCourse.getPriority(), pList);
            buckets.set(i, b);
        }
    }

    private ArrayList<Course> sortCourseList(ArrayList<Course> courseList){
        Collections.sort(courseList);
        return courseList;
    }

    public void generateOptimumCoursePlan(){
        for(int i=1;i<numCourse;i++){
            for(int j=0;j<i;j++){
                Bucket b1 = buckets.get(j);
                Bucket b2 = buckets.get(i);

                Course c1 = sortedCourseList.get(j);
                Course c2 = sortedCourseList.get(i);

                if(!c1.isOverlappingWith(c2)){
                    int cumBucketPriority = b1.getCumulativeBucketPriority();
                    int prioritySum = cumBucketPriority + c2.getPriority();
                    ArrayList<Plan> newPlanList = new ArrayList<Plan>(b1.getPlans().size());
                    for(Plan p:b1.getPlans()){
                        Plan newPlan = new Plan(new ArrayList<Course>(p.getCourseList()));
                        newPlan.addCourse(c2);
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