package com.example.seg2105walkinclinicservicesapp;

import org.junit.Test;

import java.sql.Time;

import static org.junit.Assert.*;


public class UnitTest {
    @Test

    public void clinicID_isCorrect() {

    }
    public void loginUser_isCorrect() {

    }
    public void registerUser_isCorrect() {

    }
    public void setPatientSignup_isCorrect() {

    }
    public void setClinicSignup_isCorrect() {

    }

    public void areSlotsAvalible_isCorrect1(){
        Timetable t = new Timetable();

        boolean actual = t.slotsOK(4,5);
        boolean expected = true;

        assertEquals(actual,expected);
    }
    @Test
    public void areSlotsAvalible_isCorrect2(){
        Timetable t = new Timetable();

        boolean actual = t.slotsOK(4,5);
        boolean expected = true;

        assertEquals(actual,expected);
    }
    @Test
    public void areSlotsAvalible_isCorrect3(){
        Timetable t = new Timetable();

        boolean actual = t.slotsOK(4,6);
        boolean expected = true;

        assertEquals(actual,expected);
    }
    @Test
    public void areSlotsAvalible_isCorrect4(){
        Timetable t = new Timetable();

        boolean actual = t.slotsOK(4,7);
        boolean expected = true;

        assertEquals(actual,expected);
    }
    @Test
    public void areSlotsAvalible_isCorrect5(){
        Timetable t = new Timetable();

        boolean actual = t.slotsOK(4,5);
        boolean expected = true;

        assertEquals(actual,expected);
    }
    @Test
    public void reserveSlots_isCorrect(){
        Timetable t = new Timetable();
        boolean actual = t.rSlots(4,5,"Tester1");
        boolean expected = true;

        assertEquals(actual,expected);
    }
    @Test
    public void reserveSlots_isCorrect2(){
        Timetable t = new Timetable();
        boolean actual = t.rSlots(4,6,"Tester1");
        boolean expected = true;

        assertEquals(actual,expected);
    }
    @Test
    public void reserveSlots_isCorrect3(){
        Timetable t = new Timetable();

        boolean actual = t.rSlots(4,7,"Tester1");
        boolean expected = true;

        assertEquals(actual,expected);
    }





}
