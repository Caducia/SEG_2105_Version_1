package com.example.seg2105walkinclinicservicesapp;

public class Timetable {
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
    }
    public boolean slotsOK(int t1, int t2){
        Day d = new Day();
       return d.areSlotsAvailable(t1,t2);
    }

    public boolean rSlots(int t1, int t2, String id){
        Day d = new Day();
        return d.reserveSlots(t1,t2,id);
    }
}
