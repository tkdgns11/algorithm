package test;

import java.io.*;
import java.util.*;

class Baekjoon10451순열사이클 {
	
	static int[][] pan;
	static boolean[] visited;
	
	static void dfs(int I) {
		visited[I] = true;
		
		for(int i=0; i<pan[I].length; i++) {
			if(pan[I][i] == 1 && !visited[i]) {
				dfs(i);
			}
		}
	}
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int t=0; t<T; t++) {
			int N = Integer.parseInt(br.readLine().trim()); // 순열의 크기 N (2 ≤ N ≤ 1,000)
			// 1부터 N 까지의 정수
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			pan = new int[N+1][N+1];
			for(int i=1; i<=N; i++) {
				int num = Integer.parseInt(st.nextToken());
				pan[i][num] = 1;
			}
			
			visited = new boolean[N+1];
			
			int count = 0;
			
			for(int i=1; i<=N; i++) {
				if(!visited[i]) {
					dfs(i);
					count++;
				}
			}
			bw.write("" + count);
			bw.write("\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
