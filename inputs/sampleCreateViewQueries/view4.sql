CREATE VIEW FreshmanCourses_NumClassesForClassTypes AS
SELECT 
c.CourseSubject || ' ' || c.CourseCatalog as "Course Name",
cl.ClassComponent as "Class Type",
count(*) as "Number of Classes"
from Classes cl
natural join Class_Course_Infos cci
natural join Courses c
where 
c.CourseCatalog < 200 and c.CourseSubject != 'ELC'
group by c.CourseSubject, c.CourseCatalog, cl.ClassComponent
order by "Course Name", "Number of Classes" desc, cci.CourseId, cl.ClassComponent;
--Create a view for the following query:
	--Show course name, class type and number of respective classes
	--from the entries whose course corresponds to a freshman course
	--order the results according to their class counts from highest to lowest 
	--while keeping the same courses together as a bulk 
	--(i.e. all ACWR 101 courses appear one after another, 
	--ordered in a descending fashion by the number of classes)