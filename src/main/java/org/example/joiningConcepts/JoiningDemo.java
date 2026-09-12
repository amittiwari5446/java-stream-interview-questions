package org.example.joiningConcepts;

import org.example.StreamOverCustomClass.Employee;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JoiningDemo {
    public static void main(String[] args){
        List<String> names = List.of("Alice", "Bob", "Charlie");

        //simple joining
        String simpleJoining = names.stream()
                .collect(Collectors.joining());
        System.out.println("Simple Join: "+simpleJoining);

        //using simple  delimiter
        String usingDelimiter=names.stream()
                .collect(Collectors.joining(","));
        System.out.println("using Delimiter: "+usingDelimiter);

        String usingDelimiter2=names.stream()
                .collect(Collectors.joining("-"));
        System.out.println("using Delimiter2: "+usingDelimiter2);

        //joining(delimiter, prefix, suffix)
        String delimiterPrefixSuffix = names.stream()
                .collect(Collectors.joining(", ","[","]"));
        System.out.println("Delimiter Prefix suffix: "+delimiterPrefixSuffix);

        String delimiterPrefixSuffix2 = names.stream()
                .collect(Collectors.joining(" | ","Names: ","."));
        System.out.println("Delimiter Prefix suffix2: "+delimiterPrefixSuffix2);


        //joining with objects (using 'map' first)
        record Employee(String name, String department, double salary) {}

        List<Employee> employees = List.of(
                new Employee("Alice", "IT", 90000),
                new Employee("Bob", "HR", 65000),
                new Employee("Charlie", "IT", 110000)
        );

        String joiningObject=employees.stream()
                .map(Employee::name)
                .collect(Collectors.joining(", "));
        System.out.println("Joining Objects: "+joiningObject);

        String joiningObject2=employees.stream()
                .map(emp->emp.name + " (" + emp.department + ")")
                .collect(Collectors.joining(", ", "Employees: ",""));
        System.out.println("Joining Objects2: "+joiningObject2);

        //joining with Filtering + Mapping
        String itName = employees.stream()
                .filter(emp->emp.department.equals("IT"))
                .map(Employee::name)
                .collect(Collectors.joining(", ","IT Team: ","."));
        System.out.println("IT Names: "+itName);

        //joining numbers
        List<Integer> list2=List.of(12,54,23,78,43,90,12,23,56,61);
        String joinNumbers = list2.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" - "));
        System.out.println("Joining Numbers: "+joinNumbers);

        String csv = employees.stream()
                .map(e -> e.name() + "," + e.department() + "," + e.salary())
                .collect(Collectors.joining("\n", "Name,Department,Salary\n", ""));
        System.out.println("CSV file: "+csv);

        //joining and groupingBy
        Map<String,String> grouping=employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.mapping(
                                Employee::name,
                                Collectors.joining(", ")
                        )
                ));
        System.out.println("Joining and GroupingBy: "+grouping);

    }
}
