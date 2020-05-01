package domain;

import java.io.Serializable;

public class InstructorNameRolePair implements Serializable {

    private static final long serialVersionUID = 1L;
    private String instructorName;
    private String instructorRole;

    public InstructorNameRolePair(

                                    String instructorName,
                                    String instructorRole

                                 ){

    this.instructorName = convertNaming2FirstLastName(instructorName);
    this.instructorRole = instructorRole;

    }

    private String convertNaming2FirstLastName(String name){
        String[] delimitedNameArr = name.split(",");
        String lastName = delimitedNameArr[0];
        String firstName = delimitedNameArr[1];

        return  firstName +
                " " +
                lastName;
    }

    @Override
    public boolean equals(Object obj) {

        if(!(obj instanceof InstructorNameRolePair))
            return false;

        InstructorNameRolePair p2 = (InstructorNameRolePair) obj;

        return (

                p2.instructorName.equals(this.instructorName) &&
                        p2.instructorRole.equals(this.instructorRole)

                );
    }

    @Override
    public String toString() {
        return instructorName +"(" + instructorRole + ")";
    }

    public String getInstructorName() {
        return instructorName;
    }

    public String getInstructorRole() {
        return instructorRole;
    }
}
