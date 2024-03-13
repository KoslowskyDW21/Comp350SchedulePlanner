package edu.gcc.comp350.smartest;
import java.util.ArrayList;

public class Search {
    private String userInput;
    private ArrayList<Course> results;
    private ArrayList<Filter> activeFilters;

    public Search() {
        this.userInput = "";
        this.results = new ArrayList<Course>();
        this.activeFilters = new ArrayList<>();
    }

    public ArrayList<Course> modifyQuery(String input) {
        userInput = input;
        return parseDatabase();
    }

    public ArrayList<Course> addFilter(Filter filter) {
        activeFilters.add(filter);
        return parseDatabase();
    }

    public ArrayList<Course> removeFilter(Filter filter){
        activeFilters.remove(filter);
        return parseDatabase();
    }

    public ArrayList<Course> parseDatabase() {
        for (Course course : Course.database) {

        }
        return null;
    }

}
