CREATE TABLE IF NOT EXISTS Courses(

	CourseId INTEGER(4)         NOT NULL
                              UNIQUE
                              CHECK (CourseId>=0),
    CourseSubject NVARCHAR(4) NOT NULL
                              CHECK (LENGTH(CourseSubject)=3 or LENGTH(CourseSubject)=4),
    CourseCatalog INTEGER(3) NOT NULL
                             CHECK (LENGTH(CourseCatalog)=3),
    CourseCareer NVARCHAR(4) NOT NULL
                             CHECK (CourseCareer = 'UGRD' or CourseCareer = 'MAST' or CourseCareer = 'PHD' or CourseCareer = 'EEDU'),
    CourseAcadOrg NVARCHAR(7) NOT NULL
                              CHECK (CourseAcadOrg = 'CSSH' or CourseAcadOrg = 'GSSSH' or CourseAcadOrg = 'CS' or CourseAcadOrg = 'GSSE' or CourseAcadOrg = 'CE' or CourseAcadOrg = 'LAW' or  CourseAcadOrg = 'GSB' or CourseAcadOrg = 'CASE' or CourseAcadOrg = 'DOS' or CourseAcadOrg = 'GSHS' or CourseAcadOrg = 'SOM' or CourseAcadOrg = 'ELC' or CourseAcadOrg = 'NURSING'),
    CourseDescr NVARCHAR(100) NOT NULL,
    CourseDescr2 NVARCHAR(100) NOT NULL,

    PRIMARY KEY(CourseId)

);

