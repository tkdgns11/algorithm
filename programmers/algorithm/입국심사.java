package algorithm;

import java.util.Arrays;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/43238
 */
public class 입국심사 {
	
	public long solution(int n, int[] times) {
		
		long left = 1;
		long right = (long) n * Arrays.stream(times).max().getAsInt();
		
		while(left < right) {
			long mid = (left + right) / 2; 
			
			long count = 0;
			for(int t : times) count += mid / t;
			
			if(count >= n) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}
}
