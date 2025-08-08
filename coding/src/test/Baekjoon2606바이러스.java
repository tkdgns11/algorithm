package test;

import java.io.*;
import java.util.*;

class Baekjoon2606바이러스 {
	
	static int count = -1; // 바이러스에 걸린 총 컴퓨터의 수
	
	static int[][] pan;
	
	static boolean[] visited;
	
	static void dfs(int node) {
		visited[node] = true;
		count++;
		
		for(int i=0; i<pan[node].length; i++) {
			if(pan[node][i] == 1 && !visited[i]) {
				dfs(i);
			}
		}
	}

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine().trim()); // 컴퓨터의 수
		int M = Integer.parseInt(br.readLine().trim()); // 연결된 노드의 수
		
		visited = new boolean[N+1];
		
		StringTokenizer st ;
		
		pan = new int[N+1][N+1];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			pan[start][end] = 1;
			pan[end][start] = 1;
		}
		
		// 1번 컴퓨터 바이러스 걸림.
		
		dfs(1);
		
		bw.write("" + count);

		bw.flush();
		bw.close();
		br.close();
	}
}
