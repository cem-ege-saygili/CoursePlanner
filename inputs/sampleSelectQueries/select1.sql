select  
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
where i.InstructorName = 'MCMURRAY,ANNA' or 
i.InstructorName = 'AGLİO,DANİELE' or 
i.InstructorName = 'YILMAZ,NAZMİ'
group by c.CourseId
order by "Course Instructor First & Last Name", "Course Name", "Course Description";
--Show instructor name in first&last fashion, course level, course faculty, course description
--Show the entries for the following instructors: "AGLİO,DANİELE", "YILMAZ,NAZMİ" and "MCMURRAY,ANNA"
--group same courses together