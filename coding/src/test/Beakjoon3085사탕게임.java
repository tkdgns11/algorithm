package test;

import java.io.*;
import java.util.*;

public class Beakjoon3085사탕게임 {
	static int[] dx = { 1, 0 }; // 우, 아래
	static int[] dy = { 0, 1 };
	static char[][] pan;
	static int N;
	static int Max;

	static void check(int i1, int j1, int i2, int j2) {
		int count1 = 1;
		int count2 = 1;
		int count3 = 1;
		int count4 = 1;

		for (int i = 0; i < N; i++) {
			if (i+1 <= N-1 && pan[i1][i] == pan[i1][i + 1]) {
				count1++;
			} else {
				Max = Math.max(count1, Max);
				count1 = 1;
			}

			if (i+1 <= N-1 && pan[i][j1] == pan[i + 1][j1]) {
				count2++;
			} else {
				Max = Math.max(count2, Max);
				count2 = 1;
			}
			if (i+1 <= N-1 && pan[i2][i] == pan[i2][i + 1]) {
				count3++;
			} else {
				Max = Math.max(count3, Max);
				count3 = 1;
			}

			if (i+1 <= N-1 && pan[i][j2] == pan[i + 1][j2]) {
				count4++;
			} else {
				Max = Math.max(count4, Max);
				count4 = 1;
			}
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine().trim()); // 보드의 크기

		pan = new char[N][N];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				pan[i][j] = str.charAt(j);
			}
		}

		Max = Integer.MIN_VALUE;

		int countGaroCheck = 1;
		int countSeroCheck = 1;
		for (int i = 0; i < N ; i++) {
			for (int j = 0; j < N ; j++) {
				if ((j+1 <= N-1) && pan[i][j] == pan[i][j + 1]) {
					countGaroCheck++;
				} else {
					Max = Math.max(countGaroCheck, Max);
					countGaroCheck = 1;
				}

				if ((j+1 <= N-1) && pan[j][i] == pan[j + 1][i]) {
					countSeroCheck++;
				} else {
					Max = Math.max(countSeroCheck, Max);
					countSeroCheck = 1;
				}
			}
		}

		// System.out.println("초기 최대" + Max);

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				char tmp = pan[i][j]; // 꺼내

				if (i + 1 <= N - 1) {
					char change1 = pan[i + 1][j];

					if (tmp != change1) {
						pan[i + 1][j] = pan[i][j]; // 교환
						pan[i][j] = change1;
						check(i, j, i + 1, j);

//						System.out.println("교환할때의 i랑 j 세로교환" + i+ " " + j);
//						for(int ii=0; ii<N; ii++) {
//							for(int jj=0; jj<N; jj++) {
//								System.out.print(pan[ii][jj] + " ");
//							}
//							System.out.println();
//						}

						pan[i][j] = tmp; // 원복
						pan[i + 1][j] = change1;
					}
				}

				if (j + 1 <= N - 1) {
					char change2 = pan[i][j + 1];

					if (tmp != change2) {
						pan[i][j + 1] = pan[i][j]; // 교환
						pan[i][j] = change2;
						check(i, j, i, j + 1);

//						System.out.println("교환할때의 i랑 j 가로교환" + i+ " " + j);
//						for(int ii=0; ii<N; ii++) {
//							for(int jj=0; jj<N; jj++) {
//								System.out.print(pan[ii][jj] + " ");
//							}
//							System.out.println();
//						}
						pan[i][j] = tmp; // 원복
						pan[i][j + 1] = change2;
					}
				}
			}
		}
		System.out.println(Max);
	}
}
