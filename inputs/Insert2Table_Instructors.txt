insert into Instructors (InstructorName)
select DISTINCT name FROM CoursePlannerBIGGEST
where name != ''
order by name;