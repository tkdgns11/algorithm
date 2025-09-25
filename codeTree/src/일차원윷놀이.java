package src;

import java.util.Scanner;

/**
 * n개의 턴, 1~M까지. k개의 말
 */

public class 일차원윷놀이 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	    int n = sc.nextInt();
	    int m = sc.nextInt();
	    int k = sc.nextInt();
	    int[] nums = new int[n];
	    
	    for (int i = 0; i < n; i++) {
	        nums[i] = sc.nextInt();
	    }
	}
}
