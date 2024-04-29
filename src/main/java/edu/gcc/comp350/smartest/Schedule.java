package edu.gcc.comp350.smartest;

import java.time.LocalTime;
import java.util.ArrayList;
public class Schedule {
    private ArrayList<String> filledTimeslots;
    private ArrayList<Course> currentCourses;

    public ArrayList<Course> getCurrentCourses() {
        return currentCourses;
    }

    public Schedule() {
        this.filledTimeslots = new ArrayList<>();
        this.currentCourses = new ArrayList<>();
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

    public static boolean Overlaps(Course a, Course b) {
        if((a.getDays().contains("M") && b.getDays().contains("M"))
                || (a.getDays().contains("T") && b.getDays().contains("T"))
                || (a.getDays().contains("W") && b.getDays().contains("W"))
                || (a.getDays().contains("R") && b.getDays().contains("R"))
                || (a.getDays().contains("F") && b.getDays().contains("F"))) {

            int crsStart = b.parseArr(b.getStartTimes());
            int courseStart = a.parseArr(a.getStartTimes());
            int crsEnd = b.parseArr(b.getEndTimes());
            int courseEnd = a.parseArr(a.getEndTimes());
            return ((courseStart >= crsStart && courseStart <= crsEnd)
                    || (courseEnd <= crsEnd && courseEnd >= crsStart))
                    || ((crsStart >= courseStart && crsStart <= courseEnd)
                    || (crsEnd <= courseEnd && crsEnd >= courseStart));
        }
        return false;
    }

    private static boolean overlapsWithSchedule(Course course, ArrayList<Course> schedule) {
        for (Course scheduledCourse : schedule) {
            if (Overlaps(course, scheduledCourse)) {
                return true;
            }
        }
        return false;
    }

    public void removeCourse(Course course) {
        currentCourses.remove(course);
    }

    /**
     * Greedy algorithm for generating schedules
     * Loops through classesLeft, added each class to the schedule if it does not overlap with other classes or go over the credit load
     * Runtime - O(n)
     * @param classesLeft the list of classes eligible for being added to the schedule
     * @param n the number of credits the user wants their schedule to total to
     * @return the recommended schedule that results from the algorithm
     */
    public static ArrayList<Course> createRecommendedSchedule(ArrayList<Course> classesLeft, int n) {
        ArrayList<Course> recommendedSchedule = new ArrayList<>();
        int currentNumCredits = 0;
        // Iterate through each course in the list
        for (Course currentCourse : classesLeft) {
            // Check that the current course will not go over the credit load
            // Also check that the current course does not overlap anything in the schedule
            boolean overlaps = currentNumCredits + currentCourse.getNumCredits() > n
                    || overlapsWithSchedule(currentCourse, recommendedSchedule);
            // If the current course does not overlap with any course in the recommended schedule, add it
            if (!overlaps) {
                recommendedSchedule.add(currentCourse);
                currentNumCredits += currentCourse.getNumCredits();
                // If we have n credits, break the loop
                if (currentNumCredits == n) {
                    break;
                }
            }
        }
        return recommendedSchedule;
    }

    /**
     * method that initializes the backtrack method, which explores all possible combinations of schedules
     * Runtime - O(2^n)
     * @param classesLeft the list of classes eligible for being added to the schedule
     * @param n the number of credits the user wants their schedule to total to
     * @return the recommended schedule that results from the algorithm
     */
    public static ArrayList<Course> createRecommendedScheduleSlow(ArrayList<Course> classesLeft, int n) {
        ArrayList<Course> recommendedSchedule = new ArrayList<>();
        ArrayList<Course> currentSchedule = new ArrayList<>();
        int[] maxCredits = new int[]{0}; // Using an array to pass as reference

        backtrack(classesLeft, n, 0, currentSchedule, recommendedSchedule, maxCredits);

        return recommendedSchedule;
    }

    /**
     * recursive method to explore all possible schedules that can be made from classesLeft
     * each iteration explores either adding a course to a schedule or not adding it,
     * leading to 2 branches off of every call in the worst case, which is where the O(2^n) runtime comes from
     * @param classesLeft the list of classes eligible for being added to the schedule
     * @param n the number of credits the user wants their schedule to total to
     * @param index the current index of classesLeft that is being explored
     * @param currentSchedule the schedule that has been constructed so far
     * @param recommendedSchedule the best schedule that has been found so far (closest to n credits without going over)
     * @param maxCredits the number of credits in the recommended schedule
     */
    private static void backtrack(ArrayList<Course> classesLeft, int n, int index,
                                  ArrayList<Course> currentSchedule, ArrayList<Course> recommendedSchedule,
                                  int[] maxCredits) {
        // Base case (we reach the end of classesLeft, or we have the required number of credits)
        if (index == classesLeft.size() || getTotalCredits((currentSchedule)) == n) {
            // If this iteration has the largest number of credits found so far, make this the recommended schedule
            if (getTotalCredits(currentSchedule) > maxCredits[0]) {
                maxCredits[0] = getTotalCredits(currentSchedule);
                recommendedSchedule.clear();
                recommendedSchedule.addAll(currentSchedule);
            }
            return;
        }

        // Explore adding the course at index to the schedule
        Course currentCourse = classesLeft.get(index);
        if (!overlapsWithSchedule(currentCourse, currentSchedule) &&
                getTotalCredits(currentSchedule) + currentCourse.getNumCredits() <= n) {
            currentSchedule.add(currentCourse);
            backtrack(classesLeft, n, index + 1, currentSchedule, recommendedSchedule, maxCredits);
            currentSchedule.removeLast(); // Backtrack
        }

        // Explore not adding the course at index to the schedule
        backtrack(classesLeft, n, index + 1, currentSchedule, recommendedSchedule, maxCredits);
    }

    private static int getTotalCredits(ArrayList<Course> schedule) {
        int totalCredits = 0;
        for (Course course : schedule) {
            totalCredits += course.getNumCredits();
        }
        return totalCredits;
    }

    public String toString(){
        return null;
    }

    private String popup() {
        return "Course Overlaps with one already in Schedule";
    }
}
