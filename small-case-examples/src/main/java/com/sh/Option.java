package com.sh;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Option {
	
	public static void main(String[] args) {
		Integer i1=null;
		Integer i2=new Integer(6);
		Optional<Integer> o1= Optional.ofNullable(i1);
		Optional<Integer> o2= Optional.of(i2);
		
		System.out.println(o1.orElse(new Integer(0)));
		System.out.println(o2.get());
		System.out.println(o1.isPresent());
		System.out.println(o2.isPresent());
		
		List<Integer> numbers = Arrays.asList(2, 3, 3, 2, 5, 2, 7);
		//get list of unique squares
		List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
		System.out.println(squaresList);
	}
}
