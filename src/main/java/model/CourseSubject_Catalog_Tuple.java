package model;

import java.io.Serializable;

public class CourseSubject_Catalog_Tuple implements Serializable {

    String subject;
    int catalog;
    int priority;

    public CourseSubject_Catalog_Tuple(

            String subject,
            int catalog
//            ,int priority
    ){
//        this.priority = priority;
        this.catalog = catalog;
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getCatalog() {
        return catalog;
    }

    public void setCatalog(int catalog) {
        this.catalog = catalog;
    }

//    public int getPriority() {
//        return priority;
//    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object obj){

        if(!(obj instanceof CourseSubject_Catalog_Tuple))
            return false;

        CourseSubject_Catalog_Tuple t2 = (CourseSubject_Catalog_Tuple) obj;
        return ((
                //this.priority == t2.priority &&
                this.catalog == t2.catalog &&
                this.subject.equals(t2.subject))
                ? true
                :
                false);
    }

    @Override
    public String toString(){

        return "<html>"
                + "Subject: "
                    + "<font face=\"verdana\" color=\"blue\"><b><i>"
                + subject
                    + "</i></b></font>"
                + ", Catalog: "
                    + "<font face=\"verdana\" color=\"blue\"><b><i>"
                + catalog
                    + "</i></b></font>"
                + "</html>" ;

               // + ", Priority: " + priority;
    }

}
