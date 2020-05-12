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