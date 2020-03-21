package domain;

import java.util.*;

public class Scheduler {


    private int numCourse;
    private ArrayList<Course> sortedCourseList;
    private ArrayList<Bucket> buckets;

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

        Bucket winnerBucket = buckets.get(index);
        System.out.println("WINNER IS: bucket #" + (index+1) + "/" + buckets.size() + "\n\n");
        System.out.println(winnerBucket+"\n\n");

        System.out.println("OTHER BUCKETS are as follows: \n\n");

        for(Bucket b:buckets){
            int currentIndex = buckets.indexOf(b);
            if(currentIndex != index)
                System.out.println("Bucket #" + (currentIndex+1) + "\n\n" + b + "\n");
        }

        //System.out.println(winnerBucket);



    }


    //gets a list of courses from user and returns a valid course plan
    public ValidCoursePlan PlanCourses(List<Course> courses){

        ValidCoursePlan coursePlan= new ValidCoursePlan();
        return  coursePlan;
    }
}
