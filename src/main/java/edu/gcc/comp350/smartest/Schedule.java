package edu.gcc.comp350.smartest;

import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
public class Schedule {
    private int totalCredits;
    private ArrayList<String> filledTimeslots;
    private ArrayList<Course> currentCourses;

    public Schedule() {
        this.totalCredits = 0;
        this.filledTimeslots = new ArrayList<>();
        this.currentCourses = new ArrayList<>();
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public void addCourse(Course course) {

    }

    public void removeCourse(Course course) {

    }

    public boolean testInSchedule(Course course) {
        return false;
    }

    public void createRecommendedSchedule() {

    }

    public String toString(){
        return null;
    }

    public void SaveSchedule() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("SavedCourses.txt"))) {
            for (Course course : currentCourses) {
                String line = String.format("%s,%s,%d,%s,%s,%s,%s,%s,%d,%d,%s,%s",
                        course.getCourseCode(),
                        course.getDepartment(),
                        course.getLevel(),
                        course.getName(),
                        course.getProfessor(),
                        course.getStartTimes(),
                        course.getEndTimes(),
                        course.getDescription(),
                        course.getNumCredits(),
                        course.getNumSeats(),
                        course.getDays(),
                        course.getSemester());
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadCoursesFromFile() {
        String filename = "SavedCourses.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Course course = new Course(line, true);
                System.out.println(course);
                currentCourses.add(course);
            }
        } catch (IOException | NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }
}
