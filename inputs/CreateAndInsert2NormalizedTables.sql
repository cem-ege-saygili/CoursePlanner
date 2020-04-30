insert into Courses
select CourseID, Subject, Catalog, Career, AcadOrg, Descr, Descr2
from CoursePlannerBIGGEST
group by CourseID
ORDER BY CourseID;

insert into Instructors (InstructorName)
select DISTINCT name FROM CoursePlannerBIGGEST
where name != ''
order by name;

insert into Classes (
    --ClassNbr,
    --ClassPatNbr,
    --ClassCourseId,
    ClassComponent,
    ClassSection,
    ClassMtgStart,
    ClassMtgEnd,
    ClassMon,
    ClassTues,
    ClassWed,
    ClassThurs,
    ClassFri,
    --ClassTotEnrl,
    --ClassCapEnrl,
    ClassFacilID
    )
select

--ClassNbr,
--PatNbr,
--CourseID,
Component, Section,
       MtgStart, MtgEnd,
       Mon, Tues, Wed, Thurs, Fri,
       --TotEnrl, CapEnrl,
       FacilID
from CoursePlannerBIGGEST
group by MtgStart, MtgEnd, Mon, Tues, Wed, Thurs, Fri, FacilID
order by MtgStart, MtgEnd, Mon, Tues, Wed, Thurs, Fri, FacilID;

CREATE TABLE IF NOT EXISTS Class_Instructor_Infos(

	Class_Instructor_Info_Id    INTEGER
	                            NOT NULL
	                            PRIMARY KEY
	                            AUTOINCREMENT,

    ClassId                     INTEGER(4),
    InstructorId                INTEGER(4),
    ClassInstructorRole         NVARCHAR(2),

    FOREIGN KEY (
      				ClassId
      			)
    REFERENCES Classes(ClassId)     ON DELETE NO ACTION
      							    ON UPDATE NO ACTION,
    FOREIGN KEY (
          		    InstructorId
          	    )
    REFERENCES Instructors(InstructorId)     ON DELETE NO ACTION
          							         ON UPDATE NO ACTION

);

CREATE TABLE IF NOT EXISTS Class_Course_Infos(

	Class_Course_Info_Id    INTEGER
	                        NOT NULL
	                        PRIMARY KEY
	                        AUTOINCREMENT,

    ClassId                     INTEGER(4),
    CourseId                INTEGER(4),
    ClassTotEnrl INTEGER(3),
    ClassCapEnrl INTEGER(3),

    FOREIGN KEY (
      				ClassId
      			)
    REFERENCES Classes(ClassId)     ON DELETE NO ACTION
      							    ON UPDATE NO ACTION,
    FOREIGN KEY (
          		    CourseId
          	    )
    REFERENCES Courses(CourseId)     ON DELETE NO ACTION
          							 ON UPDATE NO ACTION

);

//---------------------INSERT2TABLES--------------------------------------------


insert into Courses
select CourseID, Subject, Catalog, Career, AcadOrg, Descr, Descr2
from CoursePlannerBIGGEST
group by CourseID
ORDER BY CourseID;

insert into Instructors (InstructorName)
select DISTINCT name FROM CoursePlannerBIGGEST
where name != ''
order by name;

insert into Classes (
    --ClassNbr,
    --ClassPatNbr,
    --ClassCourseId,
    ClassComponent,
    ClassSection,
    ClassMtgStart,
    ClassMtgEnd,
    ClassMon,
    ClassTues,
    ClassWed,
    ClassThurs,
    ClassFri,
    --ClassTotEnrl,
    --ClassCapEnrl,
    ClassFacilID
    )
select

--ClassNbr,
--PatNbr,
--CourseID,
Component, Section,
       MtgStart, MtgEnd,
       Mon, Tues, Wed, Thurs, Fri,
       --TotEnrl, CapEnrl,
       FacilID
from CoursePlannerBIGGEST
group by MtgStart, MtgEnd, Mon, Tues, Wed, Thurs, Fri, FacilID
order by MtgStart, MtgEnd, Mon, Tues, Wed, Thurs, Fri, FacilID;

insert into Class_Instructor_Infos(
    ClassId,
    InstructorId,
    ClassInstructorRole)

SELECT cl.ClassId, i.InstructorId, c.Role
from CoursePlannerBIGGEST c
inner join Classes cl on
(
c.MtgStart = cl.ClassMtgStart and
c.MtgEnd = cl.ClassMtgEnd and
c.Mon = cl.ClassMon and
c.Tues = cl.ClassTues and
c.Wed = cl.ClassWed and
c.Thurs = cl.ClassThurs and
c.FacilID = cl.ClassFacilID
)
inner join Instructors i on i.InstructorName = c.Name
group by cl.ClassId, i.InstructorId, c.Role
order by cl.ClassId, i.InstructorId, c.Role;

insert into Class_Course_Infos (

    ClassId,
    CourseId,
    ClassTotEnrl,
    ClassCapEnrl)

select cl.ClassId, c.CourseId, c.TotEnrl, c.CapEnrl
from CoursePlannerBIGGEST c
inner join Classes cl on (
c.MtgStart = cl.ClassMtgStart and
c.MtgEnd = cl.ClassMtgEnd and
c.Mon = cl.ClassMon and
c.Tues = cl.ClassTues and
c.Wed = cl.ClassWed and
c.Thurs = cl.ClassThurs and
c.FacilID = cl.ClassFacilID
)
group by cl.ClassId, c.CourseId
order by cl.ClassId, c.CourseId;

