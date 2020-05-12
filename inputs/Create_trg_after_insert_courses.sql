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