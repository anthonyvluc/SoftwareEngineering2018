// Note: This solution does not handle cases where the mirrored sub sequence is
// potentially in the middle of the array as opposed to the opposite end.
package edu.nd.se2018.homework.hwk1;
public class Question3 {
	
	public Question3(){}	

    public int getMirrorCount(int[] numbers){
    	int mirrorLength = 0;
    	
    	// Handle case of empty array.
    	if (numbers.length == 0) {
    		return mirrorLength;
    	}

    	// Determine length of mirrored sequence.
    	mirrorLength = mirrorCountHelper(numbers, 0, numbers.length-1, mirrorLength);
    	
    	// Handle case where there are no mirrors in array.
    	if (mirrorLength == 0) {
    		mirrorLength = 1;
    	}

    	return mirrorLength;
	}

    // Arguments: integer number array, left index, right index, length parameter
    private int mirrorCountHelper(int[] numbers, int left, int right, int length) {
    	if ((left <= numbers.length-1) && (right >= 0)) { 	// Check bounds
        	if (numbers[left] == numbers[right]) { 			// Mirrored numbers
        		length += 1;
        		length = mirrorCountHelper(numbers, ++left, --right, length);
        	}
    	}
    	return length;
    }
}
