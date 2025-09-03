package src;

import java.io.*;
import java.util.*;

public class 큐_수영대회2 {

	static class Node {
		int i;
		int j;
		int count; // 몇초만에 골인
		int[][] pan;

		Node(int i, int j, int count, int[][] pan) {
			this.i = i;
			this.j = j;
			this.count = count;
			this.pan = pan;
		}
	}

	// 방향 위, 우, 아래, 좌. 다익스트라
	static int[] dirX = { 0, 1, 0, -1 };

	static int[] dirY = { -1, 0, 1, 0 };

	public static void main(String[] args) throws Exception {
		
		long startTime = System.currentTimeMillis();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine().trim());

		for (int test_case = 1; test_case <= T; test_case++) {

			int N = Integer.parseInt(br.readLine().trim()); // 수영장의 크기

			int[][] pan;

			pan = new int[N][N];

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

			Queue<Node> q = new LinkedList<>();

			Node startNode = new Node(startI, startJ, 0, pan);

			pan[startI][startJ] = 1;

			q.offer(startNode);

			int result = -1;

			while (!q.isEmpty()) {
				Node currentNode = q.poll();

				if (currentNode.i == endI && currentNode.j == endJ) {
					result = currentNode.count;
					break;
				}

				int[][] currentPan = currentNode.pan;

				for (int i = 0; i < dirX.length; i++) {
					int nX = currentNode.j + dirX[i];
					int nY = currentNode.i + dirY[i];

					if (nX >= 0 && nX < N && nY >= 0 && nY < N && currentPan[nY][nX] != 1) {
						int nextCount = currentNode.count + 1;

						int[][] panCopy = new int[N][N];

						for (int ii = 0; ii < N; ii++) {
							for (int j = 0; j < N; j++) {
								panCopy[ii][j] = currentPan[ii][j];
							}
						}

						if (currentPan[nY][nX] == 2) { // 소용돌이
							
							int curT = currentNode.count;

							int rem = curT % 3;
							
							// '2초 주기 소용돌이'가 열리는 시점(rem==2)가 될 때까지 대기
							// rem==0 → 2초, rem==1 → 1초, rem==2 → 0초
							// 소용돌이는 2초에 없어지는데, 가는건 3초
							int wait = (2 - rem) % 3;
							
							// 총 소요 시간 = 대기(wait) + 이동(1)
							int arrival = curT + wait + 1;

							panCopy[nY][nX] = 1;
							q.offer(new Node(nY, nX, arrival, panCopy));
							
						} else {
							panCopy[nY][nX] = 1;
							q.offer(new Node(nY, nX, nextCount, panCopy));
						}
					}
				}
			}
			bw.write("#" + test_case + " " + result);
			bw.newLine();
		}
		long endTime = System.currentTimeMillis();
		bw.write("걸린 시간 ::::::::::::::::::" + (endTime - startTime));
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}
}