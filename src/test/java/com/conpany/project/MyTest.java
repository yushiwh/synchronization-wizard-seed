package com.conpany.project;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class MyTest extends Tester {

    @Test
    public void execTestClientDataController() throws Exception {

        List<String> list =  Arrays.asList("a1", "a2", "b1", "c2", "c1");
        Stream<String> stream  = list.stream();
        stream.forEach(System.out::println);


        List<Integer> listInt = Arrays.asList(1,2,3);
        Stream<Integer> streamInt  = listInt.stream();
        streamInt.map(i->i*i).forEach(System.out::println);

    }

}
