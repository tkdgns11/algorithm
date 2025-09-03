package src;

import java.io.*;
import java.util.*;

/**
 * 0은 빈 cell을 의미하며, 1은 core
 */
public class SW샘플_프로세서연결하기 {
	// 상 하 좌 우
	static final int[] dx = { 0, 0, -1, 1 };
	static final int[] dy = { -1, 1, 0, 0 };
	static int N;
	static int[][] pan;
	static List<Node> list;
	static int resultConn;
	static int resultLen;

	static class Node {
		int i;
		int j;

		Node(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	// 현재 연결된 선의 총 길이 -> 연결 코어 수 최대 & 그 중 전선 길이 최소
	static void check(int conn, int len) {
		if (resultConn < conn) {
			resultConn = conn;
			resultLen = len;
		} else if (resultConn == conn && resultLen > len) {
			resultLen = len;
		}
	}

	static void dfs(int idx, int conn, int len) {
		if (idx == list.size()) {
			check(conn, len);
			return;
		}
		
		if (list.size() - idx + conn < resultConn) return;
		if (conn + list.size() - idx == resultConn && len >= resultLen) return; // 별 영향 x 

		Node node = list.get(idx);
		// 연결 하는 경우
		for (int dir = 0; dir < 4; dir++) {
			List<int[]> path = new ArrayList<>();
			int checkSun = checkSun(node.i, node.j, dir, path);
			if (checkSun > 0) {
				dfs(idx + 1, conn + 1, len + checkSun);
				undo(path);
			}
		}
		// 연결 안하는 경우
		dfs(idx + 1, conn, len);
	}

	// 해당방향으로 연결 가능한지 확인
	static int checkSun(int i, int j, int dir, List<int[]> path) { // 0:상 1:하 2:좌 3:우
		int nx;
		int ny;

		nx = j + dx[dir];
		ny = i + dy[dir];

		while (nx >= 0 && ny >= 0 && nx <= N - 1 && ny <= N - 1) {
			if (pan[ny][nx] == 0) {
				path.add(new int[] { ny, nx });
				nx += dx[dir];
				ny += dy[dir];
			} else {
				path.clear();
				return -1;
			}
		}
		for (int[] p : path)
			pan[p[0]][p[1]] = 2;

		return path.size();
	}

	static void undo(List<int[]> path) {
		for (int[] p : path)
			pan[p[0]][p[1]] = 0;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {

			N = Integer.parseInt(br.readLine().trim());

			pan = new int[N][N];

			StringTokenizer st;

			list = new ArrayList<>();

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					pan[i][j] = Integer.parseInt(st.nextToken());
					if (i != 0 && i != N - 1 && j != 0 && j != N - 1 && pan[i][j] == 1) {
						list.add(new Node(i, j));
					}
				}
			}
			resultConn = Integer.MIN_VALUE;
			resultLen = Integer.MAX_VALUE;
			dfs(0, 0, 0);

			bw.write("#" + tc + " " + resultLen);
			bw.write("\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
