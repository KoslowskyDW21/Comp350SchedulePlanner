package edu.gcc.comp350.smartest;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
public class User {
    private int userID;
    private String name;
    private String major;
    public ArrayList<Schedule> savedSchedules;
    private ArrayList<Course> completedCourses;
    private ArrayList<Course> gradReqs;
    private Semester curSemester;
    private Year curYear;

    public enum Semester {
        FALL,
        SPRING
    }
    public enum Year {
        FRESHMAN,
        SOPHOMORE,
        JUNIOR,
        SENIOR
    }

    public String getMajor() {
        return major;
    }

    public String getName() {
        return name;
    }

    public int getUserID() {return userID;}

    public void setName(String name) {
        this.name = name;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setSemester(int semester) {
        switch (semester) {
            case 1:
                curSemester = Semester.FALL;
                break;
            case 2:
                curSemester = Semester.SPRING;
                break;
            default:
                System.out.println("Invalid input");
        }
    }

    public void setYear(int year) {
        switch (year) {
            case 1:
                curYear = Year.FRESHMAN;
                break;
            case 2:
                curYear = Year.SOPHOMORE;
                break;
            case 3:
                curYear = Year.JUNIOR;
                break;
            case 4:
                curYear = Year.SENIOR;
                break;
            default:
                System.out.println("Invalid input");
        }
    }

    public ArrayList<Course> getGradReqs() {
        return gradReqs;
    }

    public User(int userID, String name, String major) {
        this.userID = userID;
        this.name = name;
        this.major = major;
        this.savedSchedules = new ArrayList<>();
        this.gradReqs = new ArrayList<>();
        this.completedCourses = new ArrayList<>();
        setCompSciReq();
        curSemester = Semester.FALL;
        curYear = Year.FRESHMAN;
    }

    public User() {
        this.userID = -1;
        this.name = "";
        this.major = "";
        this.gradReqs = new ArrayList<>();
        this.savedSchedules = new ArrayList<>();
        this.completedCourses = new ArrayList<>();
        setCompSciReq();
    }

    private void setCompSciReq() {
        String[] compSciGradReqs = { "COMP141A", "COMP155A", "COMP205A", "COMP220A", "COMP222A",
                "COMP233A", "COMP244A", "COMP314A", "COMP325A", "COMP340A", "COMP342A", "COMP350A",
                "COMP401A", "COMP422A", "COMP435A", "COMP441A", "COMP442A", "COMP443A", "COMP445A",
                "COMP446A", "COMP448A", "COMP451A", "COMP452A", "COMP475A"
        };

        for (String courseStr : compSciGradReqs) {
            Course course = Course.findCourse(courseStr);
            gradReqs.add(course);
            course.setCompletionColorIncompleteGradReq();
        }
    }

//    private void setCompSciReq() {
//        String[] compSciGradReqs = {
//                "COMP141A", "COMP155A"
//        };
//        gradReqs.add(Course.findCourse("COMP141A"));
//        gradReqs.add(Course.findCourse("COMP155A"));
//        gradReqs.add(Course.findCourse("COMP205A"));
//        gradReqs.add(Course.findCourse("COMP220A"));
//        gradReqs.add(Course.findCourse("COMP222A"));
//        gradReqs.add(Course.findCourse("COMP233A"));
//        gradReqs.add(Course.findCourse("COMP244A"));
//        gradReqs.add(Course.findCourse("COMP314A"));
//        gradReqs.add(Course.findCourse("COMP325A"));
//        gradReqs.add(Course.findCourse("COMP340A"));
//        gradReqs.add(Course.findCourse("COMP342A"));
//        gradReqs.add(Course.findCourse("COMP350A"));
//        gradReqs.add(Course.findCourse("COMP401A"));
//        gradReqs.add(Course.findCourse("COMP422A"));
//        gradReqs.add(Course.findCourse("COMP435A"));
//        gradReqs.add(Course.findCourse("COMP441A"));
//        gradReqs.add(Course.findCourse("COMP442A"));
//        gradReqs.add(Course.findCourse("COMP443A"));
//        gradReqs.add(Course.findCourse("COMP445A"));
//        gradReqs.add(Course.findCourse("COMP446A"));
//        gradReqs.add(Course.findCourse("COMP448A"));
//        gradReqs.add(Course.findCourse("COMP451A"));
//        gradReqs.add(Course.findCourse("COMP452A"));
//        gradReqs.add(Course.findCourse("COMP475A"));
//    }

    public ArrayList<Course> getClassesLeftToTake() {
        ArrayList<Course> classesLeft = new ArrayList<>();
        for(Course gradCourse : gradReqs) {
            boolean courseTaken = false;
            for(Course takenCourse : completedCourses) {
                if(gradCourse.getCourseCode().equals(takenCourse.getCourseCode())) {
                    courseTaken = true;
                    break;
                }
            }
            if(!courseTaken) {
                classesLeft.add(gradCourse);
            }
        }
        return classesLeft;
    }

    public void addTakenCourse(Course course) {
        completedCourses.add(course);
        course.setCompletionColorComplete();
    }

    public void removeTakenCourse(Course course) {
        completedCourses.remove(course);

        if (gradReqs.contains(course)) { // if it's a gradReq
            course.setCompletionColorIncompleteGradReq();
        }
        else { // if it's not a gradReq
            course.setCompletionColorDefault();
        }
    }

    public ArrayList<Course> getCompletedCourses() {
        return completedCourses;
    }

    /**
     * Saves the user info (ID, name, major), and saved schedules to file "SavedCourses.txt"
     * First 3 lines are user ID, name, and major respectively
     * Every following line is for each saved schedule
     * The course code of each course in a schedule is saved on the line, seperated by ", "
     */
    public void SaveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("SavedCourses.txt"))) {
            //first 3 lines of the file are user info
            writer.write(userID + "\n");
            writer.write(name + "\n");
            writer.write(major + "\n");

            // Write the course codes of taken courses
            boolean firstCourse = true;

            for (Course course : completedCourses) {
                if (!firstCourse) {
                    writer.write(", "); // Separate courses by comma
                } else {
                    firstCourse = false;
                }
                String line = String.format("%s", course.getCourseCode());
                writer.write(line);
            }
            writer.newLine();

            // Write each course code for all the saved schedules to a file
            // Courses are separated by commas, schedules by newlines
            for(Schedule schedule : savedSchedules) {
                firstCourse = true;
                for (Course course : schedule.getCurrentCourses()) {
                    if (!firstCourse) {
                        writer.write(", "); // Separate courses by comma
                    } else {
                        firstCourse = false;
                    }
                    String line = String.format("%s", course.getCourseCode());
                    writer.write(line);
                }
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Loads the user information and saved schedules from "SavedCourses.txt"
     * if "SavedCourses.txt" is empty, create default user
     * assumes file is of the format specified in the SaveToFile javadoc
     */
    public static void LoadCoursesFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("SavedCourses.txt"))) {
            // Check that information has been saved to the file
            if(!reader.ready()) {
                Main.mainUser = new User();
                return;
            }

            // Read saved user info from the file
            int userID = Integer.parseInt(reader.readLine());
            String name = reader.readLine();
            String major = reader.readLine();
            Main.mainUser = new User(userID, name, major);

            // Read list of previously taken courses
            String line = reader.readLine();
            if(line != null && !line.isEmpty()) {
                String[] takenClassCodes = line.split(", ");
                for (String code : takenClassCodes) {
                    Course course = Course.findCourse(code);
                    Main.mainUser.addTakenCourse(course);
                }
            }

            // Read each saved Schedule from the file
            while ((line = reader.readLine()) != null) {
                Schedule schedule = new Schedule();
                String[] courseCodes = line.split(", ");
                for (String courseCode : courseCodes) {
                    Course course = Course.findCourse(courseCode);
                    if (course != null) {
                        schedule.addCourse(course);
                    } else {
                        System.out.println("Course not found in database: " + courseCode);
                    }
                }
                Main.mainUser.savedSchedules.add(schedule);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
