package test;

import java.io.*;
import java.util.*;

/**
 * 문제에서 최대 300 이라고 해서 300 으로 코딩했는데, 생각해보니 이보다 작게 주어졌는데 여기까지 체크하면 안됨.
 */
class Baekjoon7562나이트의이동 {

	// 그림대로 주어진 8방향
	static final int[] dX = { 1, 2, 2, 1, -1, -2, -2, -1 };
	static final int[] dY = { -2, -1, 1, 2, 2, 1, -1, -2 };

	static class Node {
		int i;
		int j;

		Node(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine().trim());

		for (int t = 0; t < T; t++) {

			int I = Integer.parseInt(br.readLine().trim()); // 한변의 길이

			//int[][] pan = new int[I][I]; // 0 ~ I-1

			int[][] dist = new int[I][I];

			for (int i = 0; i < I; i++) {
				Arrays.fill(dist[i], -1);
			}

			StringTokenizer st = new StringTokenizer(br.readLine());

			int Si = Integer.parseInt(st.nextToken()); // 시작점 i
			int Sj = Integer.parseInt(st.nextToken()); // 시작점 j

			st = new StringTokenizer(br.readLine());

			int Ei = Integer.parseInt(st.nextToken()); // 도착점 i
			int Ej = Integer.parseInt(st.nextToken()); // 도착점 j

			dist[Si][Sj] = 0;

			Queue<Node> q = new LinkedList<>();

			q.add(new Node(Si, Sj));

			while (!q.isEmpty()) {
				Node current = q.poll();

				if (current.i == Ei && current.j == Ej) {
					break;
				}

				for (int i = 0; i < 8; i++) {
					int nx = current.j + dX[i];
					int ny = current.i + dY[i];

					if (nx >= 0 && nx < I && ny >= 0 && ny < I && dist[ny][nx] == -1) {
						dist[ny][nx] = dist[current.i][current.j] + 1;
						q.offer(new Node(ny, nx));
					}
				}
			}
			bw.write("" + dist[Ei][Ej]);
			bw.write("\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}
}