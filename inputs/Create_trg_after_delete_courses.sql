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