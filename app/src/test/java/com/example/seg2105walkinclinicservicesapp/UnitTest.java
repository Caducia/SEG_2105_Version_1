package com.example.seg2105walkinclinicservicesapp;

import com.example.seg2105walkinclinicservicesapp.Timetable.Week;

import org.junit.Test;

import java.sql.Time;
import java.time.LocalDate;

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
        LocalDate date = LocalDate.now();

        boolean actual = t.areSlotsAvailable(date.minusDays(1),1,6);
        boolean expected = true;

        assertEquals(actual,expected);
    }
    @Test
    public void areSlotsAvalible_isCorrect2(){
        Timetable t = new Timetable();

        boolean actual = t.areSlotsAvailable(LocalDate.now(),1,7);
        boolean expected = true;

        assertEquals(actual,expected);
    }
    @Test
    public void areSlotsAvalible_isCorrect3(){
        Timetable t = new Timetable();

        boolean actual = t.areSlotsAvailable(LocalDate.now(),1,8);
        boolean expected = true;

        assertEquals(actual,expected);
    }
    @Test
    public void areSlotsAvalible_isCorrect4(){
        Timetable t = new Timetable();

        boolean actual = t.areSlotsAvailable(LocalDate.now(),1,9);
        boolean expected = true;

        assertEquals(actual,expected);
    }
    @Test
    public void areSlotsAvalible_isCorrect5(){
        Timetable t = new Timetable();

        boolean actual = t.areSlotsAvailable(LocalDate.now(),1,10);
        boolean expected = true;

        assertEquals(actual,expected);
    }
    @Test
    public void reserveSlots_isCorrect(){
        Timetable t = new Timetable();
        boolean actual = t.reserveSlots(LocalDate.now(),1,6,"Tester1");
        boolean expected = true;

        assertEquals(actual,expected);
    }
    @Test
    public void reserveSlots_isCorrect2(){
        Timetable t = new Timetable();
        boolean actual = t.reserveSlots(LocalDate.now(),1,7,"Tester1");
        boolean expected = true;

        assertEquals(actual,expected);
    }
    @Test
    public void reserveSlots_isCorrect3(){
        Timetable t = new Timetable();

        boolean actual = t.reserveSlots(LocalDate.now(),1,8,"Tester1");
        boolean expected = true;

        assertEquals(actual,expected);
    }

    @Test
    public void avalibleSlots(){
        Timetable.Week t = new Timetable().new Week(5);

        boolean actual = t.areSlotsAvailable(4,7,9);
        boolean expected = true;

        assertEquals(actual,expected);
    }

    @Test
    public void avalibleSlots1(){
        Timetable.Week t = new Timetable().new Week(5);

        boolean actual = t.areSlotsAvailable(4,7,10);
        boolean expected = true;

        assertEquals(actual,expected);
    }








}
