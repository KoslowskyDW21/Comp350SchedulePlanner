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
        parseDatabase();
        return null;
    }

    public ArrayList<Course> addFilter(Filter filter) {
        activeFilters.add(filter);
        parseDatabase();
        return null;
    }

    public ArrayList<Course> removeFilter(Filter filter){
        activeFilters.remove(filter);
        parseDatabase();
        return null;
    }

    public ArrayList<Course> parseDatabase() {
        return null;
    }

}
