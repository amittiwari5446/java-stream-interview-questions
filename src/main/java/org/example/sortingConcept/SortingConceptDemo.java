package org.example.sortingConcept;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.*;

public class SortingConceptDemo {
    public static void main(String[] args){
        List<Integer> numbers = List.of(5, 2, 8, 1, 16, 9, 3, 11, 7, 4, 14,6);
        List<String> names = List.of("Charlie", "Alice", "Stuat", "Bob", "David", "Joe");
        List<String> namesWithNull = Arrays.asList("Charlie", null, "Alice", "Bob", null);

        record Employee(String name, String department, double salary, int age) {}
        List<Employee> employees = List.of(
                new Employee("Alice", "IT", 90000, 28),
                new Employee("Bob", "HR", 65000, 35),
                new Employee("Charlie", "IT", 110000, 32),
                new Employee("Diana", "Finance", 75000, 29),
                new Employee("Eve", "IT", 85000, 26)
        );


        //natural order
        List<Integer> naturalSort=numbers.stream()
                .sorted()
                .toList();
        System.out.println("Natural Sort: "+naturalSort);

        //alphabetically sort
        List<String> alphabeticSort=names.stream()
                .sorted()
                .toList();
        System.out.println("Alphabetically sort: "+alphabeticSort);

        //reverse natural order
        List<Integer> reverseNaturalSort=numbers.stream()
                .sorted(Collections.reverseOrder())
                .toList();
        System.out.println("Reverese Natural Sort: "+reverseNaturalSort);

        //sort by single field
        List<Employee> singleFieldSort=employees.stream()
                .sorted(Comparator.comparingDouble(Employee::salary))
                .toList();
        System.out.println("Single Field Sort: "+singleFieldSort);

        //sort by single field in reverse order
        List<Employee> singleFieldSortReverse=employees.stream()
                .sorted(Comparator.comparingDouble(Employee::salary).reversed())
                .toList();
        System.out.println("Single Field Sort in Reverse order: "+singleFieldSortReverse);

        //sort by single field 'name'
        List<Employee> sortByName=employees.stream()
                .sorted(Comparator.comparing(Employee::name))
                .toList();
        System.out.println("Sort By Name: "+sortByName);
        System.out.println();
        System.out.println();

        //Multiple field sorting
        //first by department then by salary in descending
        List<Employee> multiSort1=employees.stream()
                .sorted(Comparator.comparing(Employee::department)
                        .thenComparing(Comparator.comparingDouble(Employee::salary).reversed())
                )
                .toList();
        multiSort1.stream().forEach(emp->System.out.println(emp));
        System.out.println("Sort first by department then salary: "+multiSort1);

        //department -> age ascending -> salary descending
        List<Employee> multiSort2=employees.stream()
                .sorted(Comparator.comparing(Employee::department)
                        .thenComparing(Employee::age)
                        .thenComparing(Comparator.comparingDouble(Employee::salary).reversed())
                )
                .toList();
        System.out.println("sort by department then age then salary: "+multiSort2);

        //nulls first
        List<String> nullsFirst = namesWithNull.stream()
                .sorted(Comparator.nullsFirst(Comparator.naturalOrder()))
                .toList();
        System.out.println("nulls First: "+nullsFirst);

        //nulls last
        List<String> nullsLast = namesWithNull.stream()
                .sorted(Comparator.nullsLast(Comparator.naturalOrder()))
                .toList();
        System.out.println("nulls Last: "+nullsLast);

        //sort by names, null comes last
        List<Employee> nullsLast2 = employees.stream()
                .sorted(Comparator.comparing(Employee::name, Comparator.nullsLast(String::compareTo)))
                .toList();
        System.out.println("NullsLst for objectsL: "+nullsLast2);



    }
}
