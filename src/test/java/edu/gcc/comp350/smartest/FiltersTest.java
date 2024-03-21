package edu.gcc.comp350.smartest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

public class FiltersTest {
    @Test
    public void testAddDepartment() throws IOException {
        Main.ParseClasses();
        Search search = new Search();

        String department = "COMP";
        Main.addDepartmentFilter(search, department);
        ArrayList<Course> courses;
        courses = search.getResults();

        for(Course course : courses) {
            assertEquals(department, course.getDepartment());
        }

        department = "ACCT";
        Main.addDepartmentFilter(search, department);
        courses.clear();
        courses = search.getResults();

        for(Course course : courses) {
            assertEquals(department, course.getDepartment());
        }

        department = "RIZZ";
        Main.addDepartmentFilter(search, department);
        courses.clear();
        courses = search.getResults();

        assertEquals(0, courses.size());
    }

    @Test
    public void testRemoveDepartment() throws IOException {
        Main.ParseClasses();
        Search search = new Search();

        String department = "COMP";
        Main.addDepartmentFilter(search, department);
        ArrayList<Course> courses;

        Main.removeDepartmentFilter(search);
        courses = search.getResults();

        assertEquals(1494, courses.size());
    }
}
