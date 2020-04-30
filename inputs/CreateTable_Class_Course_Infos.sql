CREATE TABLE IF NOT EXISTS Class_Course_Infos(

	Class_Course_Info_Id    INTEGER
	                        NOT NULL
													UNIQUE
	                        PRIMARY KEY
	                        AUTOINCREMENT,

    ClassId                     INTEGER(4) NOT NULL,
    CourseId                INTEGER(4) NOT NULL,
    ClassTotEnrl INTEGER(3) NOT NULL CHECK (ClassTotEnrl>=0),
    ClassCapEnrl INTEGER(3) NOT NULL CHECK (ClassCapEnrl>=0),

    FOREIGN KEY (
      				ClassId
      			)
    REFERENCES Classes(ClassId)     ON DELETE NO ACTION
      							    ON UPDATE NO ACTION,
    FOREIGN KEY (
          		    CourseId
          	    )
    REFERENCES Courses(CourseId)     ON DELETE NO ACTION
          							 ON UPDATE NO ACTION

);