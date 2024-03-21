package edu.gcc.comp350.smartest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    //public static ArrayList<Course> courseList;


    public static void main(String[] args) {
        Search search = new Search();
        //courseList = new ArrayList<>();

        System.out.println("Test");

        try {
            ParseClasses();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e);
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }

        //akSearchTest();
        consoleSoftwareLoop();
    }

    public static void ParseClasses() throws IOException {
        Scanner scn = new Scanner(new File("2020-2021.csv"));
        scn.nextLine();
        while (scn.hasNext()) {
            //Course temp = new Course(scn.nextLine());
            //courseList.add(new Course(scn.nextLine()));
            Course.database.add(new Course(scn.nextLine()));
        }
    }
    public static void addDepartmentFilter(Search search, String department) {
        Filter departmentFilter = search.getActiveFilters();
        departmentFilter.setDepartment(department);
        search.modifyFilter(departmentFilter);
    }
    public static void removeDepartmentFilter(Search search) {
        Filter departmentFilter = search.getActiveFilters();
        departmentFilter.setDepartment("");
        search.modifyFilter(departmentFilter);
    }

    public static void addDaysFilter(Search search, String days) {
        Filter daysFilter = search.getActiveFilters();
        //daysFilter.setDays(days);
        search.modifyFilter(daysFilter);
    }

    public static void akSearchTest() {
        Search search = new Search();
        String input = "3 50";
        search.modifyQuery(input);
        ArrayList<Course> results = search.getResults();
        for (Course course : results) {
            System.out.println(course.getName());
        }
    }



    public static void consoleSoftwareLoop() {
        Scanner scnIn = new Scanner(System.in);
        boolean userInfo = false;
        boolean schedule = false;
        boolean courseSearch = false;
        String currInput = "";

        System.out.println("Welcome to GCC Scheduling. Type 'exit' anytime to leave.");
        System.out.println("Where would you like to go? User Info/Schedule/Course Search [ui/s/cs]");



        while (true) {
            System.out.print(":");
            currInput = scnIn.nextLine();
            if (userInfo) {
                if (currInput.toLowerCase().equals("v")) {
                    System.out.println("VIEW INFO");
                    System.out.print("");
                }
                else if (currInput.toLowerCase().equals("e")) {
                    System.out.println("EDIT INFO");
                }
            }
            else if (schedule) {
                if (currInput.toLowerCase().equals("v")) {
                    System.out.println("VIEW SCHEDULE");
                }
                else if (currInput.toLowerCase().equals("g")) {
                    System.out.println("GENERATE SCHEDULE");
                }
            }
            else if (courseSearch) {
                if (currInput.toLowerCase().equals("s")) {
                    System.out.println("SEARCH");
                }
                else if (currInput.toLowerCase().equals("f")) {
                    System.out.println("FILTERS");
                }
            }

            if (currInput.toLowerCase().equals("exit")) {
                System.out.println("EXITING");
                break;
            }
            else if (currInput.toLowerCase().equals("ui")) {
                userInfo = true;
                schedule = false;
                courseSearch = false;
                System.out.println("USER INFO");
                System.out.println("Would you like to view or edit your user info? [v/e]");
            }
            else if (currInput.toLowerCase().equals("s")) {
                userInfo = false;
                schedule = true;
                courseSearch = false;
                System.out.println("SCHEDULE");
                System.out.println("Would you like to view or generate your schedule? [v/g]");
            }
            else if (currInput.toLowerCase().equals("cs")) {
                userInfo = false;
                schedule = false;
                courseSearch = true;
                System.out.println("COURSE SEARCH");
                System.out.println("Would you like to search or apply filters? [s/f]");
            }
            else if (currInput.toLowerCase().equals("\n")) {
                // loop again (do nothing)
            }
            /*else {
                System.out.println("Command not recognized. Please try again.");
            }*/
        }

    }

}

