package domain;

public class InstructorNameRolePair {

    private String instructorName;
    private String instructorRole;

    public InstructorNameRolePair(

                                    String instructorName,
                                    String instructorRole

                                 ){

    this.instructorName = instructorName;
    this.instructorRole = instructorRole;

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
