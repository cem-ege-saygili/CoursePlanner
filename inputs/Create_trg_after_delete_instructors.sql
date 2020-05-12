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