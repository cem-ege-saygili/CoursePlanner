package deprecated;

public class ClassTime {

    private int hour;
    private int minute;

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public ClassTime(int hour, int minute){
        this.hour=hour;
        this.minute=minute;
    }

}
