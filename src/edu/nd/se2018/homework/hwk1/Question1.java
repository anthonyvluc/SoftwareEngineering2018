package edu.nd.se2018.homework.hwk1;

import java.util.HashSet;
import java.util.Set;

public class Question1 {
		
	public Question1(){}
	
	public int getSumWithoutDuplicates(int[] numbers){
		int sum = 0;
		Set<Integer> numSet = new HashSet<Integer>();

		// Add numbers to a set to remove duplicates.
		for (int number : numbers) {
			numSet.add(number);
		}
		
		// Sum the unique numbers.
		for (int number : numSet) {
			sum += number;
		}
		
		return sum;
	}
}
