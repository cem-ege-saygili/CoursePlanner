insert into Courses
select CourseID, Subject, Catalog, Career, AcadOrg, Descr, Descr2
from CoursePlannerBIGGEST
group by CourseID
ORDER BY CourseID;