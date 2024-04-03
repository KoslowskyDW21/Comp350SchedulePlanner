package edu.gcc.comp350.smartest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

public class FiltersTest {
    @BeforeAll
    public static void parse() throws IOException {
        Search.ParseClasses();
    }

    @Test
    public void testAddDepartment() throws IOException {
        Search search = new Search();

        String department = "COMP";
        Filter.addDepartmentFilter(search, department);
        ArrayList<Course> courses;
        courses = search.getResults();

        for(Course course : courses) {
            assertEquals(department, course.getDepartment());
        }

        department = "ACCT";
        Filter.addDepartmentFilter(search, department);
        courses.clear();
        courses = search.getResults();

        for(Course course : courses) {
            assertEquals(department, course.getDepartment());
        }

        department = "FAKE";
        Filter.addDepartmentFilter(search, department);
        courses.clear();
        courses = search.getResults();

        assertEquals(0, courses.size());
    }

    @Test
    public void testRemoveDepartment() throws IOException {
        Search search = new Search();

        String department = "COMP";
        Filter.addDepartmentFilter(search, department);
        ArrayList<Course> courses;

        Filter.removeDepartmentFilter(search);
        courses = search.getResults();

        assertEquals(1494, courses.size());
    }
}
