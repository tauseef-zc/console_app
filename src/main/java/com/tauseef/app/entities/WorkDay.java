package com.tauseef.app.entities;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class WorkDay {

    private DayOfWeek day;
    private LocalTime startTime;
    private LocalTime endTime;

    public WorkDay(DayOfWeek day, LocalTime startTime, LocalTime endTime) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return "WorkDay [day=" + day + ", startTime=" + startTime + ", endTime=" + endTime + "]";
    }

}
