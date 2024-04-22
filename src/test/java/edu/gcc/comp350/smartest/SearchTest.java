//package edu.gcc.comp350.smartest;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.Assertions.*;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.lang.reflect.Array;
//import java.util.ArrayList;
//import java.io.InputStream;
//import java.util.Scanner;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class SearchTest {
//    @Test
//    void testConverterWorks() {
//        Search search = new Search();
//        String ogInput = "Hello World!";
//        search.modifyQuery(ogInput);
//        Assertions.assertEquals("helloworld", search.getUserInput());
//    }
//
//    @Test
//    void testConverterDidNothing() {
//        Search search = new Search();
//        String ogInput = "Hello World!";
//        search.modifyQuery(ogInput);
//        Assertions.assertNotEquals("Hello World!", search.getUserInput());
//    }
//
//    @Test
//    void testConverterTookSpaces() {
//        Search search = new Search();
//        String ogInput = "Hello World!";
//        search.modifyQuery(ogInput);
//        Assertions.assertNotEquals("HelloWorld!", search.getUserInput());
//    }
//
//    @Test
//    void testConverterTookCapitals() {
//        Search search = new Search();
//        String ogInput = "Hello World!";
//        search.modifyQuery(ogInput);
//        assertNotEquals("hello world!", search.getUserInput());
//    }
//
//    @Test
//    void testConverterTookPunctuation() {
//        Search search = new Search();
//        String ogInput = "Hello World!";
//        search.modifyQuery(ogInput);
//        Assertions.assertNotEquals("Hello World", search.getUserInput());
//    }
//
//    @Test
//    void testCourseNameSearch() {
//        Search search = new Search();
//        String ogInput = "Automata";
//        search.modifyQuery(ogInput);
//        ArrayList<Course> temp = search.getResults();
//        for (Course course : temp) {
//            Assertions.assertEquals(search.getUserInput(), course.getName());
//            //System.out.println(course.getName());
//            //System.console().printf(course.getName());
//        }
//    }
//
//    @Test
//    void courseDoesNotExist() {
//        Search search = new Search();
//        String ogInput = "i aM nOt a coUrSe!!";
//
//        search.modifyQuery(ogInput);
//        assertTrue(search.getResults().isEmpty());
//    }
//
//    @Test
//    void courseDoesExist() throws IOException {
//        Search search = new Search();
//        Search.ParseClasses();
//
//        String query ="COMP141";
//        search.modifyQuery(query);
//        assertTrue(search.resultsToString().contains(query));
//    }
//
//}
