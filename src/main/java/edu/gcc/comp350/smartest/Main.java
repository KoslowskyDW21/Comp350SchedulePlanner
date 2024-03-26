package edu.gcc.comp350.smartest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    //public static ArrayList<Course> courseList;

    public static Scanner scnIn;
    //public static Search search;

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

    public static void addStartTimeFilter(Search search, int startTime) {
        Filter startTimeFilter = search.getActiveFilters();
        startTimeFilter.setStartTime(startTime);
        search.modifyFilter(startTimeFilter);
    }

    public static void removeStartTimeFilter(Search search) {
        Filter startTimeFilter = search.getActiveFilters();
        startTimeFilter.setEndTime(800);
        search.modifyFilter(startTimeFilter);
    }

    public static void addEndTimeFilter(Search search, int endTime) {
        Filter endTimeFilter = search.getActiveFilters();
        endTimeFilter.setEndTime(endTime);
        search.modifyFilter(endTimeFilter);
    }

    public static void removeEndTimeFilter(Search search) {
        Filter endTimeFilter = search.getActiveFilters();
        endTimeFilter.setEndTime(2100);
        search.modifyFilter(endTimeFilter);
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
        scnIn = new Scanner(System.in);
        String currInput = "";

        System.out.println("Welcome to GCC Scheduling. Type 'exit' anytime to leave, " +
                "or 'back' to return to home.");
        System.out.println("Where would you like to go? User Info/Schedule/Course Search [ui/s/cs]");


        while (true) {
            System.out.print(":");
            currInput = scnIn.nextLine();

//            if (userInfo) {
//                userInfoAction(currInput, user);
//            }
//            else if (schedule) {
//                scheduleAction(currInput, sched);
//            }
//            else if (courseSearch) {
//                courseSearchAction(currInput, search);
//            }

            // TODO: add to switch
            if (currInput.toLowerCase().equals("exit")) {
                System.out.println("EXITING");
                break;
            }
            else if (currInput.toLowerCase().equals("back")) {
                System.out.println("Where would you like to go? User Info/Schedule/Course Search [ui/s/cs]");
            }

            switch (currInput.toLowerCase()) {
                case "ui":
                    System.out.println("USER INFO");
                    System.out.println("Would you like to view or edit your user info? [v/e]");
                    System.out.print(":");
                    userInfoAction(user);
                    break;
                case "s":
                    System.out.println("SCHEDULE");
                    System.out.println("Would you like to view or generate your schedule? [v/g]");
                    System.out.print(":");
                    scheduleAction(sched);
                    break;
                case "cs":
                    System.out.println("COURSE SEARCH");
                    System.out.println("Would you like to search or apply filters? [s/f]");
                    System.out.print(":");
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

    public static void userInfoAction(User user) {
        String currInput = scnIn.nextLine();

        switch (currInput.toLowerCase()) {
            case "v":
                viewInfo(user);
                break;
            case "e":
                editInfo(user);
                break;
            default:
                break;
        }
    }

    // TODO: CHANGE
    public static void scheduleAction(Schedule sched) {
        String currInput = scnIn.nextLine();

        switch (currInput.toLowerCase()) {
            case "g":
                System.out.println("GENERATE SCHEDULE");
                System.out.println("GENERATING...");
                sched.createRecommendedSchedule();
            case "v":
                System.out.println("VIEW SCHEDULE");
            default: // view schedule
                ArrayList<Course> currCourses = sched.getCurrentCourses();
                for (Course course : currCourses) {
                    System.out.println(course.getCourseCode() + " ---- " + course.getStartTimes());
                }
                break;
        }
    }

    // TODO: add "view filter"
    public static void courseSearchAction(Search search) {
        String currInput = scnIn.nextLine();

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

                editFilters(scnIn, activeFilters, search);
                break;
            default:
                // do nothing
                break;
        }
    }


    public static void viewInfo(User user) {
        System.out.println("VIEW INFO");
        System.out.println("Name: " + user.getName());
        System.out.println("Major: " + user.getMajor());
        System.out.print("Grad Reqs: ");
        for (Course gradReq : user.getGradReqs()) {
            System.out.print(gradReq.getName() + ", ");
        }
        System.out.println();
    }

    public static void editInfo(User user) {
        System.out.println("EDIT INFO");
        System.out.println("Would you like to edit name or major? [n/m]");
        String currInput = scnIn.nextLine();
        switch (currInput.toLowerCase()) {
            case "n":
                System.out.print("Enter new name: ");
                user.setName(scnIn.nextLine());
                break;
            case "m":
                System.out.print("Enter new major: ");
                user.setMajor(scnIn.nextLine());
                break;
            default:
                break;
        }
    }

    public static void editFilters(Scanner scnIn, Filter activeFilters, Search search) {
        System.out.println("Would you like to edit/add or remove a filter? [e/r]");
        String editOrRemove = scnIn.nextLine();
        while (true) {
            System.out.println("Which filter would you like to edit or remove? [cr/st/et/lv/pr/dp]");
            System.out.print(":");
            String filterAttr = scnIn.nextLine();
            // TODO: put in switch
            if (filterAttr.toLowerCase().equals("back") || filterAttr.toLowerCase().equals("exit")) {
                break;
            }

            switch (filterAttr.toLowerCase()) {
                case "cr":
                    creditsFilter(editOrRemove, activeFilters);
                    break;
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
                default:
                    break;
            }
            //break;
        }
    }

    // TODO: change to calling edit/remove credits methods
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
    }

    public static void startTimeFilter(String editOrRemove, Filter activeFilters, Search search) {
        switch (editOrRemove) {
            case "e":
                while(true) {
                    System.out.print("Enter start time (format HHMM): ");
                    String startStr = scnIn.nextLine();
                    try {
                        int start = Integer.parseInt(startStr);

                        if (800 <= start && start <= 1900) {
                            addStartTimeFilter(search, start);
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
                removeStartTimeFilter(search);
                System.out.println("Start time filter successfully removed.");
                break;
            default:
                break;
        }
    }

    public static void endTimeFilter(String editOrRemove, Filter activeFilters, Search search) {
        switch (editOrRemove) {
            case "e":
                while(true) {
                    System.out.print("Enter end time (format HHMM): ");
                    String endStr = scnIn.nextLine();
                    try {
                        int end = Integer.parseInt(endStr);

                        if (850 <= end && end <= 2100) {
                            addEndTimeFilter(search, end);
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
                removeEndTimeFilter(search);
                System.out.println("End time filter successfully removed.");
                break;
            default:
                break;
        }
    }

    public static void levelFilter(String editOrRemove, Filter activeFilters, Search search) {
        switch (editOrRemove) {
            case "e":
                int min = -1;
                int max = -1;
                while(true) {
                    if(min == -1) {
                        try {
                            System.out.print("Enter minimum level (format 000): ");
                            String minStr = scnIn.nextLine();
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

                addLevelFilter(search, max, min);
                break;
            case "r":
                removeLevelFilter(search);
                System.out.println("Level filter successfully removed.");
                break;
            default:
                break;
        }
    }

    public static void professorFilter(String editOrRemove, Filter activeFilters, Search search) {
        switch (editOrRemove) {
            case "e":
                System.out.print("Enter professor last name: ");
                String prof = scnIn.nextLine();
                addProfessorFilter(search, prof);
                System.out.println("Professor filter successfully changed to '"
                        + activeFilters.getProfName() + "'.");
                break;
            case "r":
                removeProfessorFilter(search);
                System.out.println("Professor filter successfully removed.");
                break;
            default:
                break;
        }
    }

    public static void departmentFilter(String editOrRemove, Filter activeFilters, Search search) {
        switch (editOrRemove) {
            case "e":
                System.out.print("Enter department code: ");
                String deptCode = scnIn.nextLine();
                addDepartmentFilter(search, deptCode);
                System.out.println("Department filter successfully changed to '"
                        + activeFilters.getDepartment() + "'.");
                break;
            case "r":
                removeDepartmentFilter(search);
                System.out.println("Department filter successfully removed.");
                break;
            default:
                break;
        }
    }

}


