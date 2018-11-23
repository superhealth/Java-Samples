package com.sh.stream;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import lombok.SneakyThrows;

public class StreamTest {
    public static void main(String[] args) {

    }



    @SneakyThrows
    private void case1(){
        String content =new String(Files.readAllBytes(Paths.get("")),StandardCharsets.UTF_8);
    }
}
