CREATE TABLE IF NOT EXISTS Instructors_Log(
    InstructorsLog_LogId INTEGER   NOT NULL
                                   PRIMARY KEY AUTOINCREMENT,
    InstructorsLog_DateTime DATETIME NOT NULL,
    InstructorsLog_Type  NVARCHAR(8) NOT NULL
                           CHECK(InstructorsLog_Type = 'deleted'
                           OR InstructorsLog_Type = 'inserted'
                           OR InstructorsLog_Type = 'updated_OLD_values'
                           OR InstructorsLog_Type = 'updated_NEW_values'),
    InstructorsLog_InstructorId INTEGER NOT NULL,
    InstructorsLog_InstructorName NVARCHAR(100) NOT NULL);