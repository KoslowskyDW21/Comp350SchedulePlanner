package edu.gcc.comp350.smartest;

import java.time.LocalTime;

// hi guys!!
public class Course {
    private final String courseCode;
    private final String department;
    private final int level;
    private final String name;
    private final String professor;
    private String color;
    //private final LocalTime[] timeSlot;
    private final int[] startTimes;
    private final String description;
    private final int numCredits;
    private final Course[] preReqs;
    private final Course[] coReqs;


    /*public LocalTime[] getTimeSlot() {
        return timeSlot;
    }*/

    public Course[] getPreReqs() {
        return preReqs;
    }

    public Course[] getCoReqs() {
        return coReqs;
    }

    public Course(String courseCode, String department, int level, String name, String professor, String color, LocalTime[] timeSlot,
                  String description, int numCredits, Course[] preReqs, Course[]coReqs) {
        this.courseCode = courseCode;
        this.department = department;
        this.level = level;
        this.name = name;
        this.professor = professor;
        this.color = color;
        //this.timeSlot = timeSlot;
        this.description = description;
        this.numCredits = numCredits;
        this.preReqs = preReqs;
        this.coReqs = coReqs;
        startTimes = new int[0];
    }

    private void changeColor(String color) {

    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getDepartment() {
        return department;
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public String getProfessor() {
        return professor;
    }

    public String getColor() {
        return color;
    }

    public int[] getStartTimes() {
        return startTimes;
    }

    public String getDescription() {
        return description;
    }

    public int getNumCredits() {
        return numCredits;
    }
}
