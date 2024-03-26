package edu.gcc.comp350.smartest;

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

    public void addCourse(Course course) throws Exception {
        for (Course crs : currentCourses) {
            String[] crsArr = crs.getStartTimes().split(":");
            float crsStart = Integer.parseInt(crsArr[0]) % 12;
            if(Integer.parseInt(crsArr[1]) != 0){
                crsStart += ((float) Integer.parseInt(crsArr[1])) /60;
            }

            String[] courseArr = course.getStartTimes().split(":");
            float courseStart = Integer.parseInt(courseArr[0]) % 12;
            if(Integer.parseInt(courseArr[1]) != 0){
                courseStart += ((float) Integer.parseInt(courseArr[1])) /60;
            }

            String[] crsEndArr = crs.getEndTimes().split(":");
            float crsEnd = Integer.parseInt(crsEndArr[0]) %12;
            if(Integer.parseInt(crsEndArr[1]) != 0){
                crsEnd += ((float) Integer.parseInt(crsEndArr[1])) /60;
            }

            String[] courseEndArr = course.getEndTimes().split(":");
            float courseEnd = Integer.parseInt(courseEndArr[0]) %12;
            if(Integer.parseInt(courseArr[1]) != 0){
                courseEnd += ((float) Integer.parseInt(courseEndArr[1])) /60;
            }
            if((courseStart >= crsStart && courseStart <= crsEnd) || (courseEnd <= crsEnd && courseEnd >= crsStart)){
                throw new Exception("Course Overlaps with one already in Schedule"); //replace with popup in GUI
            }
        }
        currentCourses.add(course);

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

    //kate is making changes :)


}
