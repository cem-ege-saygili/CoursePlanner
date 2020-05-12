CREATE TRIGGER trg_abort_insert_to_Courses --before_insert_trigger
BEFORE INSERT ON Courses
WHEN(NEW.CourseCareer <> 'UGRD' AND
    NEW.CourseCareer <> 'MAST' AND
    NEW.CourseCareer <> 'PHD' AND
    NEW.CourseCareer <> 'EEDU')
BEGIN
    SELECT
    RAISE(ABORT,
    '
    Insert Aborted due to unacceptable CourseCareer:
    Applicable course levels are as follows:
    UGRD, MAST, PHD and EEDU');
END;