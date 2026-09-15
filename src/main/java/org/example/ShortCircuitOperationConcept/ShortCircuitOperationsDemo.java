package org.example.ShortCircuitOperationConcept;

import java.util.List;
import java.util.Optional;

public class ShortCircuitOperationsDemo {
    public static void main(String[] args){
        List<String> names = List.of("Alice", "Bob", "Charlie", "David");
        record Employee(String name, String department, double salary) {}

        List<Employee> employees = List.of(
                new Employee("Alice", "IT", 90000),
                new Employee("Bob", "HR", 65000),
                new Employee("Charlie", "IT", 110000),
                new Employee("Diana", "Finance", 75000)
        );

        //findFirst()
        Optional<String> findFirst = names.stream().findFirst();
        System.out.println("Find First: "+findFirst);

        Optional<String> findFirst2 = names.stream()
                .filter(n->n.length()>4)
                .findFirst();
        System.out.println("Find First with filter: "+findFirst2);

        Optional<Employee> ff3=employees.stream()
                .filter(e->e.department.equals("IT"))
                .findFirst();
        System.out.println("find first on custom class: "+ff3);

        //findAny()
        //Returns any element. In sequential streams it usually behaves like findFirst(),
        //but in parallel streams it can return any matching element (better performance).

        //Use findFirst() when order matters.
        //Use findAny() when order doesn’t matter (especially with parallel streams).

        Optional<Employee> anyHighSalary=employees.parallelStream()
                .filter(emp->emp.salary>80000)
                .findAny();
        System.out.println("find any: "+anyHighSalary);

        //anyMatch - Returns true if at least one element matches the predicate. Stops at the first match.

        boolean anyMatch = employees.stream()
                .anyMatch(e->e.salary>80000);
        System.out.println("any salary above 80000?: "+anyMatch);

        boolean hasHR = employees.stream()
                .anyMatch(e -> e.department().equals("HR"));

        // Check if any name starts with 'C'
        boolean startsWithC = names.stream()
                .anyMatch(name -> name.startsWith("C"));

        //allMatch
        boolean allHighSalary = employees.stream()
                .allMatch(e -> e.salary() > 50000);
        System.out.println("allMatch: "+allHighSalary);

        boolean allIT=employees.stream()
                .allMatch(emp->emp.department.equals("IT"));
        System.out.println("all IT?: "+allIT);

        boolean allNameLength = employees.stream()
                .allMatch(emp->emp.name.length()>2);
        System.out.println("all names length>2? : "+allNameLength);

        //nonMatch()
        boolean noOneInSales = employees.stream()
                .noneMatch(emp->emp.department.equals("Sales"));
        System.out.println("No one in sales: "+noOneInSales);

        boolean noVeryHighSalary = employees.stream()
                .noneMatch(emp->emp.salary>200000);
        System.out.println("no very high salary: "+noVeryHighSalary);



    }
}
