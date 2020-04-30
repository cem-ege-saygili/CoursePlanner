insert into Class_Course_Infos (

    ClassId,
    CourseId,
    ClassTotEnrl,
    ClassCapEnrl)

select cl.ClassId, c.CourseId, c.TotEnrl, c.CapEnrl
from CoursePlannerBIGGEST c
inner join Classes cl on (
c.MtgStart = cl.ClassMtgStart and
c.MtgEnd = cl.ClassMtgEnd and
c.Mon = cl.ClassMon and
c.Tues = cl.ClassTues and
c.Wed = cl.ClassWed and
c.Thurs = cl.ClassThurs and
(c.FacilID = cl.ClassFacilID or (cl.ClassFacilID = 'TBA' and c.FacilID = ''))
)
group by cl.ClassId, c.CourseId
order by cl.ClassId, c.CourseId;