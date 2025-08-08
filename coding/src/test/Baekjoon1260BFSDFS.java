package test;

import java.io.*;
import java.util.*;

class Baekjoon1260BFSDFS {

	static int[][] pan;
	static boolean[] visited;
	static List<Integer> dfsResult = new ArrayList<>();
	static List<Integer> bfsResult = new ArrayList<>();

	static void dfs(int start) {
		dfsResult.add(start); // 본인에 대해 방문처리

		for (int i = 0; i < pan[start].length; i++) {
			if (pan[start][i] == 1 && !visited[i]) {
				visited[i] = true; // 방문처리를 먼저
				dfs(i);
			}
		}
	}

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 정점의 개수
		int M = Integer.parseInt(st.nextToken()); // 간선의 개수
		int V = Integer.parseInt(st.nextToken()); // 탐색을 시작할 정점의 번호

		pan = new int[N + 1][N + 1]; // 0 ~ N

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			pan[start][end] = 1;
			pan[end][start] = 1; // 무방향 그래프이기때문에 양방향 체크
		}

		visited = new boolean[N + 1];
		visited[V] = true;
		dfs(V);

		Iterator<Integer> iterator = dfsResult.iterator();

		while (iterator.hasNext()) {
			bw.write(iterator.next() + " ");
		}
		bw.write("\n");
		
		dfsResult = null;

		visited = new boolean[N + 1]; // visited 초기화

		Queue<Integer> q = new LinkedList<>();

		q.offer(V);
		visited[V] = true;

		while (!q.isEmpty()) {
			int node = q.poll();

			bfsResult.add(node);

			// 단, 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문
			for (int i = 0; i < pan[node].length; i++) {
				if (pan[node][i] == 1 && !visited[i]) {
					q.offer(i);
					visited[i] = true;
				}
			}
		}

		iterator = bfsResult.iterator();

		while (iterator.hasNext()) {
			bw.write(iterator.next() + " ");
		}

		bw.flush();
		bw.close();
		br.close();
	}
}
