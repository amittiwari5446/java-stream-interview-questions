package org.example.FilteringConcepts;

import java.util.*;
import java.util.stream.Collectors;

public class FilterConceptsDemo {
    public static void main(String[] args){
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        //1. Basic filter
        //even numbers
        List<Integer> evens = numbers.stream()
                .filter(x->x%2==0)
                .collect(Collectors.toList());
        System.out.println("Even Numbers: "+evens);

        //2. Filtering Objects
        record Employee(String name, String department, double salary, int age) {}

        List<Employee> employees = List.of(
                new Employee("Alice", "IT", 90000, 28),
                new Employee("Bob", "HR", 65000, 35),
                new Employee("Charlie", "IT", 110000, 32),
                new Employee("Diana", "Finance", 75000, 29),
                new Employee("Eve", "IT", 85000, 26)
        );

        List<Employee> moreThan80K = employees.stream()
                .filter(emp->emp.salary>80000)
                .collect(Collectors.toList());
        System.out.println("More than 80K: "+moreThan80K);

        //3. 'and' condition
        List<Employee> andCond = employees.stream()
                .filter(emp->(emp.salary>80000 && emp.department.equals("IT")))
                .collect(Collectors.toList());
        System.out.println("'and' condition also: "+andCond);

        //4. 'or' condition
        List<Employee> orCond = employees.stream()
                .filter(emp->(emp.department.equals("HR") || emp.department.equals("Finance")))
                .collect(Collectors.toList());
        System.out.println("OR condition: "+orCond);

        //5. negation
        List<Employee> negationCond=employees.stream()
                .filter(emp->!emp.department.equals("IT"))
                .collect(Collectors.toList());
        System.out.println("Negation Condition: "+negationCond);

        //6. Chaining multiple filters
        List<Employee> chainCond=employees.stream()
                .filter(emp->emp.department.equals("IT"))
                .filter(emp->emp.age>30)
                .filter(emp->emp.salary>80000)
                .collect(Collectors.toList());
        System.out.println("Chaining condition: "+chainCond);

        //7. Filter + Map
        List<String> mapAndFilter=employees.stream()
                .filter(emp->!emp.department.equals("IT"))
                .map(emp->emp.name)
                .collect(Collectors.toList());
        System.out.println("map and filter: "+mapAndFilter);

        //8. filter and other collections
        Set<String> otherCollections = employees.stream()
                .filter(emp->emp.salary>=70000)
                .map(emp->emp.department)
                .collect(Collectors.toSet());
        System.out.println("Filter and other collections: "+otherCollections);

        //toMap
        Map<String, Double> toMap=employees.stream()
                .filter(emp->emp.age<30)
                .collect(Collectors.toMap(
                        Employee::name,
                        Employee::salary
                ));
        System.out.println("To Map: "+toMap);

        //9. Objects::nonNull
        List<String> list= Arrays.asList("Amit",null,"Rahul",null,"Hari");
        List<String> nonNullMethod=list.stream()
                .filter(Objects::nonNull)
                .toList();
        System.out.println("Objects::nonNull: "+nonNullMethod);

        //10. GroupingBy and Filtering
        Map<String, List<Employee>> groupAndFilter = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.filtering(
                                emp->emp.salary>80000,
                                Collectors.toList()
                        )
                ));
        System.out.println("GroupingBy and Filtering: "+groupAndFilter);

        //11. Filter + findFirst / findAny / anyMatch / allMatch / noneMatch
        //check if any employee earn more than 80k
        boolean anyMatchDemo = employees.stream()
                .anyMatch(emp->emp.salary>80000);
        System.out.println("anyMatch(): "+anyMatchDemo);

        //check if all employee earn more than 80k
        boolean allMatchDemo = employees.stream()
                .allMatch(emp->emp.salary>80000);
        System.out.println("allMatch(): "+allMatchDemo);

        //find first employee from IT
        Optional<Employee> empl= employees.stream()
                .filter(emp->emp.department.equals("IT"))
                .findFirst();
        System.out.println("findFirst(): "+empl);

        //finding employee from IT using findAny()
        Optional<Employee> empl2= employees.stream()
                .filter(emp->emp.department.equals("IT"))
                .findAny();
        System.out.println("findAny(): "+empl2);

        //map vs flatMap


    }
}
