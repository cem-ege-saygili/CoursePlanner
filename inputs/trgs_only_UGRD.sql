BEGIN TRANSACTION;
CREATE TRIGGER trg_delete_course BEFORE DELETE ON Courses
WHEN(
(1
=
(SELECT count(DISTINCT c.CourseId)
FROM Courses c
INNER JOIN Class_Course_Infos cci ON c.CourseId = cci.CourseId
WHERE cci.ClassId IN
(SELECT cci.ClassId
FROM Courses c
INNER JOIN Class_Course_Infos cci ON c.CourseId = cci.CourseId
WHERE c.CourseId = OLD.CourseId))))
BEGIN
DELETE FROM Classes WHERE ClassId IN (SELECT cl.ClassId
FROM Courses c
INNER JOIN Class_Course_Infos cci ON c.CourseId = cci.CourseId
INNER JOIN Classes cl ON cci.ClassId = cl.ClassId
WHERE c.CourseId = OLD.CourseId);
END ;
﻿CREATE TRIGGER trg_delete_class BEFORE DELETE ON Classes
WHEN(
(1
=
(SELECT count(DISTINCT cl.ClassId) FROM Instructors i
INNER JOIN Class_Instructor_Infos cii on cii.InstructorId = i.InstructorId
INNER JOIN Classes cl on cl.ClassId = cii.ClassId
WHERE i.InstructorId
IN (SELECT cii.InstructorId FROM
Class_Instructor_Infos cii
INNER JOIN Classes cl ON cii.ClassId = cl.ClassId
WHERE cl.ClassId = OLD.ClassId))))
BEGIN
DELETE FROM Instructors WHERE InstructorId 
IN (SELECT cii.InstructorId FROM
Class_Instructor_Infos cii
INNER JOIN Classes cl ON cii.ClassId = cl.ClassId
WHERE cl.ClassId = OLD.ClassId);
END ;
END TRANSACTION;

﻿DELETE FROM Courses where CourseCareer <> 'UGRD' or CourseSubject = 'ISP';

﻿SELECT "Time of Class"
FROM
(
SELECT *,
CASE
    WHEN 
        SUBSTR("Time of Class", -2, 2) = 'AM'
        AND
        SUBSTR("Time of Class", 0, 3) = '12'
    THEN 0
    WHEN 
        SUBSTR("Time of Class", -2, 2) = 'AM'
    THEN 1
    WHEN 
        SUBSTR("Time of Class", -2, 2) = 'PM'
        AND
        SUBSTR("Time of Class", 0, 3) = '12'
    THEN 2
    ELSE 3
END AS Ordering
FROM
(
SELECT ClassMtgStart as "Time of Class"
from Classes
UNION
SELECT ClassMtgEnd as "Time of Class"
from Classes
)
order by 
SUBSTR("Time of Class", -2, 2), Ordering,
LENGTH("Time of Class")
);