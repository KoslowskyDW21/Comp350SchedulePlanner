package edu.gcc.comp350.smartest;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SaveAndLoadFileTest {
    @Test
    void ReadFromFile() {
        //setup
        User.mainUser = new User();
        Course course = new Course();
        Course.database.add(course);
        Schedule schedule = new Schedule();
        schedule.addCourse(course);
        User.mainUser.savedSchedules.add(schedule);
        System.out.println("ID: " + User.mainUser.getUserID());
        System.out.println("Name: " + User.mainUser.getName());
        System.out.println("Major: " + User.mainUser.getMajor());

        //test
        User.mainUser.SaveToFile();
        User.mainUser = null;
        User.LoadCoursesFromFile();
        assertEquals(0, User.mainUser.getUserID());
        assertEquals("John Student", User.mainUser.getName());
        assertEquals("Computer Science", User.mainUser.getMajor());
        assertEquals(1, User.mainUser.savedSchedules.size());
        ArrayList<Course> scheduleCourses = User.mainUser.savedSchedules.getFirst().getCurrentCourses();
        assertEquals("000000", scheduleCourses.getFirst().getCourseCode());
    }
}
