select    cl.ClassId,cl.ClassComponent,
        cl.ClassMtgStart, cl.ClassMtgEnd,        
        cl.ClassMon, cl.ClassTues, cl.ClassWed, cl.ClassThurs,cl.ClassFri,
        cl.ClassFacilID
        --i.InstructorName, cIinfo.ClassInstructorRole
from Classes cl 
inner join Courses c on c.CourseId=cl.ClassCourseId
--inner join Class_Instructor_Infos cIinfo on cIinfo.ClassId = cl.ClassId
--inner join Instructors i on i.InstructorId = cIinfo.InstructorId
where c.CourseSubject = ? and c.CourseCatalog = ?
order by cl.ClassId;