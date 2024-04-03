package edu.gcc.comp350.smartest;
import java.util.ArrayList;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

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

    /**
     * default constructor to be used for testing purposes
     */
    public Course() {
        this.courseCode = "TEST000A";
        this.name = "Test Course";
        this.professor = "Mr. Instructor";
        this.color = "white";
        this.description = "A default course for testing purposes";
        this.numCredits = 0;
        this.preReqs = new ArrayList<>();
        this.coReqs = new ArrayList<>();
        this.startTime = "8:00:00 am";
        this.endTime = "8:50:00 am";
        this.numSeats = 40;
        this.days = "MWF";
        this.semester = "Fall";
        this.department = "COMP";
        this.level = 100;
    }

    /**
     * constructor for testing purposes
     * allows for manual setting of each parameter in a course
     */
    public Course(String courseCode, String name, String professor, String color, String description,
                  int numCredits, ArrayList<Course[]> preReqs, ArrayList<Course[]> coReqs,
                  String startTime, String endTime, int numSeats, String days, String semester, String department, int level) {
        this.courseCode = courseCode;
        this.name = name;
        this.professor = professor;
        this.color = color;
        this.description = description;
        this.numCredits = numCredits;
        this.preReqs = preReqs;
        this.coReqs = coReqs;
        this.startTime = startTime;
        this.endTime = endTime;
        this.numSeats = numSeats;
        this.days = days;
        this.semester = semester;
        this.department = department;
        this.level = level;
    }

    /**
     * constructor designed to read a line from "2020-2021.csv"
     * @param excelLine a string of the format:
     *                  yr_cde,trm_cde,crs_comp1,crs_comp2,crs_comp3,crs_title,credit_hrs,
     *                  crs_capacity,crs_enrollment,monday_cde,tuesday_cde,
     *                  wednesday_cde,thursday_cde,friday_cde,begin_tim,end_tim,
     *                  last_name,first_name,preferred_name,comment_txt
     */
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
        String tempDays = "";
        String monday = tokens[9];
        String tuesday = tokens[10];
        String wednesday = tokens[11];
        String thursday = tokens[12];
        String friday = tokens[13];
        if(monday.equals("M")) {
            tempDays += "M";
        } else {
            tempDays += "_";
        }
        if(tuesday.equals("T")) {
            tempDays += "T";
        } else {
            tempDays += "_";
        }
        if(wednesday.equals("W")) {
            tempDays += "W";
        } else {
            tempDays += "_";
        }
        if(thursday.equals("R")) {
            tempDays += "R";
        } else {
            tempDays += "_";
        }
        if(friday.equals("F")) {
            tempDays += "F";
        } else {
            tempDays += "_";
        }
        days = tempDays;
        if (Integer.parseInt(tokens[1]) == 10) {
            semester = "Fall";
        } else { //tokens[5] == 30
            semester = "Spring";
        }
    }

    /**
     * method to find a course in the database based on a course code
     * @param courseCode the courseCode matching the course to search for
     * @return Course the first course found that has a matching courseCode
     * return null if no matching courseCode is found in the database
     */
    public static Course findCourse(String courseCode) {
        for (Course course : database) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        return null; // Course not found
    }

    private void changeColor(String color) {

    }

    public String getDays() {
        return days;
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

    public String getEndTimes() {return endTime;}

    public String getDescription() {
        return description;
    }

    public int getNumCredits() {
        return numCredits;
    }
    public float getStartTime() {
        String[] crsStartArr = getStartTimes().split(":");
        return parseArr(crsStartArr);
    }

    public float getEndTime() {
        String[] crsEndArr = getEndTimes().split(":");
        return parseArr(crsEndArr);
    }

    public float parseArr(String[] crsArr){
        if (crsArr[2].split(" ")[1].equals("PM") && !crsArr[0].equals("12")) {
            crsArr[0] = String.valueOf((Integer.parseInt(crsArr[0]) + 12) % 23);
        }
        float crsTime = Integer.parseInt(crsArr[0]);
        if (Integer.parseInt(crsArr[1]) != 0) {
            crsTime += ((float) Integer.parseInt(crsArr[1])) / 60;
        }
        return crsTime;
    }
}

