CREATE TABLE IF NOT EXISTS Classes(

    ClassId INTEGER             NOT NULL
                                PRIMARY KEY
                                AUTOINCREMENT,
	--ClassNbr INTEGER(4)	        NOT NULL,
    --ClassPatNbr INTEGER(2)      NOT NULL,
    --ClassCourseId INTEGER(4),
    ClassComponent NVARCHAR(3) NOT NULL,
    ClassSection NVARCHAR(4) NOT NULL,
    --ClassInstructorRole NVARCHAR(2),
    --ClassInstructorName NVARCHAR(100),
    ClassMtgStart NVARCHAR(11) NOT NULL,
    ClassMtgEnd NVARCHAR(11) NOT NULL,
    ClassMon NVARCHAR(1) NOT NULL,
    ClassTues NVARCHAR(1) NOT NULL,
    ClassWed NVARCHAR(1) NOT NULL,
    ClassThurs NVARCHAR(1) NOT NULL,
    ClassFri NVARCHAR(1) NOT NULL,
    --ClassTotEnrl INTEGER(3),
    --ClassCapEnrl INTEGER(3),
    ClassFacilID NVARCHAR(100) NOT NULL
    --,

    --FOREIGN KEY (
  	--			ClassCourseId
  	--    	  )
    --REFERENCES Courses(CourseId)	            ON DELETE NO ACTION
  	--											ON UPDATE NO ACTION
);

