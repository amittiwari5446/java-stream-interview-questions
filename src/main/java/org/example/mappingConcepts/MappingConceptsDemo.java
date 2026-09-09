package org.example.mappingConcepts;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MappingConceptsDemo {
    public static void main(String[] args){
        List<String> names = List.of("Alice", "Bob", "Charlie");

        //1. one to one transform
        List<Integer> namesLength = names.stream()
                .map(String::length)
                .toList();
        System.out.println("one to one: "+namesLength);

        List<String> upperCase=names.stream()
                .map(String::toUpperCase)
                .toList();

        record Employee(String name, String department, double salary) {}

        List<Employee> employees = List.of(
                new Employee("Alice", "IT", 90000),
                new Employee("Bob", "HR", 65000),
                new Employee("Charlie", "IT", 110000)
        );

        //2. map() with custom objects
        List<String> onlyName=employees.stream()
                .map(Employee::name)
                .toList();
        System.out.println("Only Names: "+onlyName);

        //salary increase by 10%
        List<Employee> updatedSalary=employees.stream()
                .map(emp->new Employee(emp.name,emp.department,emp.salary*1.1))
                .toList();
        System.out.println("incremented salary: "+ updatedSalary);


        //3. primittive specialized map
        double totalSum=employees.stream()
                .mapToDouble(Employee::salary)
                .sum();
        System.out.println("Total sum: "+totalSum);


        record Employee2(String name, String department, double salary, int age) {}

        List<Employee2> employees2 = List.of(
                new Employee2("Alice", "IT", 90000, 26),
                new Employee2("Bob", "HR", 65000, 28),
                new Employee2("Charlie", "IT", 110000, 31)
        );

        double averageAge = employees2.stream()
                .mapToInt(Employee2::age)
                .average()
                .orElse(0.0);
        System.out.println("Average age: "+averageAge);


        //4. Flat map
        List<List<String>> nested = List.of(
                List.of("A", "B"),
                List.of("C", "D", "E"),
                List.of("F")
        );

        List<String> flatMap=nested.stream()
                .flatMap(List::stream)
                .toList();
        System.out.println("Flat Map: "+flatMap);


        //for Custom objects
        record Employee3(String name, List<String> skills) {}

        List<Employee3> employees3 = List.of(
                new Employee3("Alice", List.of("Java", "Spring", "Kafka")),
                new Employee3("Bob", List.of("Java", "Docker")),
                new Employee3("Charlie", List.of("Python", "Kafka", "AWS"))
        );

        List<String> skills=employees3.stream()
                .flatMap(emp->emp.skills.stream())
                .toList();
        System.out.println("Flat map for custom object: "+skills);

        //distict
        List<String> skills2=employees3.stream()
                .flatMap(emp->emp.skills.stream())
                .distinct()
                .toList();

        //toSet
        Set<String> skills3=employees3.stream()
                .flatMap(emp->emp.skills.stream())
                .collect(Collectors.toSet());
        System.out.println("Flat map and toSet: "+ skills3);


        //flatMapToInt
        List<List<Integer>> nestedNumbers = List.of(
                List.of(1, 2, 3),
                List.of(4, 5),
                List.of(6, 7, 8, 9)
        );

        int flatSum = nestedNumbers.stream()
                .flatMapToInt(list->list.stream().mapToInt(Integer::intValue))
                .sum();
        System.out.println("flat sum: "+flatSum);


        //advance mapping
        record EmployeeDTO(String name, String dept) {}

        List<EmployeeDTO> customDTO = employees.stream()
                .map(emp->new EmployeeDTO(emp.name,emp.department))
                .toList();
        System.out.println("Custom DTO: "+customDTO);

        //conditional mapping
        List<String> labels = employees.stream()
                .map(e->e.salary>100000 ? "Senior":"Junior")
                .toList();
        System.out.println("Conditional mapping: "+labels);
        




    }
}
