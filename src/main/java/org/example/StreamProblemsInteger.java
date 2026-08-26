package org.example;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamProblemsInteger {
    public static void main(String[] args){
        List<Integer> numbers= Arrays.asList(10, 15, 20, 10, 25, 30, 15, 40, 11, 12, 20, 35);

        List<Integer> evenNumbers=numbers.stream().filter(x->x%2==0).collect(Collectors.toList());
        System.out.println(evenNumbers);

        List<Integer>oddNumbersGreaterThan20=numbers.stream()
                .filter(x->x%2!=0)
                .filter(x->x>20)
                .collect(Collectors.toList());
        System.out.println(oddNumbersGreaterThan20);

        //sort ascending
        List<Integer> sortAsc=numbers.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(sortAsc);

        //sort descending
        List<Integer> sortDesc=numbers.stream()
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());
        System.out.println(sortDesc);

        //find maximum
        Optional<Integer> maxi=numbers.stream().max(Integer::compareTo);
        System.out.println(maxi);

        //find minimum
        Optional<Integer> mini=numbers.stream().min(Integer::compareTo);
        System.out.println(mini);

        //find sum
        int sum=numbers.stream()
                .mapToInt(Integer::intValue)  //convert Integer object to primitive int
                .sum();
        System.out.println(sum);

        //find average
        OptionalDouble average=numbers.stream()
                .mapToInt(Integer::intValue)
                .average();
        System.out.println(average);

        //remove duplicates
        List<Integer> distinctNumbers=numbers.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println(distinctNumbers);

        //frequency table
        Map<Integer,Long> freqTable=numbers.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));
        System.out.println(freqTable);

        //find duplicates
        Set<Integer> duplicates = numbers.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .filter(entry->entry.getValue()>1)
                .map(entry->entry.getKey())
                .collect(Collectors.toSet());
        System.out.println(duplicates);

        //first non repeating
        Optional<Integer> firstNonRepeating = numbers.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new, //we are telling java to use this specific collection bcz it preserve the insertion order
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .filter(entry->entry.getValue()==1)
                .map(entry->entry.getKey())
                .findFirst();
        System.out.println(firstNonRepeating);

        //second highest integer
        Optional<Integer> secondHighest=numbers.stream()
                .distinct()
                .sorted(Collections.reverseOrder())
                .skip(1)
                .findFirst();
        System.out.println(secondHighest);

        //top 3 highest integer
        List<Integer> topThree=numbers.stream()
                .distinct()
                .sorted(Collections.reverseOrder())
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(topThree);

        //all numbers starting from 1
        List<Integer> startsWithOne=numbers.stream()
                .filter(x->String.valueOf(x).startsWith("1"))
                .collect(Collectors.toList());
        System.out.println(startsWithOne);

        //seperate even and odd using partitioningBy()
        Map<Boolean, List<Integer>> evenOdd = numbers.stream()
                .collect(Collectors.partitioningBy(x->x%2==0));
        System.out.println("even: "+evenOdd.get(true));
        System.out.println("odd: "+evenOdd.get(false));


    }
}
