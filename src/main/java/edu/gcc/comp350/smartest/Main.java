package edu.gcc.comp350.smartest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<Course> courseList;

    public static void main(String[] args) {
        Search search = new Search();
        courseList = new ArrayList<>();

        System.out.println("Test");

        try {
            ParseClasses();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e);
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }

        for (Course course : courseList) {
            System.out.println(course.getName());
        }
    }

    private static void ParseClasses() throws IOException {
        Scanner scn = new Scanner(new File("2020-2021.csv"));
        scn.nextLine();
        while (scn.hasNext()) {
            //Course temp = new Course(scn.nextLine());
            //courseList.add(new Course(scn.nextLine()));
            Course.database.add(new Course(scn.nextLine()));
        }
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
}

