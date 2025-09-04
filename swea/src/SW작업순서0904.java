package src;

import java.io.*;
import java.util.*;

public class SW작업순서0904 {

	static int V; // 정점의 개수 3 ≤ V ≤ 1000
	static int E; // 간선의 개수 2 ≤ E ≤ 3000
	static int[][] pan;
	static boolean[] visited;
	static List nums;
	static BufferedWriter bw;

	static void dfs(int node) throws IOException {
		boolean find = false;
		for (int i = 1; i <= V; i++) {
			if (pan[node][i] == 1 && !visited[i]) {
				visited[i] = true;
				find = true;
				nums.add(i);
				dfs(i);
			}
		}

		if (!find) {
			
			for(int ii=0; ii<nums.size(); ii++) {
				System.out.print(nums.get(ii) + " ");
			}
			System.out.println();
			
			for (int j = nums.size() - 1; j >= 0; j--) {
				bw.write("" + nums.get(j) + " ");
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for (int tc = 1; tc <= 1; tc++) {

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
			
			System.out.println("판 찍어보자");
			for(int i=0; i<V+1; i++) {
				for(int j=0; j<V+1; j++) {
					System.out.print(pan[i][j] + " ");
				}
				System.out.println();
			}

			bw.write("#" + tc + " ");

			for (int i = 1; i <= V; i++) {
				if (!visited[i]) {
					boolean find = false;
					for(int j=1; j<=V; j++) {
						if(pan[i][j] == 1) {
							find = true;
							break;
						}
					}
					if(find) {
						nums = new ArrayList<>();
						visited[i] = true;
						nums.add(i);
						dfs(i);
					}
				}
			}

			bw.write("\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}

}
