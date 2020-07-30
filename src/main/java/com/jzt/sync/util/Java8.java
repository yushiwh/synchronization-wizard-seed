package com.jzt.sync.util;


import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Component
public class Java8 {
    public void getData() {
        List<String> list = Arrays.asList("a1", "a2", "b1", "c2", "c1");
        Stream<String> stream = list.stream();
        stream.forEach(System.out::println);

        System.out.println("*********************************");

        List<Integer> listInt = Arrays.asList(2, 1, 4, 5, 3);
        Stream<Integer> streamInt = listInt.stream();

        streamInt.map(i -> i * i * i).filter(i -> i >= 4).limit(4).sorted().forEach(System.out::println);
        System.out.println("*********************************");

        Random random = new Random();
        random.ints(1, 100).limit(10).forEach(System.out::println);


    }
}
