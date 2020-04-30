CREATE TABLE IF NOT EXISTS Courses(

	CourseId INTEGER(4)         NOT NULL,
    CourseSubject NVARCHAR(4) NOT NULL,
    CourseCatalog INTEGER(3) NOT NULL,
    CourseCareer NVARCHAR(4) NOT NULL,
    CourseAcadOrg NVARCHAR(7) NOT NULL,
    CourseDescr NVARCHAR(100) NOT NULL,
    CourseDescr2 NVARCHAR(100) NOT NULL,

    PRIMARY KEY(CourseId)

);

