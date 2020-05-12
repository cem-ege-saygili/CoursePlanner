CREATE TRIGGER trg_abort_update_to_Courses --before_update_trigger
BEFORE UPDATE ON Courses
WHEN(NEW.CourseCareer <> 'UGRD' AND
    NEW.CourseCareer <> 'MAST' AND
    NEW.CourseCareer <> 'PHD' AND
    NEW.CourseCareer <> 'EEDU')
BEGIN
    SELECT
    RAISE(ABORT,
    '
    Update Aborted due to unacceptable CourseCareer:
    Applicable course levels are as follows:
    UGRD, MAST, PHD and EEDU');
END;