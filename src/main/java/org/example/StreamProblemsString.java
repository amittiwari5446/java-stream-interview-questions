package org.example.StreamOverCustomClass;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamProblemsString {
    public static void main(String[] args){
        List<String> words = Arrays.asList(
                "apple",
                "banana",
                "orange",
                "apple",
                "grapes",
                "banana",
                "application",
                "cat",
                "dog",
                "elephant"
        );

        //filter string starting with a specific letter
        List<String> startsWithA=words.stream()
                .filter(w->w.startsWith("a"))
                .collect(Collectors.toList());
        System.out.println("Starts with a:"+startsWithA);

        //filter strings with length greater than 5
        List<String> lengthGreaterThan5=words.stream()
                .filter(w->w.length()>5)
                .collect(Collectors.toList());
        System.out.println("Length greater than 5: "+lengthGreaterThan5);

        //convert to upper case
        List<String> convertToUppercase=words.stream()
                .map(w->w.toUpperCase())
                .collect(Collectors.toList());
        System.out.println("upper case: "+convertToUppercase);

        List<String> str1=words.stream()
                .sorted()
                .distinct()
                .collect(Collectors.toList());
        System.out.println("str1: "+str1);

        List<String> str2=words.stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
        System.out.println("str2: "+str2);

        List<String> str3=words.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        System.out.println("str3: "+str3);

        //comparing by length
        List<String> str4=words.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());
        System.out.println("str4: "+str4);

        List<String> str5=words.stream()
                .sorted(Comparator.comparingInt(String::length).reversed())
                .collect(Collectors.toList());
        System.out.println("str5: "+str5);

        //sorting first by length then alphabetically
        List<String> str6=words.stream()
                .sorted(Comparator.comparingInt(String::length)
                        .thenComparing(Comparator.naturalOrder()))
                .collect(Collectors.toList());
        System.out.println("str6: "+str6);

        //sorting first by alphabetically then by length
        List<String> str7=words.stream()
                .sorted(Comparator.comparing(String::toString)
                        .thenComparingInt(String::length))
                .collect(Collectors.toList());
        System.out.println("str7: "+str7);


    }
}
