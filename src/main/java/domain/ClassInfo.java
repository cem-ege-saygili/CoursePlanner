package domain;

public class ClassInfo {

    private String Component;
    private String Subject;
    private int Catalog;
    private String Section;
    private int CourseID;
    private String Descr;
    private int TopicID;
    private String Descr2;
    private int TotEnrl;
    private int CapEnrl;
    private String Career;
    private String AcadOrg;
    private String Mon;
    private String Tues;
    private String Wed;
    private String Thurs;
    private String Fri;
    private String Sat;
    private String Sun;
    private int PatNbr;
    private String FacilID;
    private String Type;
    private int Capacity;
    private String MtgStart;
    private String MtgEnd;
    private String StartDate;
    private String EndDate;
    private String Pat;
    private String Role;
    private String Name;
    private int TopicID2;
    private int Term;
    private int ClassNbr;
    private String ClassStat;
    private int ReqRmCap;

    public ClassInfo(String Component,String Subject,int Catalog, String Section,int CourseID, String Descr,int TopicID,
                     String Descr2,int TotEnrl,int CapEnrl,String Career,String AcadOrg,String Mon,String Tues, String Wed,
                     String Thurs,String Fri,String Sat,String Sun,int PatNbr,String FacilID,String Type,int Capacity,
                     String MtgStart,String MtgEnd,String StartDate,String EndDate,String Pat, String Role,String Name,
                     int TopicID2, int Term, int ClassNbr,String ClassStat,int ReqRmCap){

        this.Component = Component;
        this.Subject = Subject;
        this.Catalog = Catalog;
        this.Section = Section;
        this.CourseID = CourseID;
        this.Descr = Descr;
        this.TopicID = TopicID;
        this.Descr2 = Descr2;
        this.TotEnrl = TotEnrl;
        this.CapEnrl = CapEnrl;
        this.Career = Career;
        this.AcadOrg = AcadOrg;
        this.Mon = Mon;
        this.Tues = Tues;
        this.Wed = Wed;
        this.Thurs = Thurs;
        this.Fri = Fri;
        this.Sat = Sat;
        this.Sun = Sun;
        this.PatNbr = PatNbr;
        this.FacilID = FacilID;
        this.Type = Type;
        this.Capacity = Capacity;
        this.MtgStart = MtgStart;
        this.MtgEnd = MtgEnd;
        this.StartDate = StartDate;
        this.EndDate = EndDate;
        this.Pat = Pat;
        this.Role = Role;
        this.Name = Name;
        this.TopicID2 = TopicID2;
        this.Term = Term;
        this.ClassNbr = ClassNbr;
        this.ClassStat = ClassStat;
        this.ReqRmCap = ReqRmCap;

    }

    public String getComponent() {
        return Component;
    }

    public String getSubject() {
        return Subject;
    }

    public int getCatalog() {
        return Catalog;
    }

    public String getSection() {
        return Section;
    }

    public int getCourseID() {
        return CourseID;
    }

    public String getDescr() {
        return Descr;
    }

    public int getTopicID() {
        return TopicID;
    }

    public String getDescr2() {
        return Descr2;
    }

    public int getTotEnrl() {
        return TotEnrl;
    }

    public int getCapEnrl() {
        return CapEnrl;
    }

    public String getCareer() {
        return Career;
    }

    public String getAcadOrg() {
        return AcadOrg;
    }

    public String getMon() {
        return Mon;
    }

    public String getTues() {
        return Tues;
    }

    public String getWed() {
        return Wed;
    }

    public String getThurs() {
        return Thurs;
    }

    public String getFri() {
        return Fri;
    }

    public String getSat() {
        return Sat;
    }

    public String getSun() {
        return Sun;
    }

    public int getPatNbr() {
        return PatNbr;
    }

    public String getFacilID() {
        return FacilID;
    }

    public String getType() {
        return Type;
    }

    public int getCapacity() {
        return Capacity;
    }

    public String getMtgStart() {
        return MtgStart;
    }

    public String getMtgEnd() {
        return MtgEnd;
    }

    public String getStartDate() {
        return StartDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public String getPat() {
        return Pat;
    }

    public String getRole() {
        return Role;
    }

    public String getName() {
        return Name;
    }

    public int getTopicID2() {
        return TopicID2;
    }

    public int getTerm() {
        return Term;
    }

    public int getClassNbr() {
        return ClassNbr;
    }

    public String getClassStat() {
        return ClassStat;
    }

    public int getReqRmCap() {
        return ReqRmCap;
    }

    public String toString(){
        String str = "";

        str += "Component: " + this.Component + "\n";
        str += "Subject: " + this.Subject + "\n";
        str += "Catalog: " + this.Catalog + "\n";
        str += "Section: " + this.Section + "\n";
        str += "CourseID: " + this.CourseID + "\n";
        str += "Descr: " + this.Descr + "\n";
        str += "TopicID: " + this.TopicID + "\n";
        str += "Descr2: " + this.Descr2 + "\n";
        str += "TotEnrl: " + this.TotEnrl + "\n";
        str += "CapEnrl: " + this.CapEnrl + "\n";
        str += "Career: " + this.Career + "\n";
        str += "AcadOrg: " + this.AcadOrg + "\n";
        str += "Mon: " + this.Mon + "\n";
        str += "Tues: " + this.Tues + "\n";
        str += "Wed: " + this.Wed + "\n";
        str += "Thurs: " + this.Thurs + "\n";
        str += "Fri: " + this.Fri + "\n";
        str += "Sat: " + this.Sat + "\n";
        str += "Sun: " + this.Sun + "\n";
        str += "PatNbr: " + this.PatNbr + "\n";
        str += "FacilID: " + this.FacilID + "\n";
        str += "Type: " + this.Type + "\n";
        str += "Capacity: " + this.Capacity + "\n";
        str += "MtgStart: " + this.MtgStart + "\n";
        str += "MtgEnd: " + this.MtgEnd + "\n";
        str += "StartDate: " + this.StartDate + "\n";
        str += "EndDate: " + this.EndDate + "\n";
        str += "Pat: " + this.Pat + "\n";
        str += "Role: " + this.Role + "\n";
        str += "Name: " + this.Name + "\n";
        str += "TopicID2: " + this.TopicID2 + "\n";
        str += "Term: " + this.Term + "\n";
        str += "ClassNbr: " + this.ClassNbr + "\n";
        str += "ClassStat: " + this.ClassStat + "\n";
        str += "ReqRmCap: " + this.ReqRmCap + "\n";

        return str;
    }

}
