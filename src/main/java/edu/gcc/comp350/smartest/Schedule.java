package edu.gcc.comp350.smartest;

import java.time.LocalTime;
import java.util.ArrayList;
public class Schedule {
    private int totalCredits;
    private ArrayList<String> filledTimeslots;
    private ArrayList<Course> currentCourses;

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
            if(Overlaps(c, course)) {
                return 1;
            }
        }
        currentCourses.add(course);
        return 0;
    }

    public boolean Overlaps(Course a, Course b) {
        if((a.getDays().contains("M") && b.getDays().contains("M"))
                || (a.getDays().contains("T") && b.getDays().contains("T"))
                || (a.getDays().contains("W") && b.getDays().contains("W"))
                || (a.getDays().contains("R") && b.getDays().contains("R"))
                || (a.getDays().contains("F") && b.getDays().contains("F"))) {

            int crsStart = b.parseArr(b.getStartTimes());
            int courseStart = a.parseArr(a.getStartTimes());
            int crsEnd = b.parseArr(b.getEndTimes());
            int courseEnd = a.parseArr(a.getEndTimes());
            if (((courseStart >= crsStart && courseStart <= crsEnd)
                    || (courseEnd <= crsEnd && courseEnd >= crsStart))
                    ||((crsStart >= courseStart && crsStart <= courseEnd)
                    || (crsEnd <= courseEnd && crsEnd >= courseStart))) {
                return true;
            }
        }
        return false;
    }

    public void removeCourse(Course course) {
        currentCourses.remove(course);
    }

    public boolean testInSchedule(Course course) {
        return false;
    }

    public ArrayList<Course> createRecommendedSchedule(ArrayList<Course> classesLeft, int n) {
        ArrayList<Course> recommendedSchedule = new ArrayList<>();

        // Iterate through each course in the list
        for (Course currentCourse : classesLeft) {
            //System.out.print("Checking: " + currentCourse);
            // Check if the current course overlaps with any course already in the recommended schedule
            boolean overlaps = false;
            for (Course scheduledCourse : recommendedSchedule) {
                if (Overlaps(currentCourse, scheduledCourse)) {
                    //System.out.print(currentCourse + " overlaps " + scheduledCourse);
                    overlaps = true;
                    break;
                }
            }

            // If the current course does not overlap with any course in the recommended schedule, add it
            if (!overlaps) {
                //System.out.print("Adding: " + currentCourse);
                recommendedSchedule.add(currentCourse);

                // If we have found n courses without overlaps, break the loop
                if (recommendedSchedule.size() == n) {
                    break;
                }
            }
        }

        return recommendedSchedule;
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
