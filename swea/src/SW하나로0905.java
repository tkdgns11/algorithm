package src;

import java.io.*;
import java.util.*;

public class SW하나로0905 {

	static int N;
	static double E, ans;
	static boolean[] visited;
	static double[][] pan;
	static Node[] nodes;

	static class Node {
		int idx;
		int x;
		int y;

		Node(int idx, int x, int y) {
			this.x = x;
			this.y = y;
		}

		Node() {
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());

			StringTokenizer st1 = new StringTokenizer(br.readLine());
			StringTokenizer st2 = new StringTokenizer(br.readLine());

			pan = new double[N + 1][N + 1];
			visited = new boolean[N + 1];
			nodes = new Node[N + 1];

			for (int i = 1; i <= N; i++) {
				int x = Integer.parseInt(st1.nextToken());
				int y = Integer.parseInt(st2.nextToken());
				nodes[i] = new Node(i, x, y);
			}

			E = Double.parseDouble(br.readLine().trim());

			for (int i = 1; i < N; i++) {
				for (int j = i + 1; j <= N; j++) {
					Node node1 = nodes[i];
					Node node2 = nodes[j];
					double dist = E * (Math.pow((node1.x - node2.x), 2) + Math.pow((node1.y - node2.y), 2));
					pan[i][j] = dist;
					pan[j][i] = dist;
				}
			}

			ans = 0;

			PriorityQueue<Integer> q = new PriorityQueue<>();

			q.add(1);

			int picked = 0;

			System.out.println("판 찍어보자" + E);
			for (int i = 0; i < N + 1; i++) {
				for (int j = 0; j < N + 1; j++) {
					System.out.print(" " + pan[i][j]);
				}
				System.out.println();
			}

			while (!q.isEmpty()) {
				int node = q.poll();
				
				System.out.println("node == " + node);

				if (visited[node])
					continue;

				visited[node] = true;
				picked++;

				if (picked == N)
					break;

				double minValue = Double.MAX_VALUE;
				int minIdx = -1;

				for (int i = 1; i <= N; i++) {
					if (node == i || visited[i])
						continue;

					if (minValue > pan[node][i]) {
						minValue = pan[node][i];
						minIdx = i;
					}
				}
				System.out.println("minIdx == " + minIdx );
				System.out.println("이거 더하자" + pan[node][minIdx]);
				visited[minIdx] = true;
				ans += pan[node][minIdx];
				q.offer(minIdx);
			}
			bw.write("#");
			bw.write(' ');
			bw.write(Double.toString(ans));
			bw.write("\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
