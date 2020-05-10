package model;

public class ScheduleListComboboxItem {

    Schedule schedule;
    int insertedItemCount;
    public ScheduleListComboboxItem(int insertedItemCount, Schedule schedule){
        this.schedule = schedule;
        this.insertedItemCount = insertedItemCount;
    }

    @Override
    public String toString() {
        int curItemNo = insertedItemCount+1;
        return "Plan #" + curItemNo;
    }

    public Schedule getSchedule(){
        return schedule;
    }
}
