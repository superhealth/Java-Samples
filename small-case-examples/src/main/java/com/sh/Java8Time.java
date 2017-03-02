package com.sh;

import java.time.LocalDateTime;

public class Java8Time {

	public static void main(String[] args) {
		System.out.println(LocalDateTime.now().plusMinutes(-20).compareTo(LocalDateTime.now()));;
	}
}
