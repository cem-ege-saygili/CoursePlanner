DELETE FROM CoursePlannerBIGGEST
where MtgEnd = '' or MtgStart ='' 
                  or (Mon = 'N' and Tues = 'N' and Wed = 'N' and Thurs = 'N'and Fri = 'N');