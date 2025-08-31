package test;

import java.io.*;
import java.util.*;

public class Baekjoon15649N과M연습 {
	
	static int N;
	static int M;
	static int[] choice;
	static boolean[] visited;
	static BufferedWriter bw;
	
	static void print() throws IOException {
		for(int n : choice) {
			bw.write(n + " ");
		}
		bw.write("\n");
	}
	
	static void dfs(int depth) throws IOException {
		if(depth == M) {
			print();
			return;
		}
		
		for(int i=1; i<=N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				choice[depth] = i;
				dfs(depth+1);
				visited[i] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		choice = new int[M];
		visited = new boolean[N+1];
		dfs(0);
		bw.flush();
		bw.close();
		br.close();
	}

}
