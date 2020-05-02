SELECT "Time of Class"
FROM
(
SELECT *,
CASE
    WHEN 
        SUBSTR("Time of Class", -2, 2) = 'AM'
        AND
        SUBSTR("Time of Class", 0, 3) = '12'
    THEN 0
    WHEN 
        SUBSTR("Time of Class", -2, 2) = 'AM'
    THEN 1
    WHEN 
        SUBSTR("Time of Class", -2, 2) = 'PM'
        AND
        SUBSTR("Time of Class", 0, 3) = '12'
    THEN 2
    ELSE 3
END AS Ordering
FROM
(
SELECT ClassMtgStart as "Time of Class"
from Classes
UNION
SELECT ClassMtgEnd as "Time of Class"
from Classes
)
order by 
SUBSTR("Time of Class", -2, 2), Ordering,
LENGTH("Time of Class")
)
--Show the set all possible starting and ending times of classes
--Ordered in ascending fashion in accordance with their AM/PM values.
--(i.e. from the earliest time to the latest time of classes)