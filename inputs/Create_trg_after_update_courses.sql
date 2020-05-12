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