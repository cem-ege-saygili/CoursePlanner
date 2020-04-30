CREATE TABLE IF NOT EXISTS Class_Instructor_Infos(

	Class_Instructor_Info_Id    INTEGER
	                            NOT NULL
	                            PRIMARY KEY
	                            AUTOINCREMENT,

    ClassId                     INTEGER(4) NOT NULL,
    InstructorId                INTEGER(4) NOT NULL,
    ClassInstructorRole         NVARCHAR(2) NOT NULL,

    FOREIGN KEY (
      				ClassId
      			)
    REFERENCES Classes(ClassId)     ON DELETE NO ACTION
      							    ON UPDATE NO ACTION,
    FOREIGN KEY (
          		    InstructorId
          	    )
    REFERENCES Instructors(InstructorId)     ON DELETE NO ACTION
          							         ON UPDATE NO ACTION

);