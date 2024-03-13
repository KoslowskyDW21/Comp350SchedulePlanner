package edu.gcc.comp350.smartest;

public class Main {
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
    public static void main(String[] args) {
        Search search = new Search();
        System.out.println("Never gonna give you up");
    }
}
