package src;

import java.io.*;
import java.util.*;

public class Baekjoon2252줄세우기 {
	
	static int N;
	static List<Integer>[] pan;
	static boolean[] visited;
	static BufferedWriter bw;

	static void dfs(int node) throws IOException {
		for(int i=0; i<pan[node].size(); i++) {
			if(!visited[pan[node].get(i)]) {
				visited[pan[node].get(i)] = true;
				dfs(pan[node].get(i));
			}
		}
		bw.write(node + " ");
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// Arrays.fill(pan, new ArrayList<>());
		pan = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) pan[i] = new ArrayList<>();
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			// num1이 num2보다 앞에 있어야 한다.
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());
			pan[num2].add(num1);
		}
		
		visited = new boolean[N+1];
		
		for(int i=1; i<=N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				dfs(i);
			}
		}
		bw.flush();
		bw.close();
		br.close();
	}

}
