select  cl.ClassId,cl.ClassComponent,
        cl.ClassMtgStart, cl.ClassMtgEnd,
        cl.ClassMon, cl.ClassTues, cl.ClassWed, cl.ClassThurs,cl.ClassFri,
        cl.ClassFacilID
from Courses c
        inner join Class_Course_Infos cci on cci.CourseId = c.CourseId
        inner join Classes cl on cci.ClassId = cl.ClassId
where c.CourseSubject = ? and c.CourseCatalog = ?;