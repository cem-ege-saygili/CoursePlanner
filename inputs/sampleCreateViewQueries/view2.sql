CREATE VIEW FirstNames_of_InstructorsWithLastName_YILMAZ AS
SELECT 
SUBSTR(i.InstructorName, 
INSTR(i.InstructorName, ',') + 1, 
LENGTH(i.InstructorName) - INSTR(i.InstructorName, ','))
as "Instructor First Name"
FROM Instructors i
WHERE EXISTS (
SELECT ClassId 
FROM Class_Instructor_Infos cii 
WHERE 
cii.InstructorId = i.InstructorId
AND 
(cii.ClassInstructorRole = 'SI' or cii.ClassInstructorRole = 'PI')
AND
i.InstructorName like 'YILMAZ%');
--Create a view for the following query:
	--show the first names of the instructors 
	--who teach courses assuming the role of either SI or PI
	--and whose last name is YILMAZ