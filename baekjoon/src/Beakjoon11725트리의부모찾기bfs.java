package src;

import java.io.*;
import java.util.*;

public class Beakjoon11725트리의부모찾기bfs {
	
	static List[] Nodes;
	static int[] parent;
	static boolean[] visited;
	
	static void bfs(int root) {
		Queue<Integer> q = new LinkedList<>();
		
		q.add(root);
		
		while(!q.isEmpty()) {
			int current = q.poll();
			
			for(int i=0; i<Nodes[current].size(); i++) {
				if() {
					
				}
			}
			
		}
		
	
	}

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine().trim());
		
		Nodes = new ArrayList[N+1];
		parent = new int[N+1];
		visited = new boolean[N+1];
		
		for(int i=1; i<=N; i++) {
			Nodes[i] = new ArrayList<>();
		}
		
		for(int i=0; i<N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int one = Integer.parseInt(st.nextToken());
			int two = Integer.parseInt(st.nextToken());
			
			Nodes[one].add(two);
			Nodes[two].add(one);
		}
		
		bfs(1);
		
		
	}
	
	
	

}
