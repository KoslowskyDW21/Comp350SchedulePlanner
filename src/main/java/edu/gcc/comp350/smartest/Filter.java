package edu.gcc.comp350.smartest;

public class Filter {
    // filter class with apply to results method that all filters inherit from

    private int credits;
    private int startTime;
    private int endTime;
    private int levelMin;
    private int levelMax;
    private String profName;
    private String department;



    public Filter(){
        this.credits = -1;
        this.startTime = 800;
        this.endTime = 2100;
        this.levelMin = 100;
        this.levelMax = 600;
        this.profName = "";
        this.department = "";
    }

    public static void addDepartmentFilter(Search search, String department) {
        Filter departmentFilter = search.getActiveFilters();
        departmentFilter.setDepartment(department);
        search.modifyFilter(departmentFilter);
    }

    public static void removeDepartmentFilter(Search search) {
        Filter departmentFilter = search.getActiveFilters();
        departmentFilter.setDepartment("");
        search.modifyFilter(departmentFilter);
    }

    public static void addProfessorFilter(Search search, String professor) {
        Filter professorFilter = search.getActiveFilters();
        professorFilter.setProfName(professor);
        search.modifyFilter(professorFilter);
    }

    public static void removeProfessorFilter(Search search) {
        Filter professorFilter = search.getActiveFilters();
        professorFilter.setProfName("");
        search.modifyFilter(professorFilter);
    }

    public static void addLevelFilter(Search search, int upper, int lower) {
        Filter levelFilter = search.getActiveFilters();
        levelFilter.setLevelMax(upper);
        levelFilter.setLevelMin(lower);
        search.modifyFilter(levelFilter);
    }

    public static void removeLevelFilter(Search search) {
        Filter levelFilter = search.getActiveFilters();
        levelFilter.setLevelMax(600);
        levelFilter.setLevelMin(100);
        search.modifyFilter(levelFilter);
    }

    public static void addStartTimeFilter(Search search, int startTime) {
        Filter startTimeFilter = search.getActiveFilters();
        startTimeFilter.setStartTime(startTime);
        search.modifyFilter(startTimeFilter);
    }

    public static void removeStartTimeFilter(Search search) {
        Filter startTimeFilter = search.getActiveFilters();
        startTimeFilter.setEndTime(800);
        search.modifyFilter(startTimeFilter);
    }

    public static void addEndTimeFilter(Search search, int endTime) {
        Filter endTimeFilter = search.getActiveFilters();
        endTimeFilter.setEndTime(endTime);
        search.modifyFilter(endTimeFilter);
    }

    public static void removeEndTimeFilter(Search search) {
        Filter endTimeFilter = search.getActiveFilters();
        endTimeFilter.setEndTime(2100);
        search.modifyFilter(endTimeFilter);
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getLevelMin() {
        return levelMin;
    }

    public void setLevelMin(int levelMin) {
        this.levelMin = levelMin;
    }

    public int getLevelMax() {
        return levelMax;
    }

    public void setLevelMax(int levelMax) {
        this.levelMax = levelMax;
    }

    public String getProfName() {
        return profName;
    }

    public void setProfName(String profName) {
        this.profName = profName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String filterToString() {
        String filterVals = "";

        filterVals += "Credits: " + getCredits() + " \n";
        filterVals += "Start Time: " + getStartTime() + " \n";
        filterVals += "End Time: " + getEndTime() + " \n";
        filterVals += "Level Min: " + getLevelMin() + " \n";
        filterVals += "Level Max: " + getLevelMax() + " \n";
        filterVals += "Prof Name: " + getProfName() + " \n";
        filterVals += "Department: " + getDepartment() + " \n";

        return filterVals;
    }

}
