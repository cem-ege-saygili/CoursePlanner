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