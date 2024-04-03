package edu.gcc.comp350.smartest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static Scanner scnIn;
    public static User mainUser;

    public static void main(String[] args) {
        User.LoadCoursesFromFile();
        Search search = new Search();
        //courseList = new ArrayList<>();

        try {
            Search.ParseClasses();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e);
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }

        //akSearchTest();
        consoleSoftwareLoop();
        mainUser.SaveToFile();
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
        //User user = new User();
        Schedule sched = new Schedule();
        Search search = new Search();
        scnIn = new Scanner(System.in);
        String currInput = "";

        System.out.println("Welcome to GCC Scheduling. Type 'back' anytime to go back, " +
                "or 'exit' from home to leave.");
        //System.out.println("Where would you like to go? User Info/Schedule/Course Search [ui/s/cs]");


        while (true) {
            System.out.println("Where would you like to go? User Info/Schedule/Course Search [ui/s/cs]");
            System.out.print(":");
            currInput = scnIn.nextLine();


            switch (currInput.toLowerCase()) {
                case "exit":
                    System.out.println("EXITING");
                    return;
                case "back":
                    //System.out.println("Where would you like to go? User Info/Schedule/Course Search [ui/s/cs]");
                    break;
                case "ui":
                    System.out.println("USER INFO");
                    userInfoAction();
                    break;
                case "s":
                    System.out.println("SCHEDULE");
                    scheduleAction(sched);
                    break;
                case "cs":
                    System.out.println("COURSE SEARCH");
                    courseSearchAction(search);
                    break;
                case "\n":
                    break;
                default:
                    //System.out.println("Command not recognized. Please try again.");
                    break;
            }
        }
    }

    public static void userInfoAction() {
        while (true) {
            System.out.println("Would you like to view or edit your user info? [v/e]");
            System.out.print(":");
            String currInput = scnIn.nextLine();

            switch (currInput.toLowerCase()) {
                case "back":
                    return;
                case "v":
                    System.out.println("VIEW INFO");
                    viewInfo();
                    break;
                case "e":
                    System.out.println("EDIT INFO");
                    System.out.println("Would you like to edit name or major? [n/m]");
                    System.out.print(":");
                    editInfo();
                    break;
                //case "exit":
                    //break;
                default:
                    break;
            }
        }
    }

    // TODO: CHANGE
    public static void scheduleAction(Schedule sched) {
        while (true) {
            System.out.println("Would you like to view or generate your schedule? [v/g]");
            System.out.print(":");
            String currInput = scnIn.nextLine();

            switch (currInput.toLowerCase()) {
                case "back":
                    return;
                case "g":
                    generateSchedule(sched);
                case "v":
                    System.out.println("SCHEDULE");
                //case "exit":
                default: // view schedule
                    viewSchedule(sched);
                    break;
            }
        }
    }

    public static void courseSearchAction(Search search) {
        while (true) {
            System.out.println("Would you like to search, apply, or view current filters? [s/a/v]");
            System.out.print(":");
            String currInput = scnIn.nextLine();

            switch (currInput.toLowerCase()) {
                case "back":
                    return;
                case "s":
                    System.out.println("SEARCH");
                    searchDatabase(search);
                    break;
                case "a":
                    Filter activeFilters = search.getActiveFilters();
                    System.out.println("APPLY FILTERS");
                    String filStr = activeFilters.filterToString();
                    System.out.println(filStr);

                    System.out.println("Would you like to edit/add or remove a filter? [e/r]");
                    System.out.print(":");
                    editFilters(scnIn, activeFilters, search);
                    break;
                case "v":
                    Filter activeFilters2 = search.getActiveFilters();
                    System.out.println("CURRENT FILTERS");
                    String filStr2 = activeFilters2.filterToString();
                    System.out.println(filStr2);
                    break;
                //case "exit":
                default:
                    // do nothing
                    break;
            }
        }
    }


    public static void viewInfo() {
        System.out.println("Name: " + mainUser.getName());
        System.out.println("Major: " + mainUser.getMajor());
        System.out.print("Grad Reqs: ");
        for (Course gradReq : mainUser.getGradReqs()) {
            System.out.print(gradReq.getName() + ", ");
        }
        System.out.println();
    }

    public static void editInfo() {
        String currInput = scnIn.nextLine();
        switch (currInput.toLowerCase()) {
            case "back":
                return;
            case "n":
                System.out.print("Enter new name: ");
                mainUser.setName(scnIn.nextLine());
                break;
            case "m":
                System.out.print("Enter new major: ");
                mainUser.setMajor(scnIn.nextLine());
                break;
            //case "exit":
            default:
                break;
        }
    }

    // TODO: decide whether to just put this in scheduleAction and get rid of this method
    public static void generateSchedule(Schedule sched) {
        System.out.println("GENERATE SCHEDULE");
        System.out.println("GENERATING...");
        sched.createRecommendedSchedule();
    }

    public static void viewSchedule(Schedule sched) {
        ArrayList<Course> currCourses = sched.getCurrentCourses();
        for (Course course : currCourses) {
            System.out.println(course.getCourseCode() + " ---- " + course.getStartTimes());
        }
        //System.out.println(sched.toString()); // TODO: USE THIS ONCE TOSTRING IMPLEMENTED
    }

    public static void searchDatabase(Search search) {
        while (true) {
            System.out.print("Enter query: ");
            String query = scnIn.nextLine();
            if (query.equals("back")) {
                return;
            }
            search.modifyQuery(query);
            String resStr = search.resultsToString();
            System.out.println(resStr);
        }

    }

    public static void editFilters(Scanner scnIn, Filter activeFilters, Search search) {
        String editOrRemove = scnIn.nextLine();
        if (editOrRemove.equals("back")) {
            return;
        }
        while (true) {
            System.out.println("Which filter would you like to edit or remove? [st/et/lv/pr/dp/da]");
            System.out.print(":");
            String filterAttr = scnIn.nextLine();

            switch (filterAttr.toLowerCase()) {
                case "back":
                    return;
                /*case "cr":
                    creditsFilter(editOrRemove, activeFilters);
                    break;*/
                case "st":
                    startTimeFilter(editOrRemove, activeFilters, search);
                    break;
                case "et":
                    endTimeFilter(editOrRemove, activeFilters, search);
                    break;
                case "lv":
                    levelFilter(editOrRemove, activeFilters, search);
                    break;
                case "pr":
                    professorFilter(editOrRemove, activeFilters, search);
                    break;
                case "dp":
                    departmentFilter(editOrRemove, activeFilters, search);
                    break;
                //case "exit":
                case "da":
                    daysFilter(editOrRemove, activeFilters, search);
                    break;
                case "exit":
                default:
                    break;
            }
            //break;
        }
    }

    /*// TODO: change to calling edit/remove credits methods
    public static void creditsFilter(String editOrRemove, Filter activeFilters) {
        switch (editOrRemove) {
            case "e":
                System.out.print("Enter credits: ");
                String credStr = scnIn.nextLine();
                int creds = Integer.parseInt(credStr);
                if (0 <= creds && creds <= 4) {
                    activeFilters.setCredits(creds);
                    System.out.println("Credit filter successfully changed to "
                            + activeFilters.getCredits() + "'.");
                }
                break;
            case "r":
                break;
            default:
                break;
        }
    }*/

    public static void startTimeFilter(String editOrRemove, Filter activeFilters, Search search) {
        switch (editOrRemove) {
            case "back":
                return;
            case "e":
                while(true) {
                    System.out.print("Enter start time (format HHMM): ");
                    String startStr = scnIn.nextLine();
                    if (startStr.equals("back")) {
                        return;
                    }
                    try {
                        int start = Integer.parseInt(startStr);

                        if (800 <= start && start <= 1900) {
                            Filter.addStartTimeFilter(search, start);
                            System.out.println("Start time filter successfully changed to "
                                    + activeFilters.getStartTime() + "'.");
                        }
                        else {
                            throw new Exception(String.valueOf(start));
                        }
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Incorrect format, try again.");
                    } catch (Exception e) {
                        System.out.println(e.getMessage() + " is not a valid start time, try again.");
                    }
                }
                break;
            case "r":
                Filter.removeStartTimeFilter(search);
                System.out.println("Start time filter successfully removed.");
                break;
            default:
                break;
        }
    }

    public static void endTimeFilter(String editOrRemove, Filter activeFilters, Search search) {
        switch (editOrRemove) {
            case "back":
                return;
            case "e":
                while(true) {
                    System.out.print("Enter end time (format HHMM): ");
                    String endStr = scnIn.nextLine();
                    if (endStr.equals("back")) {
                        return;
                    }
                    try {
                        int end = Integer.parseInt(endStr);

                        if (850 <= end && end <= 2100) {
                            Filter.addEndTimeFilter(search, end);
                            System.out.println("End time filter successfully changed to "
                                    + activeFilters.getEndTime() + "'.");
                        }
                        else {
                            throw new Exception(String.valueOf(end));
                        }
                        break;
                    }
                    catch (NumberFormatException e) {
                        System.out.println("Incorrect format, try again.");
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage() + " is not a valid end time, try again.");
                    }
                }
                break;
            case "r":
                Filter.removeEndTimeFilter(search);
                System.out.println("End time filter successfully removed.");
                break;
            default:
                break;
        }
    }

    public static void levelFilter(String editOrRemove, Filter activeFilters, Search search) {
        switch (editOrRemove) {
            case "back":
                return;
            case "e":
                int min = -1;
                int max = -1;
                while(true) {
                    if(min == -1) {
                        try {
                            System.out.print("Enter minimum level (format 000): ");
                            String minStr = scnIn.nextLine();
                            if (minStr.equals("back")) {
                                return;
                            }
                            min = Integer.parseInt(minStr);
                            if(!(100 <= min && min <= 600)) {
                                throw new Exception(String.valueOf(min));
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Incorrect format, try again.");
                            continue;
                        } catch (Exception e) {
                            System.out.println(e.getMessage() + " is not a valid minimum level, try again.");
                            min = -1;
                            continue;
                        }
                    }
                    if(max == -1) {
                        try {
                            System.out.print("Enter maximum level (format 000): ");
                            String maxStr = scnIn.nextLine();
                            max = Integer.parseInt(maxStr);
                            if(!(100 <= max && max <= 600)) {
                                throw new Exception(String.valueOf(max));
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Incorrect format, try again.");
                            continue;
                        } catch (Exception e) {
                            System.out.println(e.getMessage() + " is not a valid maximum level, try again.");
                            max = -1;
                            continue;
                        }
                    }
                    break;
                }

                Filter.addLevelFilter(search, max, min);
                break;
            case "r":
                Filter.removeLevelFilter(search);
                System.out.println("Level filter successfully removed.");
                break;
            default:
                break;
        }
    }

    public static void professorFilter(String editOrRemove, Filter activeFilters, Search search) {
        switch (editOrRemove) {
            case "back":
                return;
            case "e":
                System.out.print("Enter professor last name: ");
                String prof = scnIn.nextLine();
                if (prof.equals("back")) {
                    return;
                }
                Filter.addProfessorFilter(search, prof);
                System.out.println("Professor filter successfully changed to '"
                        + activeFilters.getProfName() + "'.");
                break;
            case "r":
                Filter.removeProfessorFilter(search);
                System.out.println("Professor filter successfully removed.");
                break;
            default:
                break;
        }
    }

    public static void departmentFilter(String editOrRemove, Filter activeFilters, Search search) {
        switch (editOrRemove) {
            case "back":
                return;
            case "e":
                System.out.print("Enter department code: ");
                String deptCode = scnIn.nextLine();
                if (deptCode.equals("back")) {
                    return;
                }
                Filter.addDepartmentFilter(search, deptCode);
                System.out.println("Department filter successfully changed to '"
                        + activeFilters.getDepartment() + "'.");
                break;
            case "r":
                Filter.removeDepartmentFilter(search);
                System.out.println("Department filter successfully removed.");
                break;
            default:
                break;
        }
    }

    public static void daysFilter(String editOrRemove, Filter activeFilters, Search search) {
        switch (editOrRemove) {
            case "back":
                return;
            case "e":
                while(true) {
                    try {
                        System.out.print("Enter days (format M_WRF): ");
                        String days = scnIn.nextLine();
                        if (days.equals("back")) {
                            return;
                        }
                        if (days.length() != 5) {
                            throw new Exception();
                        }
                        days = days.toUpperCase();
                        checkDaysFormat(days);
                        boolean[] daysFilter = parseDays(days);
                        Filter.addDays(search, daysFilter);
                        System.out.println("Days filter successfully added.");
                        break;
                    } catch (Exception e) {
                        System.out.println("Incorrect format, try again.");
                    }
                }
                break;
            case "r":
                Filter.removeDays(search);
                System.out.println("Days filter successfully removed.");
                break;
            default:
                break;
        }
    }

    public static boolean[] parseDays(String daysString) {
        boolean[] days = new boolean[5];
        for(int i = 0; i < 5; i++) {
            days[i] = daysString.charAt(i) != '_';
        }
        return days;
    }

    public static void checkDaysFormat(String days) throws Exception {
        char[] dayLetter = {'M', 'T', 'W', 'R', 'F'};
        for(int i = 0; i < 5; i++) {
            if (days.charAt(i) != dayLetter[i] && days.charAt(i) != '_') {
                throw new Exception();
            }
        }
    }
}
