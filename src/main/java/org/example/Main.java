package org.example;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //task1//
        String [] names = new String[]{
                "Petro", "Ivan", "Timur", "Oleksii", "Alina", "Ruslan", "Dmytro", "Roman"
        };
        printNames(names);
        //task2//
        System.out.println("\nsorted and uppersCase words:");
        for(String word:sortAndUpperCase(Arrays.asList(names))){
            System.out.println(word);
        }
        //task3//
        String [] arr = new String[]{
                "1, 2, 3", "4, 5"
        };
        System.out.println(convertNumbers(arr));
        //task4//
        long a = 25214903917L;
        long c = 11L;
        long m = (long)Math.pow(2,48);
        Stream<Long> random = randomNumbers(a, c, m, 1);
        System.out.println("random numbers");
        random.limit(10).forEach(System.out::println);
        //task5//
        Stream <Integer> stream1 = Arrays.stream(new Integer[]{5, 4, 3, 6, 2, 9});
        Stream <Integer> stream2 = Arrays.stream(new Integer[]{5, 4, 2, 1, 0, 7, 8});
        System.out.println("zip streams");
        Stream<Integer> stream3 = zip(stream1, stream2);
        stream3.forEach(System.out::println);
    }
    public static void printNames (String [] names){
        IntStream.range(0, names.length).filter(i->i%2==1).mapToObj(i->i+" "+names[i]).collect(Collectors.toList()).forEach(System.out::println);
    }
    public static List<String> sortAndUpperCase(List<String> words){
       return words.stream().map(s -> s.toUpperCase()).sorted().toList();
    }
    public static String convertNumbers(String [] arr){
        String[] numbers = String.join(", ", arr).split(", ");
        List<String> nums = Arrays.stream(numbers).map(i->Integer.parseInt(i)).sorted().map(i->i+"").toList();
        String number = String.join(", ",nums);
        return number;
    }
    public static Stream<Long> randomNumbers (long a, long c, long m, long seed){
        return Stream.iterate(seed, x->(a*x+c)%m);
    }
    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second){
        int len;

        Object [] array1 = first.toArray();
        Object [] array2 = second.toArray();
        if(array1.length>array2.length){
            len = array1.length;
        } else {
            len = array2.length;
        }
        return Stream.iterate(0, i->i+1).limit(len-1).flatMap(i->Stream.of((T)array1[i], (T)array2[i]));
    }
}