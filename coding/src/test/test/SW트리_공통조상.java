package test;

import java.io.*;
import java.util.*;

public class SW트리_공통조상 {
	static int V; // 정점의 개수
	static int E; // 간선의 개수
	static int S1; // 공통 조상 찾는 정점번호 1
	static int S2; // 공통 조상 찾는 정점번호 2
	static List<Integer>[] tree;
	static int[] parents;
	static int[] S1parents;
	static int count; // 서브 트리의 크기
	static boolean[] visited;
	
	static void dfs(int node) {
		visited[node] = true;
		count++;
		
		for(int i=0; i<tree[node].size(); i++) {
			int child = (int) tree[node].get(i);
			if(!visited[child]) {
				dfs(child);
			}
		}
	}

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			S1 = Integer.parseInt(st.nextToken());
			S2 = Integer.parseInt(st.nextToken());
			
			tree = new ArrayList[V+1];
			
			for (int i = 1; i <= V; i++) tree[i] = new ArrayList<>();
			
			parents = new int[V+1];
			
			S1parents = new int[V+1];
			
			visited = new boolean[V+1];
			
			st = new StringTokenizer(br.readLine());
			
			while(st.hasMoreTokens()) {
				int parent = Integer.parseInt(st.nextToken());
				int child = Integer.parseInt(st.nextToken());
				
				tree[parent].add(child);
				parents[child] = parent;
			}
			
			int maxNode = 1;
			
			int searchParent = Integer.MAX_VALUE;
			int finder;
			finder = S1;
			while(searchParent > 1) {
				searchParent = parents[finder];
				S1parents[searchParent] = -1;
				finder = searchParent;
			}
			
			searchParent = Integer.MAX_VALUE;
			
			finder = S2;
			while(searchParent > 1) {
				searchParent = parents[finder];
				if(S1parents[searchParent] == -1) {
					maxNode = searchParent;
					break;
				}
				finder = searchParent;
			}
			count = 0;
			dfs(maxNode);
			
			bw.write("#" + t + " " + maxNode + " " + count);
			bw.write("\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
