package org.example.groupingByConcepts;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class GroupingByConceptsDemo {
    public static void main(String[] args){
        List<String> names = List.of("Alice", "Bob", "Anna", "Charlie", "David", "Amy");

        //1. Basic Grouping according to first letter
        Map<Character,List<String>> basicGroup1= names.stream()
                .collect(Collectors.groupingBy(
                        name->name.charAt(0)
                ));
        System.out.println("Basic Grouping: "+basicGroup1);

        //Basic Grouping according to last letter
        Map<Character,List<String>> basicGroup2 = names.stream()
                .collect(Collectors.groupingBy(
                        name->name.charAt(name.length()-1)
                ));
        System.out.println("Basic Grouping2: "+basicGroup2);

        //Basic Grouping according to length of String
        Map<Integer, List<String>> basicGroup3=names.stream()
                .collect(Collectors.groupingBy(
                        name->name.length()
                ));
        System.out.println("Basic Grouping3: "+basicGroup3);
        System.out.println();
        System.out.println();

        //2. Grouping with downstream collector
        //You can pass a second collector to transform the grouped values instead of just collecting them into a List.
        //according to first character
        Map<Character, Long> downstreamCollector1=names.stream()
                .collect(Collectors.groupingBy(
                        name->name.charAt(0),
                        Collectors.counting()
                ));
        System.out.println("Downstream Collector1: "+downstreamCollector1);

        //according to last character
        Map<Character, Long> downstreamCollector2=names.stream()
                .collect(Collectors.groupingBy(
                        name->name.charAt(name.length()-1),
                        Collectors.counting()
                ));
        System.out.println("Downstream Collector2: "+downstreamCollector2);

        //to upper case
        Map<Character, List<String>> downstreamCollector3=names.stream()
                .collect(Collectors.groupingBy(
                        name->name.charAt(0),
                        Collectors.mapping(x->x.toUpperCase(), Collectors.toList())
                ));
        System.out.println("Downstream Collector3: "+downstreamCollector3);


        record Employee(String dept, double salary) {}

        List<Employee> employees = List.of(
                new Employee("IT", 80000),
                new Employee("HR", 60000),
                new Employee("IT", 95000),
                new Employee("Finance", 70000),
                new Employee("HR", 65000)
        );

        Map<String,List<Employee>> deptGrouping=employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::dept
                ));
        System.out.println("Basic Grouping with full employee data: "+deptGrouping);

        Map<String,List<Double>> deptGrouping2=employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::dept,
                        Collectors.mapping(Employee::salary, Collectors.toList())
                ));
        System.out.println("Basic Grouping with only salary of employee: "+deptGrouping2);

        //summing
        Map<String,Double> salarySum=employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::dept,
                        Collectors.summingDouble(Employee::salary)
                ));
        System.out.println("Downstream Collector Summing: "+salarySum);

        //averaging
        Map<String,Double> salaryAverage=employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::dept,
                        Collectors.averagingDouble(Employee::salary)
                ));
        System.out.println("Downstream Collector Averaging: "+salaryAverage);

        //collecting in different data structure
        Map<String, Set<Double>> convertToSet=employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::dept,
                        Collectors.mapping(Employee::salary,Collectors.toSet())
                ));
        System.out.println("Converting to Set: "+convertToSet);
        System.out.println();
        System.out.println();



        //3. Multi Level Grouping
        record Person(String city, String gender, int age) {}

        List<Person> people = List.of(
                new Person("Delhi", "M", 28),
                new Person("Mumbai", "F", 32),
                new Person("Delhi", "F", 25),
                new Person("Mumbai", "M", 40),
                new Person("Delhi", "M", 35)
        );

        Map<String, Map<String,List<Person>>> multiLevelGrouping= people.stream()
                .collect(Collectors.groupingBy(
                        Person::city,
                        Collectors.groupingBy(
                                Person::gender
                        )
                ));
        System.out.println("Multi Level Grouping: "+multiLevelGrouping);

        Map<String, Map<String,Long>> multiLevelGrouping2=people.stream()
                .collect(Collectors.groupingBy(
                        Person::city,
                        Collectors.groupingBy(
                                Person::gender,
                                Collectors.counting()
                        )
                ));
        System.out.println("Multi Level Grouping 2: "+multiLevelGrouping2);


        //4. Custom Map Implementation
        //for optimisation, i guess
        Map<String, List<Employee>>customMap =  employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::dept,
                        TreeMap::new,
                        Collectors.toList()
                ));
        System.out.println("Custom Map: "+customMap);



    }
}
