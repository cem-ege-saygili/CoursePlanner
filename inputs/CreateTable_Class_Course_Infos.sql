CREATE TABLE IF NOT EXISTS Class_Course_Infos(

	Class_Course_Info_Id    INTEGER
	                        NOT NULL
	                        PRIMARY KEY
	                        AUTOINCREMENT,

    ClassId                     INTEGER(4) NOT NULL,
    CourseId                INTEGER(4) NOT NULL,
    ClassTotEnrl INTEGER(3) NOT NULL,
    ClassCapEnrl INTEGER(3) NOT NULL,

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