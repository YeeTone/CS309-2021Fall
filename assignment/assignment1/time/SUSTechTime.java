package assignment1.time;

public class SUSTechTime {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    public SUSTechTime(){
        this.year = 1;
        this.month = 1;
        this.day = 1;
        this.hour = 0;
        this.minute = 0;
    }

    public boolean isSameDay(SUSTechTime t){
        return (this.year == t.year) && (this.month == t.month) && (this.day == t.day);
    }

    public boolean isRemindTime(){
        return (this.minute==30) && (this.hour == 12 || this.hour == 16);
    }

    public boolean isSendTime(){
        return (this.minute == 0) && (this.hour == 20);
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    @Override
    public String toString() {
        return "Time{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", hour=" + hour +
                ", minute=" + minute +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
