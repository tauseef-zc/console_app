package com.tauseef.app.entities;

import com.tauseef.app.TestCase;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class WorkDayTest extends TestCase {

    @Test
    void getDay() {
        DayOfWeek day = DayOfWeek.MONDAY;
        WorkDay workDay = createWorkDay(day, null, null);
        assertEquals(day, workDay.getDay());
    }

    @Test
    void getStartTime() {
        LocalTime startTime = LocalTime.of(8, 0);
        WorkDay workDay = createWorkDay(null, startTime, null);
        assertEquals(startTime, workDay.getStartTime());
    }

    @Test
    void getEndTime() {
        LocalTime endTime = LocalTime.of(8, 0);
        WorkDay workDay = createWorkDay(null, null, endTime);
        assertEquals(endTime, workDay.getEndTime());
    }
}