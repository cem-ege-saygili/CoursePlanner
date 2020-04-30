CREATE TABLE IF NOT EXISTS Instructors(

	InstructorId INTEGER        NOT NULL
							 								UNIQUE
	                            PRIMARY KEY
	                            AUTOINCREMENT,

    InstructorName NVARCHAR(100) NOT NULL

);