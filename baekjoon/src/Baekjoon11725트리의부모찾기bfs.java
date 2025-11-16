package src;

import java.io.*;
import java.util.*;

public class Baekjoon11725트리의부모찾기bfs {
	
	static List<Integer>[] nodes;
	static int[] parent;
	
	static void bfs(int root) {
		Queue<Integer> q = new LinkedList<>();
		
		parent[root] = 1;
		
		q.add(root);
		
		while(!q.isEmpty()) {
			int current = q.poll();
			
			for(int i=0; i<nodes[current].size(); i++) {
				int child = nodes[current].get(i);
				if(parent[child] == 0) {
					parent[child] = current;
					q.add(child);
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine().trim());
		
		nodes = new ArrayList[N+1];
		parent = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			nodes[i] = new ArrayList<>();
		}
		
		for(int i=1; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int one = Integer.parseInt(st.nextToken());
			int two = Integer.parseInt(st.nextToken());
			
			nodes[one].add(two);
			nodes[two].add(one);
		}
		
		bfs(1);
		
		for(int i=2; i<parent.length; i++) {
			bw.write(Integer.toString(parent[i]));
			bw.write('\n');
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
