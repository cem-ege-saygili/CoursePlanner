CREATE TABLE IF NOT EXISTS Class_Instructor_Infos(

	Class_Instructor_Info_Id    INTEGER
	                            NOT NULL
															--UNIQUE
	                            PRIMARY KEY
	                            AUTOINCREMENT,

    ClassId                     INTEGER(4) NOT NULL,
    InstructorId                INTEGER(4) NOT NULL,
    ClassInstructorRole         NVARCHAR(2) NOT NULL
																					  CHECK (ClassInstructorRole = 'PI' or ClassInstructorRole = 'SI' or ClassInstructorRole = 'TA'),

    FOREIGN KEY (
      				ClassId
      			)
    REFERENCES Classes(ClassId),
    FOREIGN KEY (
          		    InstructorId
          	    )
    REFERENCES Instructors(InstructorId)

);