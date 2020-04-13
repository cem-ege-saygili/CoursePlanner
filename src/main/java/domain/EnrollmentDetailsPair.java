package domain;

public class EnrollmentDetailsPair {

    private int totlEnrl;
    private int capEnrl;

    public EnrollmentDetailsPair(

            int totlEnrl,
            int capEnrl

    ){

        this.capEnrl = capEnrl;
        this.totlEnrl = totlEnrl;

    }

    @Override
    public boolean equals(Object obj) {

        EnrollmentDetailsPair e2= (EnrollmentDetailsPair) obj;

        return (

                e2.capEnrl == this.capEnrl &&
                        e2.totlEnrl == this.totlEnrl

        );
    }

    @Override
    public String toString() {
        return "EnrollmentDetailsPair: (TotalEnrl: " +
                totlEnrl + ", CapEnrl: " + capEnrl + ")";
    }

    public int getTotlEnrl() {
        return totlEnrl;
    }

    public int getCapEnrl() {
        return capEnrl;
    }
}
