CREATE TABLE IF NOT EXISTS Classes_Log(
    ClassesLog_LogId INTEGER   NOT NULL
                               PRIMARY KEY AUTOINCREMENT,
    ClassesLog_DateTime DATETIME NOT NULL,
    ClassesLog_Type  NVARCHAR(8) NOT NULL
                           CHECK(ClassesLog_Type = 'deleted'
                           OR ClassesLog_Type = 'inserted'
                           OR ClassesLog_Type = 'updated_OLD_values'
                           OR ClassesLog_Type = 'updated_NEW_values'),
    ClassesLog_ClassId INTEGER NOT NULL,
    ClassesLog_ClassComponent NVARCHAR(3) NOT NULL,
    ClassesLog_ClassSection NVARCHAR(4) NOT NULL,
    ClassesLog_ClassMtgStart NVARCHAR(11) NOT NULL,
    ClassesLog_ClassMtgEnd NVARCHAR(11) NOT NULL,
    ClassesLog_ClassMon NVARCHAR(1) NOT NULL,
    ClassesLog_ClassTues NVARCHAR(1) NOT NULL,
    ClassesLog_ClassWed NVARCHAR(1) NOT NULL,
    ClassesLog_ClassThurs NVARCHAR(1) NOT NULL,
    ClassesLog_ClassFri NVARCHAR(1) NOT NULL,
    ClassesLog_ClassFacilID NVARCHAR(100) NOT NULL);