select  i.InstructorName, cii.ClassInstructorRole
from Classes cl
  inner join Class_Instructor_Infos cii on cii.ClassId = cl.ClassId
  inner join Instructors i on i.InstructorId = cii.InstructorId
where cl.ClassId = ?;