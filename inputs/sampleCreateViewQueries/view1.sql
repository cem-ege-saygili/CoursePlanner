CREATE VIEW CoursesInformation_of_Instructors_Whose_FirstAndLastNamesStartingWithLetterA AS
SELECT  
SUBSTR(i.InstructorName, INSTR(i.InstructorName, ',') + 1, LENGTH(i.InstructorName) - INSTR(i.InstructorName, ','))
|| ' ' ||
SUBSTR(i.InstructorName, 0, INSTR(i.InstructorName, ',')) 
as "Course Instructor First & Last Name",
c.CourseSubject || ' ' || c.CourseCatalog as "Course Name", 
c.CourseCareer as "Course Level", 
c.CourseAcadOrg as "Course Faculty", 
c.CourseDescr as "Course Description"
from Classes cl
inner join Class_Course_Infos cci on cci.ClassId = cl.ClassId
inner join Courses c on c.CourseId = cci.CourseId
inner join Class_Instructor_Infos cii on cii.ClassId = cl.ClassId
inner join Instructors i on cii.InstructorId = i.InstructorId
where 
(SUBSTR
(i.InstructorName, 
INSTR(i.InstructorName, ',') + 1, 
LENGTH(i.InstructorName) - INSTR(i.InstructorName, ','))
like
'A%')
AND
(SUBSTR
(i.InstructorName, 
0, 
INSTR(i.InstructorName, ',')) 
like
'A%')
AND
(cii.ClassInstructorRole = 'SI' or cii.ClassInstructorRole = 'PI')
group by c.CourseId
order by "Course Instructor First & Last Name", "Course Name", "Course Description";
--Create a view for the following query:
	--Show instructor name in first&last fashion, course level, course faculty, course description
	--Show the entries for the instructors who not only assume the role of either PI or SI
	--but also have their first and last names starting with letter 'A'
	--group same courses together