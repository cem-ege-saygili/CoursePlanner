SELECT 
c.CourseSubject || ' ' || c.CourseCatalog as "Course Name",
cl.ClassComponent as "Class Type",
count(*) as "Number of Classes"
from Classes cl
natural join Class_Course_Infos cci
natural join Courses c
where 
(c.CourseSubject = 'PHYS' and c.CourseCatalog = '101')
or
(c.CourseSubject = 'ELEC' and c.CourseCatalog = '198')
group by c.CourseSubject, c.CourseCatalog, cl.ClassComponent
order by "Course Name", "Number of Classes" desc, cci.CourseId, cl.ClassComponent;
--Show course name, class type and number of respective classes
--from the entries whose course name is either PHYS 101 or ELEC 198
--order the results according to their class counts from highest to lowest 
--while keeping the same courses together as a bulk 
--(i.e. all ELEC 198 courses appear one after another, 
--ordered in a descending fashion by the number of classes)