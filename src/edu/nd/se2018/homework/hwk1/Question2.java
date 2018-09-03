package edu.nd.se2018.homework.hwk1;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Question2 {

	public Question2(){}
	
	public String getMostFrequentWord(String input, String stopwords){
		String mostCommonWord = null;
		
		Map<String,Integer> wordMap = new HashMap<String,Integer>();
		Set<String> stopSet = new HashSet<String>();

		// Add most common stopwords to set.
		for (String stopword : stopwords.split(" ")) {
			stopSet.add(stopword);
		}

		// Count word occurrences in input string.
		for (String word : input.split(" ")) {
			if (!stopSet.contains(word) && wordMap.containsKey(word)) {
				int wordCount = wordMap.get(word);
				wordMap.put(word, wordCount + 1);	// Add 1 to the occurrence
			} else {								
				wordMap.put(word, 1);				// First occurrence of word
			}
		}
		
		// Determine most common word.
		int maxCount = Collections.max(wordMap.values());
		for (Entry<String,Integer> entry : wordMap.entrySet()) {
			if (entry.getValue() == maxCount) {
				if (mostCommonWord == null) {		// Found word of max count
					mostCommonWord = entry.getKey();
				} else {							// Found another max count
					mostCommonWord = null;
					break;
				}
			}
		}

		return mostCommonWord;
	}
}
