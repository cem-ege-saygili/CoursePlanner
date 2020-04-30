CREATE TABLE IF NOT EXISTS Classes(

    ClassId INTEGER             NOT NULL
                                UNIQUE
                                PRIMARY KEY
                                AUTOINCREMENT,
	--ClassNbr INTEGER(4)	        NOT NULL,
    --ClassPatNbr INTEGER(2)      NOT NULL,
    --ClassCourseId INTEGER(4),
    ClassComponent NVARCHAR(3) NOT NULL
                                CHECK (ClassComponent = 'LEC' or ClassComponent = 'PRB' or ClassComponent = 'DIS' or ClassComponent = 'LAB'),
    ClassSection NVARCHAR(4) NOT NULL,
    --ClassInstructorRole NVARCHAR(2),
    --ClassInstructorName NVARCHAR(100),
    ClassMtgStart NVARCHAR(11) NOT NULL
                               CHECK (ClassMtgStart like '%:%:% AM'  or ClassMtgStart like '%:%:% PM'),
    ClassMtgEnd NVARCHAR(11) NOT NULL
                             CHECK (ClassMtgEnd like '%:%:% AM'  or ClassMtgEnd like '%:%:% PM'),
    ClassMon NVARCHAR(1) NOT NULL
                         CHECK (ClassMon = 'Y' or ClassMon = 'N'),
    ClassTues NVARCHAR(1) NOT NULL
                          CHECK (ClassTues = 'Y' or ClassTues = 'N'),
    ClassWed NVARCHAR(1) NOT NULL
                          CHECK (ClassWed = 'Y' or ClassWed = 'N'),
    ClassThurs NVARCHAR(1) NOT NULL
                           CHECK (ClassThurs = 'Y' or ClassThurs = 'N'),
    ClassFri NVARCHAR(1) NOT NULL
                         CHECK (ClassFri = 'Y' or ClassFri = 'N'),
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

