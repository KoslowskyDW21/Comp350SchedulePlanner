package edu.gcc.comp350.smartest;
import java.util.ArrayList;
import java.io.*;
import java.util.regex.Pattern;

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
        input = convertString(input);
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
            String codeConverted = convertString(course.getCourseCode());
            String nameConverted = convertString(course.getName());
            if (codeConverted.contains(userInput) // search by code
                    || nameConverted.contains(userInput) // search by name
                    && matchesFilters(course)) { // matches course with current filters
                results.add(course);
            }
        }

        return results;
    }

    public static String convertString(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        StringBuilder result = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (!(Character.isWhitespace(c) || isPunctuation(c))) {
                result.append(Character.toLowerCase(c));
            }
        }
        return result.toString();
    }

    private static boolean isPunctuation(char c) {
        Pattern pattern = Pattern.compile("\\p{Punct}");
        return pattern.matcher(Character.toString(c)).matches();
    }

    public String resultsToString() {
        String res = "";
        for (Course course : results) {
            res += course.getCourseCode() + " --- " + course.getName() + "\n";
        }
        if (res.isEmpty()) {
            System.out.println("Sorry, Course '" + userInput + "' does not exist. ");
        }
        return res;
    }


    private boolean matchesFilters(Course course) {
        String department = activeFilters.getDepartment();
        String professor = activeFilters.getProfName();
        int levelMin = activeFilters.getLevelMin();
        int levelMax = activeFilters.getLevelMax();

        if((!department.isEmpty()) && (!course.getDepartment().equals(department))) {
            return false;
        }
        if((!professor.isEmpty()) && (!course.getProfessor().equals(professor))) {
            return false;
        }
        if(course.getLevel() < levelMin || course.getLevel() > levelMax) {
            return false;
        }

        return true;
    }
}
