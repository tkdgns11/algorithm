package test;

import java.io.*;
import java.util.*;

public class SW7793오나의여신님 {

	static int N;
	static int M;

	static int si;
	static int sj;
	static int di;
	static int dj;
//	static int ai;
//	static int aj;

	// 상 하 좌 우
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static Map<Integer, char[][]> pan;
	static boolean find;

	static boolean resultCheck(int time, int i, int j) {
		char[][] panCheck;

		if (!pan.containsKey(time)) {
			makePan(time);
		}
		panCheck = pan.get(time);

		if (i == di && j == dj) {
			find = true;
			return false;
		}
		
		if() {
			
		}
	}

	static void dfs(int time, int i, int j) {
		if(!resultCheck(time, i, j));
		
		for(int ii=0; ii<4; ii++) {
			
			dfs();
		}
	}

	static void makePan(int time) {
		char[][] panCopy = pan.get(time - 1);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (panCopy[i][j] == '*') {
					for (int k = 0; k < 4; k++) {
						int nx = j + dx[k];
						int ny = i + dy[k];

						if (ny >= 0 && ny < N && nx >= 0 && nx < M && panCopy[ny][nx] != 'X') {
							panCopy[ny][nx] = '*';
						}
					}
				}
			}
		}
		pan.put(time, panCopy);
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			char[][] pan = new char[N][M];

			si = -1;
			sj = -1;
			di = -1;
			dj = -1;
			// ai = -1;
			// aj = -1;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					pan[i][j] = st.nextToken().charAt(0);
					if (pan[i][j] == 'S') {
						si = i;
						sj = j;
					} else if (pan[i][j] == 'D') {
						di = i;
						dj = j;
					}
				}
			}

			/**
			 * 수연이의 위치‘S’, 여신의 공간은 ‘D’, 돌의 위치는 ‘X’, 악마는 ‘*’로 주어진다. ‘.’는 평범한 지역 불가능하면 GAME
			 * OVER 출력
			 */

		}

	}

}
