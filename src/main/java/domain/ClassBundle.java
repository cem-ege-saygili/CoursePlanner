package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//import static java.util.Collections.emptyList;
//import static java.util.Arrays.asList;
//import static java.util.Collections.emptyList;
//import static java.util.Optional.of;
//import static java.util.stream.Collectors.toList;

public class ClassBundle{

    private String courseSubject;
    private int courseCatalog;
    private static int bundleIdcounter;
    private int bundleId;
    private List<domain.Class> classList;

    public ClassBundle(

            String courseSubject,
            int courseCatalog,
            int bundleId){

        this.courseSubject = courseSubject;
        this.bundleId = bundleId;
        this.courseCatalog = courseCatalog;
        //this.classList = classList;
        this.classList = new ArrayList<>();

    }

    public void add(domain.Class c){
        for(domain.Class curClass:classList){
            if(!c.isCompatibleWith(curClass)){
                System.out.println("Sorry, class #" + c.getId() + " is not compatible with Bundle #" + bundleId
                + ", due to the already added Class #" + curClass.getId());
                return;
            }
        }
        classList.add(c);
        Collections.sort(classList);
    }

    public Class getLecClass(){
        for(Class c:classList){
            if(c.getComponent().substring(0,3).equals("LEC")){
                return c;
            }
        }
        return null;
    }

    public Class getPrbClass(){
        for(Class c:classList){
            if(c.getComponent().substring(0,3).equals("PRB")){
                return c;
            }
        }
        return null;
    }

    public Class getDisClass(){
        for(Class c:classList){
            if(c.getComponent().substring(0,3).equals("DIS")){
                return c;
            }
        }
        return null;
    }

    public Class getLabClass(){
        for(Class c:classList){
            if(c.getComponent().substring(0,3).equals("LAB")){
                return c;
            }
        }
        return null;
    }

    public void addAll(List<domain.Class> classes){
        for(Class c:classes)
            add(c);
    }

    public String getCourseSubject() {
        return courseSubject;
    }

    public void setCourseSubject(String courseSubject) {
        this.courseSubject = courseSubject;
    }

    public int getCourseCatalog() {
        return courseCatalog;
    }

    public void setCourseCatalog(int courseCatalog) {
        this.courseCatalog = courseCatalog;
    }

    public int getBundleId() {
        return bundleId;
    }

    public void setBundleId(int bundleId) {
        this.bundleId = bundleId;
    }

    public List<Class> getClassList() {
        return classList;
    }

    public void setClassList(List<Class> classList) {
        this.classList = classList;
    }

    public static List<ClassBundle> GenerateClassBundlesFromClasses(List<domain.Class> classes){

        List<ClassBundle> bundles = new ArrayList<>();
        List<domain.Class> prb_classes = new ArrayList<>();
        List<domain.Class> lec_classes = new ArrayList<>();
        List<domain.Class> dis_classes = new ArrayList<>();
        List<domain.Class> lab_classes = new ArrayList<>();

        boolean lecFlag = false;
        boolean prbFlag = false;
        boolean labFlag = false;

        for(domain.Class curClass:classes){
            String curClassComponent = curClass.getComponent();
            if(curClassComponent.substring(0,3).equals("PRB")){
                prb_classes.add(curClass);
            }else if(curClassComponent.substring(0,3).equals("LEC")){
                lec_classes.add(curClass);
            }else if(curClassComponent.substring(0,3).equals("LAB")){
                lab_classes.add(curClass);
            }else if(curClassComponent.substring(0,3).equals("DIS")){
                dis_classes.add(curClass);
            }else{
                return null;
            }
        }

        List<List<domain.Class>> lecPrbLabDisLists = new ArrayList<>();

        if(!lec_classes.isEmpty()){
            lecPrbLabDisLists.add(lec_classes);
        }
        if(!prb_classes.isEmpty()){
            lecPrbLabDisLists.add(prb_classes);
        }
        if(!lab_classes.isEmpty()){
            lecPrbLabDisLists.add(lab_classes);
        }
        if(!dis_classes.isEmpty()){
            lecPrbLabDisLists.add(dis_classes);
        }


        List<List<domain.Class>> cartesianProductsList = GenerateCartesianProducts(lecPrbLabDisLists);

        for(List<domain.Class> curCartesianProductList:cartesianProductsList) {

            String curClassSubject = curCartesianProductList.get(0).getCourseName();
            int curClassCatalog = curCartesianProductList.get(0).getCourseCatalog();

            if(Class.AreAllCompatible(curCartesianProductList)){

                ClassBundle curClassBundle = new ClassBundle(curClassSubject, curClassCatalog, bundleIdcounter++);
                curClassBundle.addAll(curCartesianProductList);
                bundles.add(curClassBundle);

            }
        }

        return bundles;

    }

    private static <T> List<List<T>> GenerateCartesianProducts(List<List<T>> lists) {
        List<List<T>> resultLists = new ArrayList<List<T>>();
        if (lists.size() == 0) {
            resultLists.add(new ArrayList<T>());
            return resultLists;
        } else {
            List<T> firstList = lists.get(0);
            List<List<T>> remainingLists = GenerateCartesianProducts(lists.subList(1, lists.size()));
            for (T condition : firstList) {
                for (List<T> remainingList : remainingLists) {
                    ArrayList<T> resultList = new ArrayList<T>();
                    resultList.add(condition);
                    resultList.addAll(remainingList);
                    resultLists.add(resultList);
                }
            }
        }
        return resultLists;
    }

    @Override
    public boolean equals(Object obj) {
        ClassBundle cb = (ClassBundle) obj;
        int id1 = bundleId;
        int id2 = cb.bundleId;

        if(id1 == id2)
            return true;

        return false;
    }

    @Override
    public String toString(){
        return  "\n\nClassBundle #" + bundleId +
                ",\n\"" + courseSubject +
                " " + courseCatalog +
                "\" with following classes: " +
                classList;
    }



    //private List<int[]> timeExtentList;

//    public ClassBundle(String Subject,
//            int Catalog,
//            List<int[]> timeExtentList){
//
//        this.Catalog = Catalog;
//        this.Subject = Subject;
//        this.timeExtentList = timeExtentList;
//
//    }
//
//    public boolean isCompatibleWith(ClassBundle b2){
//        // A bundle of a course is incompatible with a bundle with the same course.
//        if (b2.Subject.equals(this.Subject)) return false;
//        if (notOverlappingWith(b2)) return true;
//        return false;
//    }
//
//    private boolean notOverlappingWith(ClassBundle b2){
//        boolean notOverlapping = true;
//        for(int[] thisCurrTimeFrame:this.timeExtentList){
//            for(int[] b2CurrTimeFrame:b2.timeExtentList){
//                int thisCurrTimeFrameStart = thisCurrTimeFrame[0];
//                int thisCurrTimeFrameEnd = thisCurrTimeFrame[1];
//                int b2CurrTimeFrameStart = b2CurrTimeFrame[0];
//                int b2CurrTimeFrameEnd = b2CurrTimeFrame[1];
//
//                boolean isCurrTimeFramesOverlapping =   thisCurrTimeFrameEnd >= b2CurrTimeFrameStart &&  b2CurrTimeFrameEnd>=thisCurrTimeFrameEnd    ||
//                                                        thisCurrTimeFrameStart<=b2CurrTimeFrameEnd && b2CurrTimeFrameStart<=thisCurrTimeFrameStart ||
//                        thisCurrTimeFrameStart>b2CurrTimeFrameStart && thisCurrTimeFrameEnd< b2CurrTimeFrameEnd ||
//                        thisCurrTimeFrameStart<b2CurrTimeFrameStart && b2CurrTimeFrameEnd<thisCurrTimeFrameEnd;
//
//                if(isCurrTimeFramesOverlapping){
//                    notOverlapping = false;
//                    return notOverlapping;
//                }
//
//            }
//
//
//        }
//        return notOverlapping;
//    }

}