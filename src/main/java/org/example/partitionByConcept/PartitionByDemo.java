package org.example.partitionByConcept;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PartitionByDemo {
    public static void main(String[] args){
        /*
            What is partitioningBy?
                Special case of groupingBy that always partitions the stream into exactly two groups.
                The classifier is a Predicate<T> (returns true or false).
                Result is always Map<Boolean, List<T>>.
                Key true → elements that match the predicate
                Key false → elements that do not match the predicate
        */

        //Basic
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Map<Boolean,List<Integer>> evenOdd=numbers.stream()
                .collect(Collectors.partitioningBy(n->n%2==0));
        System.out.println("Even odd partition: "+evenOdd);

        //for custom objects
        record Employee(String name, String department, double salary, int age) {}

        List<Employee> employees = List.of(
                new Employee("Alice", "IT", 90000, 28),
                new Employee("Bob", "HR", 65000, 35),
                new Employee("Charlie", "IT", 110000, 32),
                new Employee("Diana", "Finance", 75000, 29),
                new Employee("Eve", "IT", 85000, 26)
        );

        Map<Boolean,List<Employee>> byHighSalary=employees.stream()
                .collect(Collectors.partitioningBy(emp->emp.salary>80000));
        System.out.println("partition by salary: "+byHighSalary);

        //partitionBy and downstream Collector
        Map<Boolean, Long> countBySalary=employees.stream()
                .collect(Collectors.partitioningBy(
                        emp->emp.salary>80000,
                        Collectors.counting()
                ));
        System.out.println("Count By Salary: "+countBySalary);

        //average salary in each partition
        Map<Boolean,Double> averageInPartition= employees.stream()
                .collect(Collectors.groupingBy(
                        emp->emp.salary>80000,
                        Collectors.averagingDouble(Employee::salary)
                ));
        System.out.println("Average In Partition: "+averageInPartition);

        //get only names in each partition

    }
}
