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

    private static void ParseClasses() throws IOException {
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

    public static void addProfessorFilter(Search search, String professor) {
        Filter professorFilter = search.getActiveFilters();
        professorFilter.setProfName(professor);
        search.modifyFilter(professorFilter);
    }
    public static void removeProfessorFilter(Search search) {
        Filter professorFilter = search.getActiveFilters();
        professorFilter.setProfName("");
        search.modifyFilter(professorFilter);
    }

    public static void addLevelFilter(Search search, int upper, int lower) {
        Filter levelFilter = search.getActiveFilters();
        levelFilter.setLevelMax(upper);
        levelFilter.setLevelMin(lower);
        search.modifyFilter(levelFilter);
    }

    public static void removeLevelFilter(Search search) {
        Filter levelFilter = search.getActiveFilters();
        levelFilter.setLevelMax(600);
        levelFilter.setLevelMin(100);
        search.modifyFilter(levelFilter);
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
        User user = new User();
        Scanner scnIn = new Scanner(System.in);
        boolean userInfo = false;
        boolean schedule = false;
        boolean courseSearch = false;
        String currInput = "";

        System.out.println("Welcome to GCC Scheduling. Type 'exit' anytime to leave, " +
                "or 'back' to return to home.");
        System.out.println("Where would you like to go? User Info/Schedule/Course Search [ui/s/cs]");


        while (true) {
            System.out.print(":");
            currInput = scnIn.nextLine();

            if (userInfo) {
                switch (currInput.toLowerCase()) {
                    case "v":
                        System.out.println("VIEW INFO");
                        System.out.println("Name: " + user.getName());
                        System.out.println("Major: " + user.getMajor());
                        System.out.print("Grad Reqs: ");
                        for (Course gradReq : user.getGradReqs()) {
                            System.out.print(gradReq.getName() + ", ");
                        }
                        System.out.println();
                        break;
                    case "e":
                        System.out.println("EDIT INFO");
                        System.out.println("Would you like to edit name or major? [n/m]");
                        currInput = scnIn.nextLine();
                        switch (currInput.toLowerCase()) {
                            case "n":
                                System.out.print("Enter new name: ");
                                user.setName(scnIn.nextLine());
                                break;
                            case "m":
                                System.out.print("Enter new major: ");
                                user.setMajor(scnIn.nextLine());
                                break;
                        }
                        break;
                }
            }
            else if (schedule) {
                switch (currInput.toLowerCase()) {
                    case "v":
                        System.out.println("VIEW SCHEDULE");
                        break;
                    case "g":
                        System.out.println("GENERATE SCHEDULE");
                        break;
                }

            }
            else if (courseSearch) {
                switch (currInput.toLowerCase()) {
                    case "s":
                        System.out.println("SEARCH");
                        break;
                    case "f":
                        System.out.println("FILTERS");
                        break;
                }
            }

            if (currInput.toLowerCase().equals("exit")) {
                System.out.println("EXITING");
                break;
            }
            else if (currInput.toLowerCase().equals("back")) {
                System.out.println("Where would you like to go? User Info/Schedule/Course Search [ui/s/cs]");
            }
            switch (currInput.toLowerCase()) {
                case "ui":
                    userInfo = true;
                    schedule = false;
                    courseSearch = false;
                    System.out.println("USER INFO");
                    System.out.println("Would you like to view or edit your user info? [v/e]");
                    break;
                case "s":
                    userInfo = false;
                    schedule = true;
                    courseSearch = false;
                    System.out.println("SCHEDULE");
                    System.out.println("Would you like to view or generate your schedule? [v/g]");
                    break;
                case "cs":
                    userInfo = false;
                    schedule = false;
                    courseSearch = true;
                    System.out.println("COURSE SEARCH");
                    System.out.println("Would you like to search or apply filters? [s/f]");
                    break;
                case "\n":
                    break;
                default:
                    //System.out.println("Command not recognized. Please try again.");
                    break;
            }
        }
    }
}

