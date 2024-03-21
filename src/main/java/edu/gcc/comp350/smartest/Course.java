package edu.gcc.comp350.smartest;
import java.util.ArrayList;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Course {
    public static ArrayList<Course> database = new ArrayList<>();
    private final String courseCode;
    private final String department;
    private final int level;
    private final String name;
    private final String professor;
    private String color;
    private final String startTime;
    private final String endTime;
    private final String description;
    private final int numCredits;
    private final ArrayList<Course[]> preReqs;
    private final ArrayList<Course[]> coReqs;
    private final int numSeats;
    private final String days;
    private final String semester;

    public ArrayList<Course[]> getPreReqs() {
        return preReqs;
    }

    public ArrayList<Course[]> getCoReqs() {
        return coReqs;
    }


//    public Course(String courseCode, String name, String professor, String color, String description,
//                  int numCredits, ArrayList<Course[]> preReqs, ArrayList<Course[]> coReqs,
//                  String startTime, String endTime, int numSeats, String days) {
//        this.courseCode = courseCode;
//        this.name = name;
//        this.professor = professor;
//        this.color = color;
//        this.description = description;
//        this.numCredits = numCredits;
//        this.preReqs = preReqs;
//        this.coReqs = coReqs;
//        this.startTime = startTime;
//        this.endTime = endTime;
//        this.numSeats = numSeats;
//        this.days = days;
//    }

    public Course(String excelLine) {
        String[] tokens = excelLine.split(",");
        courseCode = tokens[2] + tokens[3] + tokens[4];
        department = tokens[2];
        level = Integer.parseInt(tokens[3]);
        name = tokens[5];
        professor = tokens[17] + " " + tokens[16];
        color = "white";
        if (tokens.length >= 20) {
            description = tokens[19];
        } else {
            description = "";
        }
        numCredits = Integer.parseInt(tokens[6]);
        numSeats = Integer.parseInt(tokens[7]);
        preReqs = new ArrayList<>();
        coReqs = new ArrayList<>();
        startTime = tokens[14];
        endTime = tokens[15];
        days = tokens[9] + tokens[10] + tokens[11] + tokens[12] + tokens[13];
        if (Integer.parseInt(tokens[1]) == 10) {
            semester = "Fall";
        } else { //tokens[5] == 30
            semester = "Spring";
        }
    }

    public Course(String line, boolean differentiate) {
        String[] tokens = line.split(",");
        courseCode = tokens[0];
        department = tokens[1];
        level = Integer.parseInt(tokens[2]);
        name = tokens[3];
        professor = tokens[4];
        startTime = tokens[5];
        endTime = tokens[6];
        description = tokens[7];
        numCredits = Integer.parseInt(tokens[8]);
        numSeats = Integer.parseInt(tokens[9]);
        days = tokens[9];
        semester = tokens[10];
        preReqs = new ArrayList<>();
        coReqs = new ArrayList<>();
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

    public String getStartTimes() {
        return startTime;
    }

    public String getEndTimes() {
        return endTime;
    }

    public String getDescription() {
        return description;
    }

    public String getDays() {
        return days;
    }

    public String getSemester() {
        return semester;
    }

    public int getNumCredits() {
        return numCredits;
    }

    public int getNumSeats() {
        return numSeats;
    }
}

