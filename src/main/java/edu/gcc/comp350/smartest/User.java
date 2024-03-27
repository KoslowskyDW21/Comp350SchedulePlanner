package edu.gcc.comp350.smartest;

import java.io.*;
import java.util.ArrayList;
public class User {
    private int userID;
    private String name;
    private String major;
    public ArrayList<Schedule> savedSchedules;

    private ArrayList<Course> gradReqs;

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

    public ArrayList<Course> getGradReqs() {
        return gradReqs;
    }

    public User(int userID, String name, String major) {
        this.userID = userID;
        this.name = name;
        this.major = major;
        this.gradReqs = new ArrayList<>();
        this.savedSchedules = new ArrayList<>();
    }

    public User() {
        this.userID = 0;
        this.name = "John Student";
        this.major = "Computer Science";
        this.gradReqs = new ArrayList<>();
        this.savedSchedules = new ArrayList<>();
    }

    //TODO: Check that there are no schedule conflicts or something

    public void changeName(String newName) {

    }

    public void changeMajor(String newMajor) {

    }

    public void setGradReqs(ArrayList<Course> req) {

    }

    public void SaveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("SavedCourses.txt"))) {
            //first 3 lines of the file are user info
            writer.write(userID + "\n");
            writer.write(name + "\n");
            writer.write(major + "\n");

            // Write each course code for all the saved schedules to a file
            // Courses are separated by commas, schedules by newlines
            for(Schedule schedule : savedSchedules) {
                boolean firstCourse = true;
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

    public static void LoadCoursesFromFile() throws Exception {
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

            // Read each saved Schedule from the file
            String line;
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
        }
    }
}
