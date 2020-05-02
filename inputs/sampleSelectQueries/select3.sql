SELECT 
SUBSTR(ClassFacilID,0,4) as 'Location',
CASE
    WHEN 
    (ClassMon = 'Y' and ClassTues = 'N' and ClassWed = 'Y' and ClassThurs = 'N' and ClassFri = 'N')
    THEN 'Monday & Wednesday'
    WHEN 
    (ClassMon = 'N' and ClassTues = 'Y' and ClassWed = 'N' and ClassThurs = 'Y' and ClassFri = 'N')
    THEN 'Tuesday & Thursday'
END AS "On Days",
count(*) as "Number of Classes Given"
from Classes
where ClassFacilID like 'SNA%'
group by ClassMon, ClassTues, ClassWed, ClassThurs, ClassFri
having 
(ClassMon = 'Y' and ClassTues = 'N' and ClassWed = 'Y' and ClassThurs = 'N' and ClassFri = 'N')
or
(ClassMon = 'N' and ClassTues = 'Y' and ClassWed = 'N' and ClassThurs = 'Y' and ClassFri = 'N')
order by "On Days";
--Show the location, on days and number of classes given
--for the entries whose days are either Mon&Wed or Tues&Thurs
--and whose location is any class room in SNA building.