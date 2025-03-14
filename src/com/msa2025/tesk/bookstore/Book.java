package com.msa2025.tesk.bookstore;

public class Book {
	final String name;
	final int value;
	
	
	Book(String name, int value){
		this.name = name;
		this.value = value;
	}


	public String getName() {
		return name;
	}


	public int getValue() {
		return value;
	}
}
