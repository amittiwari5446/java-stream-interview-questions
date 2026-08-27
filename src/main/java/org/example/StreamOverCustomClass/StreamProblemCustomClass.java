package org.example.StreamOverCustomClass;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamProblemCustomClass {
    public static void main(String[] args){
        List<Employee> employees= Arrays.asList(
                new Employee(1,"Amit","IT","Gurugram",90000,27),
                new Employee(2,"Riya","IT","Banglore",70000,28),
                new Employee(3,"Abha","HR","Banglore",69000,26),
                new Employee(4,"Ravi","Finance","Mumbai",110000,30),
                new Employee(5,"Vibhuti","IT","Gurugram",88000,29),
                new Employee(6,"Chetan","Finance","Mumbai",79000,28),
                new Employee(7,"John","HR","Mumbai",91000,27),
                new Employee(8,"Karan","HR","Gurugram",74000,29),
                new Employee(9,"Meghna","IT","Gurugram",83000,28),
                new Employee(10,"Saurabh","Finance","Bangolore",102000,30)
        );

        //count employee by department
        Map<String,Long> countByDepartment=employees.stream()
                .collect(Collectors.groupingBy(
                        e->e.getDepartment(),
                        Collectors.counting()
                ));
        System.out.println("Count BY Department: "+countByDepartment);

        //total salary by department
        Map<String,Double>salaryByDepartment = employees.stream()
                .collect(Collectors.groupingBy(
                        e->e.getDepartment(),
                        Collectors.summingDouble(e->e.getSalary())
                ));
        System.out.println("Salary By Department: "+salaryByDepartment);

        Map<String, Double> averageByDepartment=employees.stream()
                .collect(Collectors.groupingBy(
                        e->e.getDepartment(),
                        Collectors.averagingDouble(e->e.getSalary())
                ));
        System.out.println("Average By Department: "+averageByDepartment);

        //Maximum salary of each department
        Map<String, Optional<Double>> maxSalary=employees.stream()
                .collect(Collectors.groupingBy(
                        e->e.getDepartment(),
                        Collectors.mapping(
                                e->e.getSalary(),
                                Collectors.maxBy(Double::compareTo)
                        )
                ));
        System.out.println("Max salary by department: "+maxSalary);

        //Highest paid employee of each department
        Map<String, Optional<Employee>> highestPaid=employees.stream()
                .collect(Collectors.groupingBy(
                        e->e.getDepartment(),
                        Collectors.maxBy(
                                Comparator.comparingDouble(e->e.getSalary())
                        )
                ));
        System.out.println("Highest paid employee of each department: "+highestPaid);

        //lowest paid employee of each department
        Map<String, Optional<Employee>> lowestPaid=employees.stream()
                .collect(Collectors.groupingBy(
                        e->e.getDepartment(),
                        Collectors.minBy(
                                Comparator.comparingDouble(e->e.getSalary())
                        )
                ));
        System.out.println("Lowest paid employee in each department: "+lowestPaid);

        //Group employees by department
        Map<String, List<Employee>> employeesByDepartment=employees.stream()
                .collect(Collectors.groupingBy(
                        e->e.getDepartment()
                ));
        System.out.println("Group employee by department: "+employeesByDepartment);

        //employee names by department
        Map<String, List<String>> employeeNameByDepartment=employees.stream()
                .collect(Collectors.groupingBy(
                        e->e.getDepartment(),
                        Collectors.mapping(
                                e->e.getName(),
                                Collectors.toList()
                        )
                ));
        System.out.println("employee name by department: "+employeeNameByDepartment);

        //convert List<Employees> to map<Integer, Employee>
        Map<Integer,Employee> employeeMap=employees.stream()
                .collect(Collectors.toMap(
                        e->e.getId(),
                        Function.identity()
                ));
        System.out.println("list to Map: "+employeeMap);

        //employee name and salary map
        Map<String,Double> salaryMap=employees.stream()
                .collect(Collectors.toMap(
                        e->e.getName(),
                        e->e.getSalary(),
                        (oldValue,newValue)->oldValue //if map has duplicate keys then it will keep old value instead of throwing illegalStateException for duplicate keys
                ));
        System.out.println("Name and Salary map: "+salaryMap);

        Map<Boolean,List<Employee>> partitionedOnSalary=employees.stream()
                .collect(Collectors.partitioningBy(
                        e->e.getSalary()>80000
                ));
        System.out.println("Partitioning employee based on salary: "+partitionedOnSalary);

        Optional<Double> secondHighestSalary=employees.stream()
                .map(e->e.getSalary())
                .distinct()
                .sorted(Collections.reverseOrder())
                .skip(1)
                .findFirst();
        System.out.println("Second Highest salary: "+secondHighestSalary);

        //top 3 highest paid salary
        List<Employee> topThreeHighestSalary=employees.stream()
                .sorted(
                        Comparator.comparingDouble(Employee::getSalary).reversed()
                )
                .limit(3)
                .collect(Collectors.toList());
        System.out.println("Top 3 highest salary: "+topThreeHighestSalary);


    }
}


