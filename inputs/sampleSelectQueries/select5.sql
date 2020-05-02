SELECT 
SUBSTR
(InstructorName, 
INSTR(InstructorName, ',') + 1, 
LENGTH(InstructorName) - INSTR(InstructorName,','))
|| ' ' ||
SUBSTR
(InstructorName, 
0, 
INSTR(InstructorName, ',')) 
as "Instructors who has overloaded classes"
FROM
(
Select DISTINCT InstructorName
FROM Instructors
WHERE 
InstructorId in
(
SELECT
InstructorId
FROM Class_Instructor_Infos
where 
ClassInstructorRole != 'TA' and
ClassId in
(
SELECT 
ClassId
FROM Classes 
WHERE ClassId in 
(
SELECT ClassId
FROM Class_Course_Infos
WHERE ClassTotEnrl > ClassCapEnrl
))))
order by "Instructors who has overloaded classes";
--Show the name of the instructors assuming the role that is different than TA
--who have overloaded classes
--show the name in first&last fashion.