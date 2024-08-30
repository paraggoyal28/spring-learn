package org.example.stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


public class GroupBy {


    public static List<Employee> getEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(27, "Parag"));
        employeeList.add(new Employee(27, "Shreya"));
        employeeList.add(new Employee(30, "Akash"));
        employeeList.add(new Employee(32, "Sandeep"));
        employeeList.add(new Employee(27, "Parag"));
        return employeeList;
    }

    public static Map<Integer, Set<Employee>> groupEmployeesByAge() {
        return getEmployees().stream().collect(Collectors.groupingBy(Employee::getAge, TreeMap::new, Collectors.toSet()));
    }

    public static Integer getMaxAge() {
        return getEmployees().stream().map(Employee::getAge).mapToInt(x -> x).summaryStatistics().getMax();
    }

    public static Integer getMinAge() {
        return getEmployees().stream().map(Employee::getAge).mapToInt(x -> x).summaryStatistics().getMin();
    }

    public static Double getAverageAge() {
        return getEmployees().stream().map(Employee::getAge).mapToInt(x -> x).summaryStatistics().getAverage();
    }

    public static Long getSumAge() {
        return getEmployees().stream().map(Employee::getAge).mapToInt(x -> x).summaryStatistics().getSum();
    }

    // get the slice of list of employees between startIdx and endIdx inclusive (1 idx based)
    public static List<Employee> getSlice(int startIdx, int endIdx) {
        return getEmployees().stream().skip(startIdx-1).limit(endIdx - startIdx + 1).collect(Collectors.toList());
    }

    public static List<Set<String>> getDuplicateAndUniqueNames() {
        Set<String> uniqueNames = new HashSet<>();
        Set<String> duplicateNames = getEmployees().stream().map(Employee::getName).filter(name -> !uniqueNames.add(name)).collect(Collectors.toSet());
        return List.of(uniqueNames, duplicateNames);
    }

    public static List<String> getDuplicateNames() {
        Map<String, Long> groupByCount = getEmployees().stream().map(Employee::getName).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return groupByCount.entrySet().stream().filter(entry -> entry.getValue() > 1).map(Map.Entry::getKey).collect(Collectors.toList());
    }


    public static void main(String args[]) {
//        Map<Integer, Set<Employee>> employeesGroupByAge = groupEmployeesByAge();
//        System.out.println(employeesGroupByAge);
//        System.out.printf("minimum age %d\n", getMinAge());
//        System.out.printf("maximum age %d\n", getMaxAge());
//        System.out.printf("average age %f\n", getAverageAge());
//        System.out.printf("sum of all ages %d\n", getSumAge());
//        System.out.println("List of employees between idx 2 to 4");
//        System.out.println(getSlice(2, 4));
//        System.out.println("List of employees between 1 to 3");
//        System.out.println(getSlice(1, 3));
//        System.out.println("List of employees between 1 to 5");
//        System.out.println(getSlice(1, 5));
//        System.out.println("Unique Names");
//        System.out.println(getDuplicateAndUniqueNames().get(0));
        System.out.println("Duplicate Names");
//        System.out.println(getDuplicateAndUniqueNames().get(1));
        System.out.println(getDuplicateNames());
    }

}
