package com.sh.stream;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import lombok.SneakyThrows;

public class StreamTest {
    public static void main(String[] args) {

        List<String> numbers = Arrays.asList("12", "123", "333");
        List<String> numbers2 = Arrays.asList("12", "123", "333","334");
        String mergedString=numbers.stream().collect(Collectors.joining(","));

        System.out.println("合并字符串:"+mergedString);

        List<Integer> numbers1 = Arrays.asList(2, 3, 3, 2, 5, 2, 7);
//        numbers1.sort(Comparator.comparing(Integer::intValue).reversed());
        numbers.stream().sorted(Comparator.comparing(String::length).reversed());


        List<String> numbers3=numbers2.subList(0,0);
        System.out.println(numbers3);
        System.out.println(numbers2.size());

    }



    @SneakyThrows
    private void case1(){
        String content =new String(Files.readAllBytes(Paths.get("")),StandardCharsets.UTF_8);
    }
}
