package domain;

import java.util.ArrayList;
import java.util.List;

public class ActiveClassFilterElement extends ClassFilterElement{

    public ActiveClassFilterElement(Class c) {
        super(c);
    }

    public Class getClassForActiveClassFilterElement(){
        return super.getClassForClassFilterElement();
    }

    @Override
    public String toString() {

        Class c = super.getClassForClassFilterElement();

        return "<html>" +
                "<font face=\"verdana\"><b><i>" +
                "Include: " +
                "</i></b></font>" +
                "<font face=\"verdana\"><b><i>" +
                "\"" +
                c.getCourseName() +
                " " + c.getCourseCatalog() +
                "\" " +
                "</i></b></font>" +
                super.toString().substring(6,super.toString().length());
    }


}
