package edu.gcc.comp350.smartest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

public class SearchTest {
    @Test
    void testInput() {
        Search search = new Search();
        String ogInput = "Hello World!";
        search.modifyQuery(ogInput);
        Assertions.assertEquals("helloworld!", search.getUserInput());
        Assertions.assertNotEquals("Hello World!", search.getUserInput());
        Assertions.assertNotEquals("HelloWorld!", search.getUserInput());
        Assertions.assertNotEquals("hello world!", search.getUserInput());
        Assertions.assertNotEquals("helloworld", search.getUserInput());
    }

    @Test
    void testCourseNameSearch() {
        Search search = new Search();
        String ogInput = "Automata";
        search.modifyQuery(ogInput);
        ArrayList<Course> temp = search.getResults();
        for (Course course : temp) {
            Assertions.assertEquals(search.getUserInput(), course.getName());
            //System.out.println(course.getName());
            //System.console().printf(course.getName());
        }
    }
}
