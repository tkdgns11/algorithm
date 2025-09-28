package src;

import java.util.Scanner;

/**
 * n개의 턴, 1~M까지. k개의 말
   nums에 들어있는 n개의 숫자를 중복을 허용하지않고 중복순열
 */
public class 일차원윷놀이 {
	static int n,m,k;
	static int[] nums;
	static int[] choice;
	static int result;
	
	static void calc() {
		int[] horse = new int[k];
		
		for(int i=0; i<n; i++) {
			horse[choice[i]] += nums[i];
		}
		int count = 0;
		for(int i=0; i<k; i++) {
			if(horse[i] >= m-1) {
				count++;
			}
		}
		result = Math.max(count, result);	
	}
	
	static void dfs(int depth) {
		if(depth == n) {
			calc();
			return;
		}
		
		for(int i=0; i<k; i++) {
			choice[depth] = i; 
			dfs(depth+1);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	    n = sc.nextInt();
	    m = sc.nextInt();
	    k = sc.nextInt();
	    nums = new int[n];
	    
	    for (int i = 0; i < n; i++) {
	        nums[i] = sc.nextInt();
	    }
	    choice = new int[n];
	    result = Integer.MIN_VALUE;
	    dfs(0);
	    System.out.println(result);
	}
}
