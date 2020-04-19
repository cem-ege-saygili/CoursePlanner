SELECT DISTINCT cl.ClassComponent
from Courses c
inner join Class_Course_Infos cci on cci.CourseId = c.CourseId
inner join Classes cl on cci.ClassId = cl.ClassId
where c.CourseSubject = ? and c.CourseCatalog = ?
order by cl.ClassComponent = 'LAB', cl.ClassComponent = 'DIS',  cl.ClassComponent = 'PRB', cl.ClassComponent = 'LEC';