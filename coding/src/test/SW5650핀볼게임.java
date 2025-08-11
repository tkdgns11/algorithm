package test;

import java.util.*;
import java.io.*;

public class SW5650핀볼게임 {
	static final char[] dirr = { 'U', 'D', 'L', 'R' };
	// 상U 하D 좌L 우R 무방향S
	static final int[] dx = { 0, 0, -1, 1 };
	static final int[] dy = { -1, 1, 0, 0 };
	static int N;
	static int[][] pan;
	static int maxScore;
	static List<Node>[] wormCheck;
	
	static boolean isOut(int x, int y) {
		return x >= 0 && x <= N-1 && y >= 0 && y <= N-1;
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

	static void dfs(int type, char dir, int count, int I, int J, int startI, int startJ, int step) {
		if ((I == startI && J == startJ && step > 0) || type == -1) {
			maxScore = Math.max(maxScore, count);
			return;
		}

		if (dir == 'S') { // 방향이 없는 경우 네방향 탐색. 어느 위치에서 어느 방향으로 출발했느냐에 따라 최댓값 구하기.
			for (int i = 0; i < 4; i++) {
				dfs(pan[I][J], dirr[i], count, I, J, startI, startJ, step);
			}
		} else if (dir == 'U') {
			int nx = J + dx[0];
			int ny = I + dy[0];
			
			if(!isOut(nx, ny)) {
				dfs(pan[I][J], 'D', count+1, I, J, startI, startJ, step+1);
			} else if (ny == 0 || pan[ny][nx] == 1 || pan[ny][nx] == 4 || pan[ny][nx] == 5) { // 위쪽이 벽 or 블록으로 막힌경우
				dfs(pan[I][J], 'D', count + 1, I, J, startI, startJ, step + 1);
			} else if (pan[ny][nx] >= 6 && pan[ny][nx] <= 10) { // 웜홀인 경우
				List<Node> switchNodes = wormCheck[pan[ny][nx]];
				for (Node node : switchNodes) {
					if (node.i != ny || node.j != nx) {
						dfs(pan[node.i][node.j], 'U', count, node.i, node.j, startI, startJ, step + 1);
						break;
					}
				}
			} else {
				dfs(pan[ny][nx], 'U', count, ny, nx, startI, startJ, step + 1);
			}
		} else if (dir == 'D') {
			int nx = J + dx[1];
			int ny = I + dy[1];

			// 아래가 막히는 경우
			if(!isOut(nx, ny)) {
				dfs(pan[I][J], 'U', count+1, I, J, startI, startJ, step+1);
			} else if (ny == N - 1 || pan[ny][nx] == 2 || pan[ny][nx] == 3 || pan[ny][nx] == 5) { // 아래쪽이 벽 or 블록으로 막힌경우
				dfs(pan[I][J], 'U', count + 1, I, J, startI, startJ, step + 1);
			} else if (pan[ny][nx] >= 6 && pan[ny][nx] <= 10) { // 웜홀인 경우
				List<Node> switchNodes = wormCheck[pan[ny][nx]];
				for (Node node : switchNodes) {
					if (node.i != ny || node.j != nx) {
						dfs(pan[node.i][node.j], 'D', count, node.i, node.j, startI, startJ, step + 1);
						break;
					}
				}
			} else {
				dfs(pan[ny][nx], 'D', count, ny, nx, startI, startJ, step + 1);
			}
		} else if (dir == 'L') {
			int nx = J + dx[2];
			int ny = I + dy[2];

			// 왼쪽이 막히는 경우
			if(!isOut(nx, ny)) {
				dfs(pan[I][J], 'R', count+1, I, J, startI, startJ, step+1);
			} else if (nx == 0 || pan[ny][nx] == 3 || pan[ny][nx] == 4 || pan[ny][nx] == 5) { // 왼쪽이 벽 or 블록으로 막힌경우
				dfs(pan[I][J], 'R', count + 1, I, J, startI, startJ, step + 1);
			} else if (pan[ny][nx] >= 6 && pan[ny][nx] <= 10) { // 웜홀인 경우
				List<Node> switchNodes = wormCheck[pan[ny][nx]];
				for (Node node : switchNodes) {
					if (node.i != ny || node.j != nx) {
						dfs(pan[node.i][node.j], 'L', count, node.i, node.j, startI, startJ, step + 1);
						break;
					}
				}
			} else {
				dfs(pan[ny][nx], 'L', count, ny, nx, startI, startJ, step + 1);
			}
		} else if (dir == 'R') {
			int nx = J + dx[3];
			int ny = I + dy[3];

			// 오른쪽이 막히는 경우
			if(!isOut(nx, ny)) {
				dfs(pan[I][J], 'L', count+1, I, J, startI, startJ, step+1);
			} else if (nx == N - 1 || pan[ny][nx] == 1 || pan[ny][nx] == 2 || pan[ny][nx] == 5) { // 왼쪽이 벽 or 블록으로 막힌경우
				dfs(pan[I][J], 'L', count + 1, I, J, startI, startJ, step + 1);
			} else if (pan[ny][nx] >= 6 && pan[ny][nx] <= 10) { // 웜홀인 경우
				List<Node> switchNodes = wormCheck[pan[ny][nx]];
				for (Node node : switchNodes) {
					if (node.i != ny || node.j != nx) {
						dfs(pan[node.i][node.j], 'R', count, node.i, node.j, startI, startJ, step + 1);
						break;
					}
				}
			} else {
				dfs(pan[ny][nx], 'R', count, ny, nx, startI, startJ, step + 1);
			}
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

			maxScore = Integer.MIN_VALUE;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// 각각 돌면서 전체 탐색
					if (pan[i][j] == 0) {
						dfs(pan[i][j], 'S', 0, i, j, i, j, 0);
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
