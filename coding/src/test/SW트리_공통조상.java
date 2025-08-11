package test;

import java.io.*;
import java.util.*;

public class SW트리_공통조상 {
	static int V; // 정점의 개수
	static int E; // 간선의 개수
	static int S1; // 공통 조상 찾는 정점번호 1
	static int S2; // 공통 조상 찾는 정점번호 2
	static int[] pan;
	static boolean[] visited;
	static int count; // 서브 트리의 크기
	
	static void dfs(int start) {
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

			bw.write("\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
