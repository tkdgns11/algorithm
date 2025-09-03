package src;

import java.io.*;
import java.util.*;

public class SW문제해결_숫자만들기 {
	
	static int[] calc;
	static int[] input;
	static int total;
	static char[] transCalc;
	static char[] compositionCalc;
	static boolean[] visited;
	
	static void calc() {
		int result;
		int num1;
		int num2;
		boolean num1exists = false;
		
		for(int i=0; i<input.length + compositionCalc.length; i++) {
			if(i%2 == 1) { // 숫자면
				if(!num1exists) {
					num1 = input[i];
					num1exists = true;
				} else {
					num2 = input[i];
					num1exists = false;
				}
			} else {
				
			}
		}
	}
	
	static void dfs(int depth) {
		if(depth == compositionCalc.length) {
			calc();
			return;
		}
		
		for(int i=depth; i<transCalc.length; i++) {
			if(!visited[i]) {
				compositionCalc[i] = transCalc[i];
				dfs(i+1);
			}
		}
	}
	
	/**
	 * 입력으로 주어지는 숫자의 순서는 변경할 수 없다.
	 * 
	 */
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine().trim());;

		for (int t = 1; t <= T; t++) {
			
			int N = Integer.parseInt(br.readLine().trim());
			
			StringTokenizer st = new StringTokenizer(br.readLine());

			calc = new int[4]; // + - * /
			
			input = new int[N];
			
			total = 0;
			
			for(int i=0; i<calc.length; i++) {
				calc[i] = Integer.parseInt(st.nextToken());
				total += calc[i];
			}
			
			transCalc = new char[total];
			
			int temp = 0;
			for(int i=0; i<calc.length; i++) {
				for(int j=0; j<calc[i]; j++) {
					if(i == 0) {
						transCalc[temp] = '+';
					} else if(i == 1) {
						transCalc[temp] = '-';
					} else if(i == 2) {
						transCalc[temp] = '*';
					} else {
						transCalc[temp] = '/';
					}
					temp++;
				}
			}
			
			st = new StringTokenizer(br.readLine());
			
			for(int i=0; i<input.length; i++) {
				input[i] = Integer.parseInt(st.nextToken());
			}
			
			visited = new boolean[total];
			
			dfs(0);
			
			bw.write("\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
