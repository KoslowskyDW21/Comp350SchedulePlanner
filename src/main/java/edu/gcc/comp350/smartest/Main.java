package edu.gcc.comp350.smartest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/home.fxml"));
        Scene scene = new Scene(root, 800, 500);
        stage.setScene(scene);
        stage.show();

        //Window window = new Window(stage);
        //PopupWindow popupWindow = new PopupWindow() {};
    }

    public static Scanner scnIn;
    public static User mainUser;
    public static Search search;
    //public static Filter mainFilter;

    public static void main(String[] args) {
        search = new Search();
        //mainFilter = new Filter();
        setUp();

        launch();
        // Create the course database
        //setUp();
    }

    public static void setUp() {
        try {
            Search.ParseClasses();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e);
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }

        // Load previous user and schedule info
        User.LoadCoursesFromFile();
        if(mainUser.savedSchedules.isEmpty()) {
            mainUser.savedSchedules.add(new Schedule());
            mainUser.savedSchedules.add(new Schedule());
        }
        //System.out.println(Course.database.getFirst().getDescription());
        //System.out.println(Course.database.get(1).getDescription());


        //consoleSoftwareLoop();

        //launch();

        // Save to the file before closing
        mainUser.SaveToFile();
    }

    /*public static void akSearchTest() {
        Search search = new Search();
        String input = "3 50";
        search.modifyQuery(input);
        ArrayList<Course> results = search.getResults();
        for (Course course : results) {
            System.out.println(course.getName());
        }
    }*/

    public static void consoleSoftwareLoop() {
        //Search search = new Search();
        //search = new Search();
        scnIn = new Scanner(System.in);
        String currInput = "";

        System.out.println("Welcome to GCC Scheduling. Type 'back' anytime to go back, " +
                "or 'exit' from home to leave.");

        while (true) {
            System.out.println("Where would you like to go? User Info/Schedule/Course Search [ui/s/cs]. Type 'exit' to quit");
            System.out.print(":");
            currInput = scnIn.nextLine();


            switch (currInput.toLowerCase()) {
                case "exit":
                    System.out.println("EXITING");
                    return;
                case "back":
                    break;
                case "ui":
                    System.out.println("USER INFO");
                    userInfoAction();
                    break;
                case "s":
                    System.out.println("SCHEDULE");
                    scheduleAction();
                    break;
                case "cs":
                    System.out.println("COURSE SEARCH");
                    courseSearchAction(search);
                    break;
                case "\n":
                    break;
                default:
                    System.out.println("Command not recognized. Please try again.");
                    break;
            }
        }
    }

    public static void userInfoAction() {
        while (true) {
            System.out.println("Would you like to view or edit your user info? [v/e] Type 'back' to return");
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
                    System.out.println("Would you like to add or remove completed courses? [add/rem]");
                    System.out.print(":");
                    editInfo();
                    break;
                default:
                    System.out.println("Command not recognized. Please try again.");
                    break;
            }
        }
    }

    public static void scheduleAction() {
        while (true) {
            System.out.println("Would you like to view or generate your schedule, or remove a course? [v/g/r] Type 'back' to return");
            System.out.print(":");
            String currInput = scnIn.nextLine();

            switch (currInput.toLowerCase()) {
                case "back":
                    return;
                case "g":
                    generateSchedule();
                    break;
                case "v":
                    System.out.println("SCHEDULE");
                    viewSchedule();
                    break;
                case "r":
                    removeCourse();
                    break;
                default:
                    System.out.println("Command not recognized. Please try again.");
                    break;
            }
        }
    }

    public static void courseSearchAction(Search search)  {
        while (true) {
            System.out.println("Would you like to search, apply, or view current filters? [s/a/v] Type 'back' to return");
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
                default:
                    System.out.println("Command not recognized. Please try again.");
                    break;
            }
        }
    }


    public static void viewInfo() {
        System.out.println("Name: " + mainUser.getName());
        System.out.println("Major: " + mainUser.getMajor());
        System.out.println("Graduation Requirements: ");
        for (Course gradReq : mainUser.getGradReqs()) {
            System.out.print(gradReq);
        }
        if(mainUser.getGradReqs().isEmpty()) {
            System.out.println("No graduation requirements");
        }
        System.out.println();
        System.out.println("Completed Courses:");
        for (Course completedCourse : mainUser.getCompletedCourses()) {
            System.out.println(completedCourse);
        }
        if(mainUser.getCompletedCourses().isEmpty()) {
            System.out.println("No completed courses");
        }
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
//            case "y":
//                System.out.println("Enter 1 for Freshman, 2 for Sophomore, 3 for Junior, 4 for Senior:");
//                currInput = scnIn.nextLine();
//                if(scnIn.hasNextInt()) {
//                    int num = Integer.parseInt(currInput);
//                    if(1 <= num && num <= 4) {
//                        mainUser.setSemester(num);
//                    } else {
//                        System.out.println("Invalid input");
//                    }
//                } else {
//                    System.out.println("Invalid input");
//                }
//                break;
//            case "s":
//                System.out.println("Enter 1 for Fall, 2 for Spring:");
//                currInput = scnIn.nextLine();
//                if(scnIn.hasNextInt()) {
//                    int num = Integer.parseInt(currInput);
//                    if(1 <= num && num <= 2) {
//                        mainUser.setSemester(num);
//                    } else {
//                        System.out.println("Invalid input");
//                    }
//                } else {
//                    System.out.println("Invalid input");
//                }
//                break;
            case "add":
                System.out.println("Enter Course Code to be added to completed courses, or type 'back' to return: ");
                currInput = scnIn.nextLine();
                Course course = Course.findCourse(currInput);
                if(course != null) {
                    mainUser.addTakenCourse(course);
                } else {
                    System.out.println("Could not find course in database");
                }
                break;
            case "rem":
                System.out.println("Enter Course Code to be removed from completed courses, or type 'back' to return: ");
                currInput = scnIn.nextLine();
                Course courseToRemove = Course.findCourse(currInput);
                if(courseToRemove != null) {
                    mainUser.removeTakenCourse(courseToRemove);
                } else {
                    System.out.println("Could not find course in database");
                }
                break;
            default:
                System.out.println("Command '" + currInput + "' not recognized");
        }
    }

    public static void generateSchedule() {
        int numClasses;
        do {
            System.out.print("How many credits do you want this semester? (integer between 12 and 18): ");
            while (!scnIn.hasNextInt()) {
                System.out.println("That's not an integer!");
                scnIn.next(); // discard non-integer input
                System.out.print("How many credits do you want this semester? (integer between 12 and 18): ");
            }
            numClasses = scnIn.nextInt();
            scnIn.nextLine();
        } while (numClasses < 12 || numClasses > 18);
        ArrayList<Course> classesLeft = mainUser.getClassesLeftToTake();
        System.out.println("Select the type of schedule generation you want [fast/accurate]");
        String input = scnIn.nextLine();
        ArrayList<Course> recommendedSchedule;
        switch (input.toLowerCase()) {
            case "fast":
                recommendedSchedule = Schedule.createRecommendedSchedule(classesLeft, numClasses);
                break;
            case "accurate":
                recommendedSchedule = Schedule.createRecommendedScheduleSlow(classesLeft, numClasses);
                break;
            default:
                System.out.println("Invalid input: '" + input + "'");
                return;
        }
        // Print or return the recommended schedule
        for (Course course : recommendedSchedule) {
            System.out.print(course);
        }
        while(true) {
            System.out.print("Type 'all' to make this your current schedule, 'add' to add a specific course to your schedule, or type 'back' to return: ");
            String query = scnIn.nextLine();
            if(query.equals("back")){
                return;
            }
            if(query.equals("all")) {
                mainUser.savedSchedules.remove(mainUser.savedSchedules.getFirst());
                Schedule newSchedule = new Schedule();
                for(Course course : recommendedSchedule) {
                    newSchedule.addCourse(course);
                }
                mainUser.savedSchedules.add(newSchedule);
                System.out.println("New schedule created!");
            }
            if(query.equals("add")) {
                System.out.println("Type the course code of the course you would like to add");
                query = scnIn.nextLine();
                boolean added = false;
                for (Course c : recommendedSchedule){
                    if(Search.convertString(query).equals(Search.convertString(c.getCourseCode()))){
                        if(mainUser.savedSchedules.getFirst().addCourse(c) == 0) {
                            System.out.println("Course added to Schedule!");
                        }
                        else{
                            System.out.println("Course overlaps with one already in schedule!");
                        }
                        added = true;
                        break;
                    }
                }
                if(!added){
                    System.out.println("Invalid/Incorrect Course Code.");
                }
            }
        }
    }

    public static void viewSchedule() {
        ArrayList<Course> currCourses = mainUser.savedSchedules.getFirst().getCurrentCourses();
        if(mainUser.savedSchedules.getFirst().getCurrentCourses().isEmpty()){
            System.out.println("Schedule is Empty");
        }
        else {
            for (Course course : currCourses) {
                System.out.print(course);
            }
            //System.out.println(sched);
        }
    }

    public static void removeCourse(){
        while(true){
            System.out.print("Enter Course Code to be removed from schedule, or type 'back' to return: ");
            String course = scnIn.nextLine();
            if(course.equals("back")){
                return;
            }
            Course toRemove = null;
            for(Course c : mainUser.savedSchedules.getFirst().getCurrentCourses()){
                if(Search.convertString(course).equals(Search.convertString(c.getCourseCode()))){
                    System.out.println("Course removed from schedule.");
                    toRemove = c;
                }
            }
            //was getting a concurrent modification exception without this - i know it looks stupid
            if(toRemove != null){
                mainUser.savedSchedules.getFirst().removeCourse(toRemove);
            }
            else{
                System.out.println("Course Code not found in schedule.");
            }
        }
    }

    public static void searchDatabase(Search search) {
        while (true) {
            if(search.getResults().isEmpty()) {
                System.out.print("Enter query, or type 'back' to return: ");
            }
            else{
                System.out.print("Enter query, [a] to add a course to schedule, or type 'back' to return: ");
            }
            String query = scnIn.nextLine();
            if (query.equals("back")) {
                return;
            }
            if(query.equals("a")){
                addFromSearch(search);
            }
            else {
                search.modifyQuery(query);
                String resStr = search.resultsToString();
                System.out.println(resStr);
            }
        }

    }

    public static void addFromSearch(Search search)  {
        while(true){
            System.out.print("Enter course code to add to schedule, or type 'back' to return: ");
            String query = scnIn.nextLine();
            if(query.equals("back")){
                return;
            }
            boolean added = false;
            for (Course c : search.getResults()){
                if(Search.convertString(query).equals(Search.convertString(c.getCourseCode()))){
                    if(mainUser.savedSchedules.getFirst().addCourse(c) == 0) {
                        System.out.println("Course added to Schedule");
                    }
                    else{
                        System.out.println("Course overlaps with one already in schedule!");
                    }
                    added = true;
                    break;
                }
            }
            if(!added){
                System.out.println("Invalid/Incorrect Course Code.");
            }
        }
    }

    public static void editFilters(Scanner scnIn, Filter activeFilters, Search search) {
        String editOrRemove = scnIn.nextLine();
        while(!(editOrRemove.equals("e") || editOrRemove.equals("r") || editOrRemove.equals("back"))) {
            System.out.println("Command not found, please try again:");
            System.out.print(":");
            editOrRemove = scnIn.nextLine();
        }
        if (editOrRemove.equals("back")) {
            return;
        }
        while (true) {
            System.out.println("Which filter would you like to edit or remove? [st/et/lv/pr/dp/da] Type 'back' to return");
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
                case "da":
                    daysFilter(editOrRemove, activeFilters, search);
                    break;
                default:
                    break;
            }
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

                        if (850 <= end && end <= 2130) {
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
                Filter.addDepartmentFilter(search, deptCode.toUpperCase());
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
