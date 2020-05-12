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