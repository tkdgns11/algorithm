package test;

import java.io.*;
import java.util.*;

class Baekjoon2747피보나치수 {
	
	static int[] memo;
	
	// n이 주어졌을 때, n번째 피보나치 수를 구하는 프로그램
	//  Fn = Fn-1 + Fn-2 (n ≥ 2)
	static int dfs(int N) {
		if(N == 0) return 0;
		if(N == 1) return 1;
		
		if(memo[N] != 0) return memo[N];
		
		memo[N] = dfs(N-1) + dfs(N-2);
		
		return memo[N];
	}

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine().trim());
		
		memo = new int[N+1]; // 0 ~ N
		
		bw.write(String.valueOf(dfs(N)));

		bw.flush();
		bw.close();
		br.close();
	}
}
