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
}
