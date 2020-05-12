CREATE TABLE IF NOT EXISTS Courses_Log(
    CoursesLog_LogId INTEGER   NOT NULL
                               PRIMARY KEY AUTOINCREMENT,
    CoursesLog_DateTime DATETIME NOT NULL,
    CoursesLog_Type  NVARCHAR(8) NOT NULL
                     CHECK(CoursesLog_Type = 'deleted'
                     OR CoursesLog_Type = 'inserted'
                     OR CoursesLog_Type = 'updated_OLD_values'
                     OR CoursesLog_Type = 'updated_NEW_values'),
    CoursesLog_CourseId INTEGER(4) NOT NULL,
    CoursesLog_CourseSubject NVARCHAR(4) NOT NULL,
    CoursesLog_CourseCatalog INTEGER(3) NOT NULL,
    CoursesLog_CourseCareer NVARCHAR(4) NOT NULL,
    CoursesLog_CourseAcadOrg NVARCHAR(7) NOT NULL,
    CoursesLog_CourseDescr NVARCHAR(100) NOT NULL,
    CoursesLog_CourseDescr2 NVARCHAR(100) NOT NULL);