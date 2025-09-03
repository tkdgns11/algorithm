package src;

import java.io.*;
import java.util.*;

public class 큐_수영대회 {

	static class Node implements Comparable<Node> {
		int i;
		int j;
		int count; // 몇초만에 골인

		Node(int i, int j, int count) {
			this.i = i;
			this.j = j;
			this.count = count;
		}

		@Override
		public int compareTo(Node o) {
			return this.count - o.count;
		}
	}

	// 방향 위, 우, 아래, 좌
	static int[] dirX = { 0, 1, 0, -1 };

	static int[] dirY = { -1, 0, 1, 0 };

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine().trim());

		for (int test_case = 1; test_case <= T; test_case++) {

			int N = Integer.parseInt(br.readLine().trim()); // 수영장의 크기

			int[][] pan = new int[N][N];

			StringTokenizer st;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					pan[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 0 : 지나갈 수 있는 곳 , 1 : 장애물 , 2: 주기가 2초인 소용돌이
			st = new StringTokenizer(br.readLine().trim());
			int startI = Integer.parseInt(st.nextToken());
			int startJ = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine().trim());
			int endI = Integer.parseInt(st.nextToken());
			int endJ = Integer.parseInt(st.nextToken());

			PriorityQueue<Node> q = new PriorityQueue<>();

			Node startNode = new Node(startI, startJ, 0);

			q.offer(startNode);

			int result = -1;

			final int INF = Integer.MAX_VALUE / 2;
			
			int[][] dist = new int[N][N];
			for (int[] row : dist)
				Arrays.fill(row, INF);

			dist[startI][startJ] = 0;

			while (!q.isEmpty()) {
				Node currentNode = q.poll();
				if (currentNode.count > dist[currentNode.i][currentNode.j]) continue;

				if (currentNode.i == endI && currentNode.j == endJ) {
					result = currentNode.count;
					break;
				}

				for (int i = 0; i < 4; i++) {
					int nX = currentNode.j + dirX[i];
					int nY = currentNode.i + dirY[i];

					if (nX >= 0 && nX < N && nY >= 0 && nY < N && pan[nY][nX] != 1) {
						if (pan[nY][nX] == 2) { // 소용돌이

							int curT = currentNode.count;

							int rem = curT % 3;

							// '2초 주기 소용돌이'가 열리는 시점(rem==2)가 될 때까지 대기
							// 소용돌이는 2초에 없어지는데, 가는건 3초
							int wait = (2 - rem) % 3;

							// 총 소요 시간 = 대기(wait) + 이동(1)
							int arrival = curT + wait + 1;
							
							if (arrival < dist[nY][nX]) {
								dist[nY][nX] = arrival;
								q.offer(new Node(nY, nX, arrival));
		                    }
						} else {
							if (currentNode.count + 1 < dist[nY][nX]) {
								dist[nY][nX] = currentNode.count + 1;
								q.offer(new Node(nY, nX, currentNode.count + 1));
		                    }
						}
					}
				}
			}
			bw.write("#" + test_case + " " + result);
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}
}