﻿select i.InstructorName, c.CourseSubject, c.CourseCatalog,
         c.CourseDescr, c.CourseDescr2, cl.ClassComponent,
        cl.ClassMtgStart, cl.ClassMtgEnd,        
        cl.ClassMon, cl.ClassTues, cl.ClassWed, cl.ClassThurs,cl.ClassFri        
from Classes cl 
inner join Courses c on c.CourseId=cl.ClassCourseId
inner join Class_Instructor_Infos cIinfo on cIinfo.ClassId = cl.ClassId
inner join Instructors i on i.InstructorId = cIinfo.InstructorId
where (c.CourseSubject = 'MGMT' and (c.CourseCatalog = 411 or 
            c.CourseCatalog = 451 or c.CourseCatalog = 445))
       or
       (c.CourseSubject = 'ENGR' and c.CourseCatalog = 400)
order by c.CourseSubject, c.CourseCatalog;