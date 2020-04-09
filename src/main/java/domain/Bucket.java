package domain;

import java.util.ArrayList;
public class Bucket {

    private int cumulativeBucketPriority;
    private ArrayList<Plan> plans;

    public void setPlans(ArrayList<Plan> plans) {
        this.plans = plans;
    }

    public ArrayList<Plan> getPlans() {
        return plans;
    }

    public int getCumulativeBucketPriority() {
        return cumulativeBucketPriority;
    }

    public void setCumulativeBucketPriority(int cumulativeBucketPriority) {
        this.cumulativeBucketPriority = cumulativeBucketPriority;
    }

    public Bucket(int cumulativeBucketPriority, ArrayList<Plan> plans){
        this.cumulativeBucketPriority = cumulativeBucketPriority;
        this.plans = plans;
    }

    public void addPlan(Plan p){
        plans.add(p);
    }

    public String toString() {
        int count = 0;
        String str = "";
        for(Plan p:plans){
            str += ++count + ". plan with priority sum " + this.getCumulativeBucketPriority() + " is as follows: \n\n" + p.toString() + "\n";
        }
        return str;
    }

}
