CREATE TABLE IF NOT EXISTS Courses_Log(
    CoursesLog_LogId INTEGER   NOT NULL
                               PRIMARY KEY AUTOINCREMENT,
    CoursesLog_DateTime DATETIME NOT NULL,
    CoursesLog_Type  NVARCHAR(8) NOT NULL
                     CHECK(CoursesLog_Type = 'deleted'
                     OR CoursesLog_Type = 'inserted'
                     OR CoursesLog_Type = 'updated_OLD_values'
                     OR CoursesLog_Type = 'updated_NEW_values'),
    CoursesLog_CourseId INTEGER(4) NOT NULL,
    CoursesLog_CourseSubject NVARCHAR(4) NOT NULL,
    CoursesLog_CourseCatalog INTEGER(3) NOT NULL,
    CoursesLog_CourseCareer NVARCHAR(4) NOT NULL,
    CoursesLog_CourseAcadOrg NVARCHAR(7) NOT NULL,
    CoursesLog_CourseDescr NVARCHAR(100) NOT NULL,
    CoursesLog_CourseDescr2 NVARCHAR(100) NOT NULL);

CREATE TABLE IF NOT EXISTS Classes_Log(
    ClassesLog_LogId INTEGER   NOT NULL
                               PRIMARY KEY AUTOINCREMENT,
    ClassesLog_DateTime DATETIME NOT NULL,
    ClassesLog_Type  NVARCHAR(8) NOT NULL
                           CHECK(ClassesLog_Type = 'deleted'
                           OR ClassesLog_Type = 'inserted'
                           OR ClassesLog_Type = 'updated_OLD_values'
                           OR ClassesLog_Type = 'updated_NEW_values'),
    ClassesLog_ClassId INTEGER NOT NULL,
    ClassesLog_ClassComponent NVARCHAR(3) NOT NULL,
    ClassesLog_ClassSection NVARCHAR(4) NOT NULL,
    ClassesLog_ClassMtgStart NVARCHAR(11) NOT NULL,
    ClassesLog_ClassMtgEnd NVARCHAR(11) NOT NULL,
    ClassesLog_ClassMon NVARCHAR(1) NOT NULL,
    ClassesLog_ClassTues NVARCHAR(1) NOT NULL,
    ClassesLog_ClassWed NVARCHAR(1) NOT NULL,
    ClassesLog_ClassThurs NVARCHAR(1) NOT NULL,
    ClassesLog_ClassFri NVARCHAR(1) NOT NULL,
    ClassesLog_ClassFacilID NVARCHAR(100) NOT NULL);

CREATE TABLE IF NOT EXISTS Instructors_Log(
    InstructorsLog_LogId INTEGER   NOT NULL
                                   PRIMARY KEY AUTOINCREMENT,
    InstructorsLog_DateTime DATETIME NOT NULL,
    InstructorsLog_Type  NVARCHAR(8) NOT NULL
                           CHECK(InstructorsLog_Type = 'deleted'
                           OR InstructorsLog_Type = 'inserted'
                           OR InstructorsLog_Type = 'updated_OLD_values'
                           OR InstructorsLog_Type = 'updated_NEW_values'),
    InstructorsLog_InstructorId INTEGER NOT NULL,
    InstructorsLog_InstructorName NVARCHAR(100) NOT NULL);

BEGIN TRANSACTION;

CREATE TRIGGER trg_before_delete_course
BEFORE DELETE ON Courses
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
    DELETE FROM Classes
    WHERE ClassId IN
        (SELECT cl.ClassId
        FROM Courses c
        INNER JOIN Class_Course_Infos cci ON c.CourseId = cci.CourseId
        INNER JOIN Classes cl ON cci.ClassId = cl.ClassId
        WHERE c.CourseId = OLD.CourseId);
END ;

CREATE TRIGGER trg_after_insert_courses
AFTER INSERT ON Courses
BEGIN
    INSERT INTO Courses_Log(CoursesLog_DateTime,
                            CoursesLog_Type,
                            CoursesLog_CourseId,
                            CoursesLog_CourseSubject,
                            CoursesLog_CourseCatalog,
                            CoursesLog_CourseCareer,
                            CoursesLog_CourseAcadOrg,
                            CoursesLog_CourseDescr,
                            CoursesLog_CourseDescr2)
    values(datetime('now'),
           'inserted',
           NEW.CourseId,
           NEW.CourseSubject,
           NEW.CourseCatalog,
           NEW.CourseCareer,
           NEW.CourseAcadOrg,
           NEW.CourseDescr,
           NEW.CourseDescr2) ;
END;

CREATE TRIGGER trg_after_update_courses
AFTER UPDATE ON Courses
BEGIN
    INSERT INTO Courses_Log(CoursesLog_DateTime,
                            CoursesLog_Type,
                            CoursesLog_CourseId,
                            CoursesLog_CourseSubject,
                            CoursesLog_CourseCatalog,
                            CoursesLog_CourseCareer,
                            CoursesLog_CourseAcadOrg,
                            CoursesLog_CourseDescr,
                            CoursesLog_CourseDescr2)
    values(datetime('now'),
           'updated_OLD_values',
           OLD.CourseId,
           OLD.CourseSubject,
           OLD.CourseCatalog,
           OLD.CourseCareer,
           OLD.CourseAcadOrg,
           OLD.CourseDescr,
           OLD.CourseDescr2) ;

    INSERT INTO Courses_Log(CoursesLog_DateTime,
                            CoursesLog_Type,
                            CoursesLog_CourseId,
                            CoursesLog_CourseSubject,
                            CoursesLog_CourseCatalog,
                            CoursesLog_CourseCareer,
                            CoursesLog_CourseAcadOrg,
                            CoursesLog_CourseDescr,
                            CoursesLog_CourseDescr2)
    values(datetime('now'),
           'updated_NEW_values',
           NEW.CourseId,
           NEW.CourseSubject,
           NEW.CourseCatalog,
           NEW.CourseCareer,
           NEW.CourseAcadOrg,
           NEW.CourseDescr,
           NEW.CourseDescr2) ;

END;

CREATE TRIGGER trg_abort_insert_to_Courses --before_insert_trigger
BEFORE INSERT ON Courses
WHEN(NEW.CourseCareer <> 'UGRD' AND
    NEW.CourseCareer <> 'MAST' AND
    NEW.CourseCareer <> 'PHD' AND
    NEW.CourseCareer <> 'EEDU')
BEGIN
    SELECT
    RAISE(ABORT,
    '
    Insert Aborted due to unacceptable CourseCareer:
    Applicable course levels are as follows:
    UGRD, MAST, PHD and EEDU');
END;

CREATE TRIGGER trg_abort_update_to_Courses --before_update_trigger
BEFORE UPDATE ON Courses
WHEN(NEW.CourseCareer <> 'UGRD' AND
    NEW.CourseCareer <> 'MAST' AND
    NEW.CourseCareer <> 'PHD' AND
    NEW.CourseCareer <> 'EEDU')
BEGIN
    SELECT
    RAISE(ABORT,
    '
    Update Aborted due to unacceptable CourseCareer:
    Applicable course levels are as follows:
    UGRD, MAST, PHD and EEDU');
END;

CREATE TRIGGER trg_after_delete_courses
AFTER DELETE ON Courses
BEGIN
    INSERT INTO Courses_Log(CoursesLog_DateTime,
                            CoursesLog_Type,
                            CoursesLog_CourseId,
                            CoursesLog_CourseSubject,
                            CoursesLog_CourseCatalog,
                            CoursesLog_CourseCareer,
                            CoursesLog_CourseAcadOrg,
                            CoursesLog_CourseDescr,
                            CoursesLog_CourseDescr2)
    values(datetime('now'),
           'deleted',
           OLD.CourseId,
           OLD.CourseSubject,
           OLD.CourseCatalog,
           OLD.CourseCareer,
           OLD.CourseAcadOrg,
           OLD.CourseDescr,
           OLD.CourseDescr2) ;
END;

﻿CREATE TRIGGER trg_before_delete_class
BEFORE DELETE ON Classes
WHEN(
    (1
    =
    (SELECT count(DISTINCT cl.ClassId)
    FROM Instructors i
    INNER JOIN Class_Instructor_Infos cii on cii.InstructorId = i.InstructorId
    INNER JOIN Classes cl on cl.ClassId = cii.ClassId
    WHERE i.InstructorId IN
        (SELECT cii.InstructorId
        FROM Class_Instructor_Infos cii
        INNER JOIN Classes cl ON cii.ClassId = cl.ClassId
        WHERE cl.ClassId = OLD.ClassId))))
BEGIN
    DELETE FROM Instructors
    WHERE InstructorId IN
        (SELECT cii.InstructorId
        FROM Class_Instructor_Infos cii
        INNER JOIN Classes cl ON cii.ClassId = cl.ClassId
        WHERE cl.ClassId = OLD.ClassId);
END ;

CREATE TRIGGER trg_after_insert_classes
AFTER INSERT ON Classes
BEGIN
    INSERT INTO Classes_Log(ClassesLog_DateTime,
                            ClassesLog_Type,
                            ClassesLog_ClassId,
                            ClassesLog_ClassComponent,
                            ClassesLog_ClassSection,
                            ClassesLog_ClassMtgStart,
                            ClassesLog_ClassMtgEnd,
                            ClassesLog_ClassMon,
                            ClassesLog_ClassTues,
                            ClassesLog_ClassWed,
                            ClassesLog_ClassThurs,
                            ClassesLog_ClassFri,
                            ClassesLog_ClassFacilID)
    values(datetime('now'),
           'inserted',
           NEW.ClassId,
           NEW.ClassComponent,
           NEW.ClassSection,
           NEW.ClassMtgStart,
           NEW.ClassMtgEnd,
           NEW.ClassMon,
           NEW.ClassTues,
           NEW.ClassWed,
           NEW.ClassThurs,
           NEW.ClassFri,
           NEW.ClassFacilID) ;
END ;

CREATE TRIGGER trg_after_update_classes
AFTER UPDATE ON Classes
BEGIN
INSERT INTO Classes_Log(ClassesLog_DateTime,
                            ClassesLog_Type,
                            ClassesLog_ClassId,
                            ClassesLog_ClassComponent,
                            ClassesLog_ClassSection,
                            ClassesLog_ClassMtgStart,
                            ClassesLog_ClassMtgEnd,
                            ClassesLog_ClassMon,
                            ClassesLog_ClassTues,
                            ClassesLog_ClassWed,
                            ClassesLog_ClassThurs,
                            ClassesLog_ClassFri,
                            ClassesLog_ClassFacilID)
    values(datetime('now'),
           'updated_OLD_values',
           OLD.ClassId,
           OLD.ClassComponent,
           OLD.ClassSection,
           OLD.ClassMtgStart,
           OLD.ClassMtgEnd,
           OLD.ClassMon,
           OLD.ClassTues,
           OLD.ClassWed,
           OLD.ClassThurs,
           OLD.ClassFri,
           OLD.ClassFacilID) ;
    INSERT INTO Classes_Log(ClassesLog_DateTime,
                            ClassesLog_Type,
                            ClassesLog_ClassId,
                            ClassesLog_ClassComponent,
                            ClassesLog_ClassSection,
                            ClassesLog_ClassMtgStart,
                            ClassesLog_ClassMtgEnd,
                            ClassesLog_ClassMon,
                            ClassesLog_ClassTues,
                            ClassesLog_ClassWed,
                            ClassesLog_ClassThurs,
                            ClassesLog_ClassFri,
                            ClassesLog_ClassFacilID)
    values(datetime('now'),
           'updated_NEW_values',
           NEW.ClassId,
           NEW.ClassComponent,
           NEW.ClassSection,
           NEW.ClassMtgStart,
           NEW.ClassMtgEnd,
           NEW.ClassMon,
           NEW.ClassTues,
           NEW.ClassWed,
           NEW.ClassThurs,
           NEW.ClassFri,
           NEW.ClassFacilID) ;
END ;

CREATE TRIGGER trg_after_delete_classes
AFTER DELETE ON Classes
BEGIN
    INSERT INTO Classes_Log(ClassesLog_DateTime,
                            ClassesLog_Type,
                            ClassesLog_ClassId,
                            ClassesLog_ClassComponent,
                            ClassesLog_ClassSection,
                            ClassesLog_ClassMtgStart,
                            ClassesLog_ClassMtgEnd,
                            ClassesLog_ClassMon,
                            ClassesLog_ClassTues,
                            ClassesLog_ClassWed,
                            ClassesLog_ClassThurs,
                            ClassesLog_ClassFri,
                            ClassesLog_ClassFacilID)
    values(datetime('now'),
           'deleted',
           OLD.ClassId,
           OLD.ClassComponent,
           OLD.ClassSection,
           OLD.ClassMtgStart,
           OLD.ClassMtgEnd,
           OLD.ClassMon,
           OLD.ClassTues,
           OLD.ClassWed,
           OLD.ClassThurs,
           OLD.ClassFri,
           OLD.ClassFacilID);
END;

CREATE TRIGGER trg_after_delete_instructors
AFTER DELETE ON Instructors
BEGIN
    INSERT INTO Instructors_Log(InstructorsLog_DateTime,
                            InstructorsLog_Type,
                            InstructorsLog_InstructorId,
                            InstructorsLog_InstructorName)
    values(datetime('now'),
           'deleted',
           OLD.InstructorId,
           OLD.InstructorName);
END;

CREATE TRIGGER trg_after_insert_instructors
AFTER INSERT ON Instructors
BEGIN
    INSERT INTO Instructors_Log(InstructorsLog_DateTime,
                            InstructorsLog_Type,
                            InstructorsLog_InstructorId,
                            InstructorsLog_InstructorName)
    values(datetime('now'),
           'inserted',
           NEW.InstructorId,
           NEW.InstructorName);
END;

CREATE TRIGGER trg_after_update_instructors
AFTER UPDATE ON Instructors
BEGIN
    INSERT INTO Instructors_Log(InstructorsLog_DateTime,
                            InstructorsLog_Type,
                            InstructorsLog_InstructorId,
                            InstructorsLog_InstructorName)
    values(datetime('now'),
           'updated_OLD_values',
           OLD.InstructorId,
           OLD.InstructorName);

    INSERT INTO Instructors_Log(InstructorsLog_DateTime,
                            InstructorsLog_Type,
                            InstructorsLog_InstructorId,
                            InstructorsLog_InstructorName)
    values(datetime('now'),
           'updated_NEW_values',
           NEW.InstructorId,
           NEW.InstructorName);
END;

END TRANSACTION;

﻿DELETE FROM Courses where CourseCareer <> 'UGRD' or CourseSubject = 'ISP';

DELETE FROM Courses where CourseSubject = 'MATH';

INSERT INTO Courses values(2, 'MATH', 203, 'UGRD', 'CS', 'linear algebra', 'hurray');

UPDATE Courses
SET CourseDescr2 = 'yasasin'
WHERE CourseId = 2;

UPDATE Courses
SET CourseCareer = 'newCourseCareerType'
WHERE CourseId = 2;

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

select
SUBSTR(i.InstructorName, INSTR(i.InstructorName, ',') + 1, LENGTH(i.InstructorName) - INSTR(i.InstructorName, ','))
|| ' ' ||
SUBSTR(i.InstructorName, 0, INSTR(i.InstructorName, ','))
as "Course Instructor First & Last Name",
c.CourseSubject || ' ' || c.CourseCatalog as "Course Name",
c.CourseCareer as "Course Level",
c.CourseAcadOrg as "Course Faculty",
c.CourseDescr as "Course Description"
from Classes cl
inner join Class_Course_Infos cci on cci.ClassId = cl.ClassId
inner join Courses c on c.CourseId = cci.CourseId
inner join Class_Instructor_Infos cii on cii.ClassId = cl.ClassId
inner join Instructors i on cii.InstructorId = i.InstructorId
where i.InstructorName like '%NIKOS';

CREATE VIEW CoursesInformation_of_Instructors_Whose_FirstAndLastNamesStartingWithLetterA AS
SELECT
SUBSTR(i.InstructorName, INSTR(i.InstructorName, ',') + 1, LENGTH(i.InstructorName) - INSTR(i.InstructorName, ','))
|| ' ' ||
SUBSTR(i.InstructorName, 0, INSTR(i.InstructorName, ','))
as "Course Instructor First & Last Name",
c.CourseSubject || ' ' || c.CourseCatalog as "Course Name",
c.CourseCareer as "Course Level",
c.CourseAcadOrg as "Course Faculty",
c.CourseDescr as "Course Description"
from Classes cl
inner join Class_Course_Infos cci on cci.ClassId = cl.ClassId
inner join Courses c on c.CourseId = cci.CourseId
inner join Class_Instructor_Infos cii on cii.ClassId = cl.ClassId
inner join Instructors i on cii.InstructorId = i.InstructorId
where
(SUBSTR
(i.InstructorName,
INSTR(i.InstructorName, ',') + 1,
LENGTH(i.InstructorName) - INSTR(i.InstructorName, ','))
like
'A%')
AND
(SUBSTR
(i.InstructorName,
0,
INSTR(i.InstructorName, ','))
like
'A%')
AND
(cii.ClassInstructorRole = 'SI' or cii.ClassInstructorRole = 'PI')
group by c.CourseId
order by "Course Instructor First & Last Name", "Course Name", "Course Description";
--Create a view for the following query:
	--Show instructor name in first&last fashion, course level, course faculty, course description
	--Show the entries for the instructors who not only assume the role of either PI or SI
	--but also have their first and last names starting with letter 'A'
	--group same courses together