package example.inheritance;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/* Imagine we have a list of employees in a company. Each employee
has a
Map
Key: Department Name (String)
Value: Name of the employee with highest salary in their department
 */
record Employee(String name, String department, double salary) {}

public class EmployeeService {
    public static Map<String, String> getTopEarnerPerDepartment(List<Employee> employees) {
        return employees.stream().collect(Collectors.groupingBy(
                Employee::department,
                Collectors.collectingAndThen(
                        Collectors.maxBy(Comparator.comparingDouble(Employee::salary)),
                        opt -> opt.map(Employee::name).orElse("")
                )
        ));
    }
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>(List.of(
                new Employee("Abc", "HR", 1000),
                new Employee("Def", "IT", 2000),
                new Employee("Pqr", "HR", 3000)
        ));
        System.out.println(getTopEarnerPerDepartment(employees));
    }
}
