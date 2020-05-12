CREATE TRIGGER trg_before_delete_class
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