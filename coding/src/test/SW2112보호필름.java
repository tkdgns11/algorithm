package test;

import java.util.Arrays;
import java.util.Scanner;

public class SW2112보호필름 {

	// 테케를 어떻게 넣습니까? 복붙?
	static int D, W, K; // 행, 열, 통과기준
	static int[][] film;
	static int ans;
	static int[] A, B;
	static {

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			D = sc.nextInt();
			W = sc.nextInt();
			K = sc.nextInt();
			film = new int[D][W];
			for (int i = 0; i < D; i++) {
				for (int j = 0; j < W; j++) {
					film[i][j] = sc.nextInt();
				}
			} // 입력끝

			A = new int[W];
			B = new int[W];
			Arrays.fill(B, 1);

			// 아주아주아주 큰 수로 초기화 시켜놓자!
			ans = K;

			make(0, 0);

			System.out.println("#" + tc + " " + ans);

		} // tc
	}// main

	// r : 행, cnt : 지금까지 주입한 약품의 수
	private static void make(int r, int cnt) {
		if (check()) {
			ans = Math.min(ans, cnt);
			return;
		}
		if (ans < cnt)
			return; // 고만훼 필요없어 너는 정답이 될수 없어!
		if (r == D)
			return;

		// 재귀파트

//			1. 주입X
		make(r + 1, cnt);
		
		int[] tmp = film[r];

//			2. A주입
		film[r] = A;
		make(r + 1, cnt + 1);

//			3. B주입
		film[r] = B;
		make(r + 1, cnt + 1);

//			4.원상복구
		film[r] = tmp;
	}

	// 막을 검사할꺼야
	private static boolean check() {
		// 막검사! (열우선순회방식)
		for (int c = 0; c < W; c++) {
			boolean flag = false;
			int cnt = 1; // 연속된 값이 K개 있는지 쳌
			for (int r = 1; r < D; r++) {
				if (film[r][c] == film[r - 1][c])
					cnt++;
				else
					cnt = 1;

				if (cnt == K) {
					// 만족
					flag = true;
					break;
				}
			}
			/////////////////////////
			// 정상적인 경우, 비정상적인 경우 있겠다.
			if (!flag)
				return false; // 더이상의 진행은 무 의미해!
		}
		return true;
	}

}
