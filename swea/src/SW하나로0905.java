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
			this.idx = idx;
			this.x = x;
			this.y = y;
		}

		Node() {
		}
	}

	static class Edge implements Comparable<Edge> {
		int to;
		double w;

		Edge(int to, double w) {
			this.to = to;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.w, o.w);
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
					Node a = nodes[i], b = nodes[j];
					long dx = (long) a.x - b.x;
					long dy = (long) a.y - b.y;
					double dist = E * (dx * dx + dy * dy);
					pan[i][j] = dist;
					pan[j][i] = dist;
				}
			}

			ans = 0.0;

			PriorityQueue<Edge> pq = new PriorityQueue<>();
			pq.add(new Edge(1, 0.0));

			int picked = 0;

			while (!pq.isEmpty()) {
				Edge cur = pq.poll();
				int next = cur.to;
				if (visited[next]) continue;
				visited[next] = true;
				picked++;

				ans += cur.w;
				if (picked == N) break;

				for (int i = 1; i <= N; i++) {
					if (!visited[i] && i != next) {
						pq.offer(new Edge(i, pan[next][i]));
					}
				}
			}
			bw.write('#');
			bw.write(Integer.toString(tc));
			bw.write(' ');
			bw.write(Long.toString(Math.round(ans)));
			bw.write('\n');
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
