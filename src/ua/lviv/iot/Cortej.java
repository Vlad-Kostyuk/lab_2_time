package ua.lviv.iot;

public class Cortej {
   
    private long hour;
    private long minute;

    public Cortej() {
    }

    public Cortej(long hour, long minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public long getHour() {
        return hour;
    }

    public long getMinute() {
        return minute;
    }

    @Override
    public String toString() {
        return "(" + this.hour + "; " + this.minute + ")";
    }
}
