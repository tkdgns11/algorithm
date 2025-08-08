package test;

import java.io.*;
import java.util.*;

class Baekjoon11724연결요소의개수 {
	
	static int count;
	
	static boolean[] visited;
	
	static void dfs(int node, int[][] pan, int N) {
		
		visited[node] = true;
		
		for(int i=1; i<N+1; i++) {
			if(pan[node][i] == 1 && !visited[i] ) {
				dfs(i, pan, N);
			}
		}
	}

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 정점의 갯수
		int M = Integer.parseInt(st.nextToken()); // 정점의 갯수
	
		int[][] pan = new int[N+1][N+1]; 
				
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()); // 정점의 갯수
			int end = Integer.parseInt(st.nextToken()); // 정점의 갯수
		
			pan[start][end] = 1;
			pan[end][start] = 1;
		}
		
		visited = new boolean[N+1];
		
		for(int i=1; i<N+1; i++) {
			if(visited[i]) continue; // break x 
			
			count++;
			dfs(i, pan, N);
		}
		System.out.println("" + count);
	}
}
