package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Optional.of;
import static java.util.stream.Collectors.toList;

public class ClassBundle{

    private String courseSubject;
    private int courseCatalog;
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

    @Override
    public boolean equals(Object obj) {
        ClassBundle cb = (ClassBundle) obj;
        int id1 = bundleId;
        int id2 = cb.bundleId;

        if(id1 == id2)
            return true;

        return false;
    }

//    public static List<ClassBundle> generateBundlesFromClasses(List<domain.Class> classes){
//
//        List<ClassBundle> bundles = new ArrayList<>();
//        List<domain.Class> prb_classes = new ArrayList<>();
//        List<domain.Class> lec_classes = new ArrayList<>();
//        List<domain.Class> lab_classes = new ArrayList<>();
//
//        boolean lecFlag = false;
//        boolean prbFlag = false;
//        boolean labFlag = false;
//
//        for(domain.Class curClass:classes){
//            String curClassComponent = curClass.getComponent();
//            if(curClassComponent.equals("PRB")){
//                prb_classes.add(curClass);
//                prbFlag = true;
//            }else if(curClassComponent.equals("LEC")){
//                lec_classes.add(curClass);
//                lecFlag = true;
//            }else if(curClassComponent.substring(0,3).equals("LAB")){
//                lab_classes.add(curClass);
//                labFlag = true;
//            }else{
//                return null;
//            }
//
//        }
//
//
//
//    }

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