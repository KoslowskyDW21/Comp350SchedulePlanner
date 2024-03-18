package edu.gcc.comp350.smartest;
import java.util.ArrayList;
import java.io.*;

public class Search {
    private String userInput;
    // TODO: get rid of spaces
    private ArrayList<Course> results;
    private Filter activeFilters;

    public Search() {
        this.userInput = "";
        this.results = new ArrayList<Course>();
        this.activeFilters = new Filter();
    }

    public String getUserInput() {
        return userInput;
    }

    public ArrayList<Course> getResults() {
        return results;
    }

    public Filter getActiveFilters() {
        return activeFilters;
    }

    public void modifyQuery(String input) {
        userInput = input;
        parseDatabase();
    }



    public void modifyFilter(Filter filter) {
        activeFilters = filter;
        parseDatabase();
    }


    private ArrayList<Course> parseDatabase() {
        results.clear();

        for (Course course : Course.database) {
            if (course.getCourseCode().contains(userInput) // search by code
                    || course.getName().contains(userInput)) { // search by name
                results.add(course);
            }
            //else if (course)
        }
        for(Course result: results) {
            String department = activeFilters.getDepartment();
            if(department.isEmpty() && result.getDepartment().equals(department)) {
                results.remove(result);
            }
        }

        return results;
    }

}
