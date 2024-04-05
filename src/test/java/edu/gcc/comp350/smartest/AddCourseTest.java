package edu.gcc.comp350.smartest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddCourseTest {

    @Test
    public void addOneCourse(){
        Course course = new Course("2020,10,ACCT,201,A,PRINCIPLES OF ACCOUNTING I,3,30,30,M,,W,,F,9:00:00 AM,9:50:00 AM,Stone,Jennifer,Nicole,Online materials fee");
        Schedule s = new Schedule();
        s.addCourse(course);
        assertEquals(course, s.getCurrentCourses().getFirst());
    }

    @Test
    public void addSameCourse() {
        Course course = new Course("2020,10,ACCT,201,A,PRINCIPLES OF ACCOUNTING I,3,30,30,M,,W,,F,9:00:00 AM,9:50:00 AM,Stone,Jennifer,Nicole,Online materials fee");
        Course course2 = new Course("2020,10,ACCT,201,A,PRINCIPLES OF ACCOUNTING I,3,30,30,M,,W,,F,9:00:00 AM,9:50:00 AM,Stone,Jennifer,Nicole,Online materials fee");
        Schedule s = new Schedule();
        s.addCourse(course);
        assertEquals(1, s.addCourse(course2));
    }

    @Test
    public void addDiffCourse() {
        Course course = new Course("2020,10,ACCT,201,A,PRINCIPLES OF ACCOUNTING I,3,30,30,M,,W,,F,9:00:00 AM,9:50:00 AM,Stone,Jennifer,Nicole,Online materials fee");
        Course course2 = new Course("2020,10,ACCT,201,B,PRINCIPLES OF ACCOUNTING I,3,30,32,M,,W,,F,11:00:00 AM,11:50:00 AM,Snyder,Richard,,Online materials fee");
        Schedule s = new Schedule();
        s.addCourse(course);
        s.addCourse(course2);
        assertEquals(2, s.getCurrentCourses().size());
    }

    @Test
    public void addOverlapCourse() {
        Course course = new Course("2020,10,BIOL,101,L,LABORATORY,0,28,29,M,,,,,2:00:00 PM,4:59:00 PM,Antoszewski,Lisa,,LAB FEE");
        Course course2 = new Course("2020,30,ACCT,202,E,PRINCIPLES OF ACCOUNTING II,3,30,28,M,,W,,F,3:00:00 PM,3:50:00 PM,Snyder,Richard,,ACCT 201; Online Materials Fee");
        Schedule s = new Schedule();
        s.addCourse(course);
        assertEquals(1, s.addCourse(course2));
    }

    @Test
    public void overlapTimeDifferentDays() {
        Course course = new Course("2020,30,ACCT,202,A,PRINCIPLES OF ACCOUNTING II,3,30,30,M,,W,,F,9:00:00 AM,9:50:00 AM,Stone,Jennifer,Nicole,ACCT 201; Online Materials Fee");
        Course course2 = new Course("2020,10,ACCT,303,A,COST ACCOUNTING,3,35,27,,T,,R,,8:00:00 AM,9:15:00 AM,McFeaters,Michelle,,ACCT 202");
        Schedule s = new Schedule();
        s.addCourse(course);
        assertEquals(0, s.addCourse(course2));
    }

    @Test
    public void OverlapTimeOneDay(){
        Course course = new Course("2020,30,BIOL,102,N,LABORATORY,0,28,28,,T,,,,2:30:00 PM,5:29:00 PM,Dudt,Jan,,LAB FEE");
        Course course2 = new Course("2020,10,ART,104,B,CERAMICS I WHEEL,3,12,12,,T,,R,,2:30:00 PM,3:45:00 PM,Rhoades,Kathy,,FEE REQUIRED");
        Schedule s = new Schedule();
        s.addCourse(course);
        assertEquals(1, s.addCourse(course2));
    }
}




