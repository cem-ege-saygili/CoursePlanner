package domain;

import java.util.ArrayList;

public class Plan {

    private ArrayList<Class> classList;

    public Plan(ArrayList<Class> classList){
        this.classList = classList;
    }

    public ArrayList<Class> getClassList() {
        return classList;
    }

    public void setClassList(ArrayList<Class> classList) {
        this.classList = classList;
    }

    public void addClass(Class newClass){
        classList.add(newClass);
    }

    @Override
    public String toString() {
        return "Plan{" +
                "classList=" + classList +
                '}';
    }
}
