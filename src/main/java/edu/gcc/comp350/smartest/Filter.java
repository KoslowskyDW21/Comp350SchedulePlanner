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

        filterVals += "Credits: " + getCredits() + " ";
        filterVals += "Start Time: " + getStartTime() + " ";
        filterVals += "End Time: " + getEndTime() + " ";
        filterVals += "Level Min: " + getLevelMin() + " ";
        filterVals += "Level Max: " + getLevelMax() + " ";
        filterVals += "Prof Name: " + getProfName() + " ";
        filterVals += "Department: " + getDepartment() + " ";

        return filterVals;
    }

}
