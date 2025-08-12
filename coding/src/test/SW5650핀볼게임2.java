package test;

import java.util.*;
import java.io.*;

public class SW5650핀볼게임2 {
	static final char[] dirr = { 'U', 'D', 'L', 'R' };
	// 상U 하D 좌L 우R 무방향S
	static final int[] dx = { 0, 0, -1, 1 };
	static final int[] dy = { -1, 1, 0, 0 };
	static int N;
	static int[][] pan;
	static int maxScore;
	static List<Node>[] wormCheck;

	static boolean isOut(int x, int y) {
		return x >= 0 && x <= N - 1 && y >= 0 && y <= N - 1;
	}

	static class Node {
		int i;
		int j;
		int number;

		Node(int i, int j, int number) {
			this.i = i;
			this.j = j;
			this.number = number;
		}
	}

	// dir: 0=U, 1=D, 2=L, 3=R
	// dir: 0=U, 1=D, 2=L, 3=R
	static int simulate(int sy, int sx, int dir) {
		int y = sy, x = sx, d = dir, score = 0;
		boolean moved = false; // 최소 1스텝 후 시작점 복귀 인정
		boolean[][][] visited = new boolean[N][N][4];
		visited[y][x][d] = true; // 시작 상태 기록

		while (true) {
			int nx = x + dx[d];
			int ny = y + dy[d];

			// 1) 경계 반사 (같은 칸에서 방향만 반전, 점수+1)
			if (!isOut(nx, ny)) {
				d ^= 1; // 0<->1, 2<->3
				score++;
				moved = true;
				if (visited[y][x][d])
					return score; // 사이클 차단
				visited[y][x][d] = true;
				continue;
			}

			int cell = pan[ny][nx];

			// 2) 블랙홀
			if (cell == -1)
				return score;

			// 3) 시작점 복귀
			if (moved && ny == sy && nx == sx)
				return score;

			// 4) 웜홀 (방향 유지, 위치만 텔레포트)
			if (cell >= 6 && cell <= 10) {
				List<Node> sw = wormCheck[cell];
				Node a = sw.get(0), b = sw.get(1);
				if (a.i == ny && a.j == nx) {
					y = b.i;
					x = b.j;
				} else {
					y = a.i;
					x = a.j;
				}
				moved = true;
				if (visited[y][x][d])
					return score;
				visited[y][x][d] = true;
				continue;
			}

			// 5) 블록 반사 (현재 규칙 유지: 막힘 집합이면 반사)
			if (d == 0 && (cell == 1 || cell == 4 || cell == 5)) { // 위→아래
				d = 1;
				score++;
				moved = true;
				if (visited[y][x][d])
					return score;
				visited[y][x][d] = true;
				continue;
			}
			if (d == 1 && (cell == 2 || cell == 3 || cell == 5)) { // 아래→위
				d = 0;
				score++;
				moved = true;
				if (visited[y][x][d])
					return score;
				visited[y][x][d] = true;
				continue;
			}
			if (d == 2 && (cell == 3 || cell == 4 || cell == 5)) { // 좌→우
				d = 3;
				score++;
				moved = true;
				if (visited[y][x][d])
					return score;
				visited[y][x][d] = true;
				continue;
			}
			if (d == 3 && (cell == 1 || cell == 2 || cell == 5)) { // 우→좌
				d = 2;
				score++;
				moved = true;
				if (visited[y][x][d])
					return score;
				visited[y][x][d] = true;
				continue;
			}

			// 6) 빈칸 등 통과: 한 칸 전진
			y = ny;
			x = nx;
			moved = true;
			if (visited[y][x][d])
				return score; // 사이클 차단
			visited[y][x][d] = true;
		}
	}

	/**
	 * 출발 위치와 진행 방향을 임의로 선정가능 작은 핀볼 하나가 상, 하, 좌, 우 중 한 방향으로 움직인다. 게임은 핀볼이 출발 위치로
	 * 돌아오거나, 블랙홀에 빠질 때 끝나게 되며, 점수는 벽이나 블록에 부딪힌 횟수가 된다. (웜홀을 통과하는 것은 점수에 포함되지 않는다.)
	 * 
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine().trim());

			pan = new int[N][N];

			wormCheck = new List[11]; // 0~10

			for (int i = 0; i < wormCheck.length; i++) {
				wormCheck[i] = new ArrayList<Node>();
			}

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					pan[i][j] = Integer.parseInt(st.nextToken());
					if (pan[i][j] >= 6 && pan[i][j] <= 10) { // 웜홀
						wormCheck[pan[i][j]].add(new Node(i, j, pan[i][j]));
					}
				}
			}

			maxScore = 0; // 음수일 필요 없음
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (pan[i][j] == 0) {
						for (int d = 0; d < 4; d++) {
							maxScore = Math.max(maxScore, simulate(i, j, d));
						}
					}
				}
			}

			bw.write("#" + t + " " + maxScore);
			bw.write("\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}
}
