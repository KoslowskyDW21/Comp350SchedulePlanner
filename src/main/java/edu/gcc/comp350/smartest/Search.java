package edu.gcc.comp350.smartest;
import java.util.ArrayList;

public class Search {
    private String userInput;
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

    public void addFilter(Filter filter) {
        activeFilters = filter;
        parseDatabase();
    }

    public void removeFilter(Filter filter){

        parseDatabase();
    }

    public ArrayList<Course> parseDatabase() {
        for (Course course : Course.database) {
            //if (course.)
        }
        return null;
    }

}
