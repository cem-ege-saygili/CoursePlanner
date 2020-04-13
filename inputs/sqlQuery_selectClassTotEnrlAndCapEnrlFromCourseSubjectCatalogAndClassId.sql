select ClassTotEnrl, ClassCapEnrl
from Class_Course_Infos
where
CourseId = (
  select CourseId
  from Courses
  where CourseSubject = ? and CourseCatalog = ?
) and ClassId = ?;