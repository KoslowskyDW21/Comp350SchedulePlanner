package edu.gcc.comp350.smartest;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
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

    static void ParseClasses() throws IOException {
        Scanner scn = new Scanner(new File("2020-2021.csv"));
        scn.nextLine();
        while (scn.hasNext()) {
            Course c = new Course(scn.nextLine());
            if(c.getSemester().equals("Fall")){
                Course.falldatabase.add(c);
            }
            else{
                Course.springdatabase.add(c);
            }
        }



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
        if(activeFilters.getSemester() == 0){
        for (Course course : Course.falldatabase) {
            String codeConverted = convertString(course.getCourseCode());
            String nameConverted = convertString(course.getName());
            if ((codeConverted.contains(userInput) // search by code
                    || nameConverted.contains(userInput)) // search by name
                    && matchesFilters(course)) { // matches course with current filters
                results.add(course);
            }
        }
        }
        else{
            for (Course course : Course.springdatabase) {
                String codeConverted = convertString(course.getCourseCode());
                String nameConverted = convertString(course.getName());
                if ((codeConverted.contains(userInput) // search by code
                        || nameConverted.contains(userInput)) // search by name
                        && matchesFilters(course)) { // matches course with current filters
                    results.add(course);
                }
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
        StringBuilder res = new StringBuilder();
        for (Course course : results) {
            res.append(course);
        }
        if (res.isEmpty()) {
            System.out.println("No courses have been added yet");
        }
        return res.toString();
    }


    private boolean matchesFilters(Course course) {
        String department = activeFilters.getDepartment();
        String professor = activeFilters.getProfName();
        int levelMin = activeFilters.getLevelMin();
        int levelMax = activeFilters.getLevelMax();
        int startTime = activeFilters.getStartTime();
        int endTime = activeFilters.getEndTime();
        boolean[] days = activeFilters.getDays();

        if((!department.isEmpty()) && (!course.getDepartment().equals(department))) {
            return false;
        }
        /*if((!professor.isEmpty()) && (!course.getProfessor().equals(professor))) {
            return false;
        }*/
        if((!professor.isEmpty()) && (!Search.convertString(course.getProfessor()).contains(Search.convertString(professor)))) {
            return false;
        }
        if(course.getLevel() < levelMin || course.getLevel() > levelMax) {
            return false;
        }
        if((startTime > course.getStartTime() || endTime < course.getEndTime())
                && course.getStartTime() > 0 && course.getEndTime() > 0) {
            return false;
        }
        if(!matchDays(days, course)) {
            return false;
        }


        return true;
    }

    private boolean matchDays(boolean[] days, Course course) {
        String courseDays = course.getDays();
        boolean flag = false;
        for(int i = 0; i < 5; i++) {
            if(courseDays.charAt(i) != '_' && days[i]) {
                flag = true;
                break;
            }
        }

        return flag;
    }
}
