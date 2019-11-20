package com.example.seg2105walkinclinicservicesapp;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class Timetable {

    Week firstWeek;
    Week currentWeek;
    private Week sampleWeek;

    public Timetable() {
        firstWeek = new Week(0);
        currentWeek = weekAndDayNumberAtDate(LocalDate.now()).week;
    }

    public Week getCurrentWeek() {
        return weekAndDayNumberAtDate(LocalDate.now()).week;
    }

    public boolean areSlotsAvailable(LocalDate date, int firstSlot, int lastSlot) {
        WeekAndDayNumber weekData = weekAndDayNumberAtDate(date);
        return weekData.week.areSlotsAvailable(weekData.dayNumber, firstSlot, lastSlot);
    }

    public boolean reserveSlots(LocalDate date, int firstSlot, int lastSlot, String patientID) {
        WeekAndDayNumber weekData = weekAndDayNumberAtDate(date);
        return weekData.week.reserveSlots(weekData.dayNumber, firstSlot, lastSlot, patientID);
    }

    public void freeUpSlots(LocalDate date, int firstSlot, int lastSlot) {
        WeekAndDayNumber weekData = weekAndDayNumberAtDate(date);
        weekData.week.freeUpSlots(weekData.dayNumber, firstSlot, lastSlot);
    }

    public Week getClinicOpenHours() {
        return sampleWeek;
    }

    public void setWorkingHours(Week sampleWeek) {
        this.sampleWeek = sampleWeek;
    }

    private class Day {
        private String[] slots;

        public Day() {
            slots = new String[96];
        }

        public boolean areSlotsAvailable(int firstSlot, int lastSlot) {
            for (int i = firstSlot; i < lastSlot+1; i++) {
                if (slots[i] != null) {
                    return false;
                }
            }

            return true;
        }

        public boolean reserveSlots(int firstSlot, int lastSlot, String patientID) {
            if (!areSlotsAvailable(firstSlot, lastSlot)) {
                return false;
            }

            for (int i = firstSlot; i < lastSlot+1; i++) {
                slots[i] = patientID;
            }

            return true;
        }

        public void freeUpSlots(int firstSlot, int lastSlot) {
            for (int i = firstSlot; i < lastSlot+1; i++) {
                slots[i] = null;
            }
        }
    }

    public class Week {
        private Day[] days;
        private Week nextWeek;
        private int weeksSinceJan6th2019;

        public Week(int weekNumber) {
            days = (sampleWeek != null) ? sampleWeek.days : new Day[7];
            nextWeek = null;
            weeksSinceJan6th2019 = weekNumber;
        }

        public Week nextWeek() {
            if (nextWeek == null){
                nextWeek = new Week(weeksSinceJan6th2019+1);
            }

            return nextWeek;
        }

        public boolean areSlotsAvailable(int day, int firstSlot, int lastSlot) {
            return days[day].areSlotsAvailable(firstSlot, lastSlot);
        }

        public boolean reserveSlots(int day, int firstSlot, int lastSlot, String patientID) {
            return days[day].reserveSlots(firstSlot, lastSlot, patientID);
        }

        public void freeUpSlots(int day, int firstSlot, int lastSlot) {
            days[day].freeUpSlots(firstSlot, lastSlot);
        }
    }

    private class WeekAndDayNumber {
        int dayNumber;
        Week week;

        public WeekAndDayNumber(Week week, int dayNumber) {
            this.dayNumber = dayNumber;
            this.week = week;
        }
    }

    private WeekAndDayNumber weekAndDayNumberAtDate(LocalDate date) {
        long days = daysSinceFirstDate(date);

        Week loopedWeek = firstWeek;

        long remaining = days;

        while (remaining > 0) {
            remaining -= 7;

            if (remaining > 0) {
                loopedWeek = loopedWeek.nextWeek();
            }
        }

        return new WeekAndDayNumber(loopedWeek, 7 + (int)remaining);
    }

    private long daysSinceFirstDate(LocalDate newDate) {
        LocalDate originalDate = LocalDate.of(2019,01,06);
        return ChronoUnit.DAYS.between(originalDate, newDate);
    }
}
