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