insert into Class_Instructor_Infos(
    ClassId,
    InstructorId,
    ClassInstructorRole)

SELECT cl.ClassId, i.InstructorId, c.Role
from CoursePlannerBIGGEST c
inner join Classes cl on
(
c.MtgStart = cl.ClassMtgStart and
c.MtgEnd = cl.ClassMtgEnd and
c.Mon = cl.ClassMon and
c.Tues = cl.ClassTues and
c.Wed = cl.ClassWed and
c.Thurs = cl.ClassThurs and
(c.FacilID = cl.ClassFacilID or (cl.ClassFacilID = 'TBA' and c.FacilID = ''))
)
inner join Instructors i on i.InstructorName = c.Name
group by cl.ClassId, i.InstructorId, c.Role
order by cl.ClassId, i.InstructorId, c.Role;