package test;

import java.io.*;
import java.util.*;

public class SW트리_공통조상 {
	static int V; // 정점의 개수
	static int E; // 간선의 개수
	static int S1; // 공통 조상 찾는 정점번호 1
	static int S2; // 공통 조상 찾는 정점번호 2
	static List<Integer> rootS1; // S1의 부모목록
	static List<Integer> rootS2; // S2의 부모목록
	static int[] pan;
	static boolean[] visited;
	static int count; // 서브 트리의 크기
	
	static void dfs(int start) {
		visited[start] = true;
		count ++;
		
		if(start * 2 < V * 2 + 1 && pan[start * 2] != 0 && !visited[start * 2]) {
			dfs(start * 2);
		}	
		
		if(start * 2 + 1 < V * 2 + 1 && pan[start * 2 + 1] != 0 && !visited[start * 2 + 1]) {
			dfs(start * 2 + 1);
		}
	}

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine().trim()); // 테스트 케이스의 수

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			S1 = Integer.parseInt(st.nextToken());
			S2 = Integer.parseInt(st.nextToken());

			pan = new int[V * 2 + 1];
			visited = new boolean[V * 2 + 1];

			st = new StringTokenizer(br.readLine());

			// 1 2 1 3 2 4 3 5 3 6 4 7 7 12 5 9 5 8 6 10 6 11 11 13

			int lastleftStart = -1;

			for (int i = 0; i < E; i++) {
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());

				if (start != lastleftStart) { // 시작이면서 처음
					pan[start * 2] = end; // 왼쪽 노드
					lastleftStart = start;
				} else if (start == lastleftStart) {
					pan[start * 2 + 1] = end; // 오른쪽 노드
				}
			}

			rootS1 = new ArrayList<>();
			int tmep = S1;
			while (tmep > 0) {
				rootS1.add(tmep /= 2);
			}

			rootS2 = new ArrayList<>();
			tmep = S2;
			while (tmep > 0) {
				rootS2.add(tmep /= 2);
			}
			int[] rootS1Arr = rootS1.stream().mapToInt(Integer::intValue).toArray();
			int[] rootS2Arr = rootS2.stream().mapToInt(Integer::intValue).toArray();
			System.out.println(":::::::::::::::::::::::::");
			System.out.println(rootS1Arr.toString());
			System.out.println(":::::::::::::::::::::::::");
			System.out.println(rootS2Arr.toString());
			System.out.println(":::::::::::::::::::::::::");
			
			int maxNode = Integer.MIN_VALUE;
			
			outer : for(int i=0; i<rootS1Arr.length; i++) {
				for(int j=0; j<rootS2Arr.length; j++) {
					if(rootS1Arr[i] == rootS2Arr[j]) {
						maxNode = rootS1Arr[i];
						break outer;
					}
				}
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
