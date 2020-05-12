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