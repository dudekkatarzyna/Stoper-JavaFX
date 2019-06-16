package sample.java;

import java.time.LocalTime;

public class Record {
    private String name;
    private LocalTime start;
    private LocalTime finish;
    private String time;

    public Record(String name, LocalTime start, LocalTime finish, String time) {
        this.name = name;
        this.start = start;
        this.finish = finish;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getFinish() {
        return finish;
    }

    public void setFinish(LocalTime finish) {
        this.finish = finish;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
