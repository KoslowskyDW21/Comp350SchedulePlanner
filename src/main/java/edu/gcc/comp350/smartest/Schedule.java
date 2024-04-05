package edu.gcc.comp350.smartest;

import java.time.LocalTime;
import java.util.ArrayList;
public class Schedule {
    private int totalCredits;
    private ArrayList<String> filledTimeslots;
    private ArrayList<Course> currentCourses = new ArrayList<>();

    public ArrayList<Course> getCurrentCourses() {
        return currentCourses;
    }

    public Schedule() {
        this.totalCredits = 0;
        this.filledTimeslots = new ArrayList<>();
        this.currentCourses = new ArrayList<>();
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public int addCourse(Course course) {
        for(Course c : currentCourses) {
            if((c.getDays().contains("M") && course.getDays().contains("M"))|| (c.getDays().contains("T") && course.getDays().contains("T")) || (c.getDays().contains("W") && course.getDays().contains("W")) || (c.getDays().contains("R") && course.getDays().contains("R")) || (c.getDays().contains("F") && course.getDays().contains("F"))) {
                /*String[] crsArr = course.getStartTimes().split(":");
                if (crsArr[2].split(" ")[1].equals("PM") && !crsArr[0].equals("12")) {
                    crsArr[0] = String.valueOf((Integer.parseInt(crsArr[0]) + 12));
                }
                float crsStart = Integer.parseInt(crsArr[0]);
                if (Integer.parseInt(crsArr[1]) != 0) {
                    crsStart += ((float) Integer.parseInt(crsArr[1])) / 60;
                }*/
                int crsStart = course.parseArr(course.getStartTimes());


                /*String[] courseArr = c.getStartTimes().split(":");
                if (courseArr[2].split(" ")[1].equals("PM") && !courseArr[0].equals("12")) {
                    courseArr[0] = String.valueOf((Integer.parseInt(courseArr[0]) + 12));
                }
                float courseStart = Integer.parseInt(courseArr[0]);
                if (Integer.parseInt(courseArr[1]) != 0) {
                    courseStart += ((float) Integer.parseInt(courseArr[1])) / 60;
                }*/
                int courseStart = c.parseArr(c.getStartTimes());

                /*String[] crsEndArr = course.getEndTimes().split(":");
                if (crsEndArr[2].split(" ")[1].equals("PM") && !crsEndArr[0].equals("12")) {
                    crsEndArr[0] = String.valueOf((Integer.parseInt(crsEndArr[0]) + 12) % 23);
                }
                float crsEnd = Integer.parseInt(crsEndArr[0]);

                if (Integer.parseInt(crsEndArr[1]) != 0) {
                    crsEnd += ((float) Integer.parseInt(crsEndArr[1])) / 60;
                }*/
                int crsEnd = course.parseArr(course.getEndTimes());

                /*String[] courseEndArr = c.getEndTimes().split(":");
                if (courseEndArr[2].split(" ")[1].equals("PM") && !courseEndArr[0].equals("12")) {
                    courseEndArr[0] = String.valueOf((Integer.parseInt(courseEndArr[0]) + 12) % 23);
                }
                float courseEnd = Integer.parseInt(courseEndArr[0]);

                if (Integer.parseInt(courseEndArr[1]) != 0) {
                    courseEnd += ((float) Integer.parseInt(courseEndArr[1])) / 60;
                }*/
                int courseEnd = c.parseArr(c.getEndTimes());
                if (((courseStart >= crsStart && courseStart <= crsEnd) || (courseEnd <= crsEnd && courseEnd >= crsStart))||((crsStart >= courseStart && crsStart <= courseEnd) || (crsEnd <= courseEnd && crsEnd >= courseStart))) {
                    return 1;
                }
            }
        }
        currentCourses.add(course);
        return 0;
    }

    public void removeCourse(Course course) {
        currentCourses.remove(course);
    }

    public boolean testInSchedule(Course course) {
        return false;
    }

    public void createRecommendedSchedule() {

    }

    public String toString(){
        return null;
    }

    public void SaveSchedule(){

    }

    private String popup() {
        return "Course Overlaps with one already in Schedule";
    }
}
