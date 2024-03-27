package edu.gcc.comp350.smartest;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SaveAndLoadFileTest {
    @Test
    void ReadFromFile() throws IOException {
        //setup
        Search.ParseClasses();
        Main.mainUser = new User();
        Course course1 = new Course();
        Course.database.add(course1);
        //2020,10,ACCT,201,A,PRINCIPLES OF ACCOUNTING I,3,30,30,M,,W,,F,9:00:00 AM,9:50:00 AM,Stone,Jennifer,Nicole,Online materials fee
        Course course2 = Course.findCourse("ACCT201A");
        //2020,30,COMP,233,A,PARALLEL COMPUTING,3,50,49,M,,W,,F,11:00:00 AM,11:50:00 AM,Valentine,David,,COMP 220; CSCI/CSBA Maj onlyCourse course2 = Course.findCourse("ACCT201A");
        Course course3 = Course.findCourse("COMP233A");
        Schedule schedule = new Schedule();
        schedule.addCourse(course1);
        schedule.addCourse(course2);
        schedule.addCourse(course3);
        Main.mainUser.savedSchedules.add(schedule);

        //test
        Main.mainUser.SaveToFile();
        Main.mainUser = null;
        User.LoadCoursesFromFile();
        assertEquals(0, Main.mainUser.getUserID());
        assertEquals("John Student", Main.mainUser.getName());
        assertEquals("Computer Science", Main.mainUser.getMajor());
        assertEquals(1, Main.mainUser.savedSchedules.size());
        ArrayList<Course> scheduleCourses = Main.mainUser.savedSchedules.getFirst().getCurrentCourses();
        assertEquals(3, scheduleCourses.size());
        assertEquals("TEST000A", scheduleCourses.getFirst().getCourseCode());
        assertEquals("PRINCIPLES OF ACCOUNTING I", scheduleCourses.get(1).getName());
        assertEquals("David Valentine", scheduleCourses.get(2).getProfessor());
    }
}
