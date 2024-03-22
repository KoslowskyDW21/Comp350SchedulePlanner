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
        Schedule sched = new Schedule();
        Search search = new Search();
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
                userInfoAction(currInput, user, scnIn);
            }
            else if (schedule) {
                scheduleAction(currInput, sched);
            }
            else if (courseSearch) {
                courseSearchAction(currInput, search, scnIn);
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

    public static void userInfoAction(String currInput, User user, Scanner scnIn) {
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

    public static void scheduleAction(String currInput, Schedule sched) {
        switch (currInput.toLowerCase()) {
            case "g":
                System.out.println("GENERATE SCHEDULE");
                System.out.println("GENERATING...");
                sched.createRecommendedSchedule();
            case "v":
                System.out.println("VIEW SCHEDULE");
                ArrayList<Course> currCourses = sched.getCurrentCourses();
                for (Course course : currCourses) {
                    System.out.println(course.getCourseCode() + " ---- " + course.getStartTimes());
                }
                break;
        }
    }

    public static void courseSearchAction(String currInput, Search search, Scanner scnIn) {
        switch (currInput.toLowerCase()) {
            case "s":
                System.out.println("SEARCH");
                System.out.print("Enter query: ");
                String query = scnIn.nextLine();
                search.modifyQuery(query);
                String resStr = search.resultsToString();
                System.out.println(resStr);
                break;
            case "f":
                Filter activeFilters = search.getActiveFilters();
                System.out.println("FILTERS");
                String filStr = activeFilters.filterToString();
                System.out.println(filStr);
                /*System.out.println("Current filters are: ");
                System.out.println("Credits: " + activeFilters.getCredits());
                System.out.println("Start Time: " + activeFilters.getStartTime());
                System.out.println("End Time: " + activeFilters.getEndTime());
                System.out.println("Level Min: " + activeFilters.getLevelMin());
                System.out.println("Level Max: " + activeFilters.getLevelMax());
                System.out.println("Prof. Name: " + activeFilters.getProfName());
                System.out.println("Department: " + activeFilters.getDepartment());*/

                editFilters(scnIn, activeFilters);

        }
    }

    public static void editFilters(Scanner scnIn, Filter activeFilters) {
        while (true) {
            System.out.println("Which filter would you like to edit? [cr/st/et/mn/mx/pr/dp]");
            System.out.print(":");
            String filterAttr = scnIn.nextLine();
            if (filterAttr.toLowerCase().equals("back")) {
                break;
            }
            switch (filterAttr.toLowerCase()) {
                case "cr":
                    System.out.print("Enter credits: ");
                    String credStr = scnIn.nextLine();
                    int creds = Integer.parseInt(credStr);
                    if (0 <= creds && creds <= 4) {
                        activeFilters.setCredits(creds);
                        System.out.println("Credit filter successfully changed.");
                    }
                    break;
                case "st":
                    System.out.print("Enter start time (format HHMM): ");
                    String startStr = scnIn.nextLine();
                    int start = Integer.parseInt(startStr);
                    if (800 <= start && start <= 1900) {
                        activeFilters.setStartTime(start);
                        System.out.println("Start time filter successfully changed.");
                    }
                    break;
                case "et":
                    System.out.print("Enter end time (format HHMM): ");
                    String endStr = scnIn.nextLine();
                    int end = Integer.parseInt(endStr);
                    if (900 <= end && end <= 2100) {
                        activeFilters.setEndTime(end);
                        System.out.println("End time filter successfully changed.");
                    }
                    break;
                case "mn":
                    System.out.print("Enter minimum level (format 000): ");
                    String minStr = scnIn.nextLine();
                    int min = Integer.parseInt(minStr);
                    if (100 <= min && min <= 400) {
                        activeFilters.setLevelMin(min);
                        System.out.println("Minimum level filter successfully changed.");
                    }
                    break;
                case "mx":
                    System.out.print("Enter maximum level (format 000): ");
                    String maxStr = scnIn.nextLine();
                    int max = Integer.parseInt(maxStr);
                    if (100 <= max && max <= 400) {
                        activeFilters.setLevelMax(max);
                        System.out.println("Maximum level filter successfully changed.");
                    }
                    break;
                case "pr":
                    System.out.print("Enter professor last name: ");
                    String prof = scnIn.nextLine();
                    activeFilters.setProfName(prof);
                    System.out.println("Professor filter successfully changed.");
                    break;
                case "dp":
                    System.out.print("Enter department code: ");
                    String deptCode = scnIn.nextLine();
                    activeFilters.setDepartment(deptCode);
                    System.out.println("Department filter successfully changed.");
                    break;
            }
            //break;
        }
    }

}

