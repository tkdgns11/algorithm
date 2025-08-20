package test;

import java.io.*;
import java.util.*;

class Baekjoon2178미로탐색 {
	// 상 하 좌 우
	static final int[] dX = { 0, 0, -1, 1 };
	static final int[] dY = { -1, 1, 0, 0 };
	static int N;
	static int M;
	static int[][] pan;
	static int[][] dist;

	static class Node {
		int i;
		int j;

		Node(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	/**
	 * 1: 이동가능, 0: 이동불가 (1, 1)에서 출발하여 (N, M)의 위치로 이동할 때 지나야 하는 최소의 칸 수를 구하는 프로그램 항상
	 * 도착위치로 이동할 수 있는 경우만 입력으로 주어진다. (0,0) -> (N-1, M-1)
	 */
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		pan = new int[N][M];
		dist = new int[N][M];

		for (int i = 0; i < N; i++) {
			String str = br.readLine().trim();
			for (int j = 0; j < M; j++) {
				pan[i][j] = str.charAt(j) - '0'; // str.charAt(j) 주의
			}
		}

		Queue<Node> q = new LinkedList<>();

		Node startNode = new Node(0, 0);
		pan[0][0] = 0;
		q.add(startNode);
		dist[0][0] = 1;
		
		while (!q.isEmpty()) {
			Node cNode = q.poll();

			if (cNode.i == N - 1 && cNode.j == M - 1)
				break;
			
			for(int i=0; i<4; i++) {
				int nX = cNode.j + dX[i];
				int nY = cNode.i + dY[i];
				
				if(nX >= 0 && nX < M && nY >= 0 && nY < N && pan[nY][nX] == 1) {
					pan[nY][nX] = 0;
					dist[nY][nX] = dist[cNode.i][cNode.j] + 1;
					q.offer(new Node(nY, nX));
				}
			}
		}
		
		bw.write("" + dist[N-1][M-1]);
		bw.flush();
		bw.close();
		br.close();
	}
}
