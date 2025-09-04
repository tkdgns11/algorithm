package src;

import java.io.*;
import java.util.*;

public class SW작업순서0904 {

	static int V; // 정점의 개수 3 ≤ V ≤ 1000
	static int E; // 간선의 개수 2 ≤ E ≤ 3000
	static int[][] pan;
	static boolean[] visited;
	static BufferedWriter bw;

	static void dfs(int node) throws IOException {
		for (int i = 1; i <= V; i++) {
			if (pan[node][i] == 1 && !visited[i]) {
				dfs(i);
			}
		}
		visited[node] = true;
		bw.write(node + " ");
	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for (int tc = 1; tc <= 10; tc++) {

			StringTokenizer st = new StringTokenizer(br.readLine());

			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());

			pan = new int[V + 1][V + 1];
			visited = new boolean[V + 1];

			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < E; i++) {
				int num1 = Integer.parseInt(st.nextToken());
				int num2 = Integer.parseInt(st.nextToken());
				pan[num2][num1] = 1;
			}

			bw.write("#" + tc + " ");

			for (int i = 1; i <= V; i++) {
				if (!visited[i]) {
					dfs(i);
				}
			}
			bw.write("\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}

}
