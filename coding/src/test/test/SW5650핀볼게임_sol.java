package test;

import java.util.Scanner;

public class SW5650핀볼게임_sol {

		static int[][] map; // NxN 크기의 배열 -1 ~ 10 각각의 의미하는 바가 있다더라
		static int N, ans; // N : 5 ~ 100사이 , ans 최대 점수를 의미할거야!
		static int[] dr = { -1, 1, 0, 0 };
		static int[] dc = { 0, 0, -1, 1 }; // 상하좌우
		// 블록
		static int[][] block = {
				{0,1,2,3},//0번블록은 그냥 버려! (상하좌우) 0:상, 1:하, 2:좌, 3:우
				{1,3,0,2},
				{3,0,1,2},
				{2,0,3,1},
				{1,2,3,0},
				{1,0,3,2}
		};

		public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);

			int T = sc.nextInt();
			for (int tc = 1; tc <= T; tc++) {
				N = sc.nextInt();
				ans = 0; // 점수가 0점일 수도 있음
				map = new int[N + 2][N + 2];
				for (int i = 1; i <= N; i++) {
					for (int j = 1; j <= N; j++) {
						map[i][j] = sc.nextInt();
					}
				} // 입력끝

				// 5번 블록을 둘러싸자
				for (int i = 0; i <= N + 1; i++) {
					map[i][0] = map[i][N + 1] = map[0][i] = map[N + 1][i] = 5;
				}

				// 모든 곳/모든 방향 발사
				for (int i = 1; i <= N; i++) {
					for (int j = 1; j <= N; j++) {
						if (map[i][j] == 0) {
							for (int d = 0; d < 4; d++) {
								shoot(i, j, d);
							} // 4방향 발사
						} // 빈블록쳌
					} // 모든곳
				}

				System.out.println("#" + tc + " " + ans);

			} // tc
		}// main

		private static void shoot(int stR, int stC, int d) {
			int r = stR;
			int c = stC;

			int score = 0;

			while (true) {
				int nr = r + dr[d];
				int nc = c + dc[d]; // 다음좌표 구했다!

				if ((nr == stR && nc == stC) || map[nr][nc] == -1) {
					if (ans < score)
						ans = score;
					return;
				} // 시작좌표에 도달했거나, 블랙홀을 만나면 멈춰

				if (map[nr][nc] > 0) {
					// 블록을 만났다.
					if (map[nr][nc] < 6) {
						int blockNum = map[nr][nc];
						d = block[blockNum][d];
						score++;
					} else {
						// 웜홀 이라는 뜻
						out: for (int i = 1; i <= N; i++) {
							for (int j = 1; j <= N; j++) {
								if ((nr != i || nc != j) && map[i][j] == map[nr][nc]) {
									nr = i;
									nc = j;
									break out;
								}
							}
						}//웜홀찾기
					}//웜홀
				}

				r = nr;
				c = nc;

			} // 게임중

	}
}
