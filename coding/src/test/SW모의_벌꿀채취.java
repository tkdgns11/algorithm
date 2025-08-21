package test;

import java.util.*;
import java.io.*;

public class SW모의_벌꿀채취 {

	static int N;
	static int M;
	static int C;

	static int[][] pan;

	static int MaxBenefit; // 최대 이익

	static List<Integer> choice1;
	static List<Integer> choice2;

	static void calcBenefit(List<Integer> choice1calc, List<Integer> choice2calc) {
		int check = 0;
		for (int i = 0; i < choice1calc.size(); i++) {
			check += choice1calc.get(i);
		}
		if (check > C)
			return;

		check = 0;
		for (int i = 0; i < choice2calc.size(); i++) {
			check += choice2calc.get(i);
		}
		if (check > C)
			return;

		int benefit = 0;
		for (int i = 0; i < choice1calc.size(); i++) {
			benefit += (choice1calc.get(i) * choice1calc.get(i));
		}

		for (int i = 0; i < choice2calc.size(); i++) {
			benefit += (choice2calc.get(i) * choice2calc.get(i));
		}

		MaxBenefit = Math.max(MaxBenefit, benefit);
	}

	static void calc(int depth, List<Integer> choice1calc, List<Integer> choice2calc) {
		if (depth == M) {
			calcBenefit(choice1calc, choice2calc);
			return;
		}

		int num1 = choice1.get(depth);
		// 1넣기
		choice1calc.add(num1);
		calc(depth + 1, choice1calc, choice2calc);
		choice1calc.remove(choice1calc.size() - 1);

		int num2 = choice2.get(depth);
		// 2넣기
		choice2calc.add(num2);
		calc(depth + 1, choice1calc, choice2calc);
		choice2calc.remove(choice2calc.size() - 1);

		// 둘다 넣기
		choice1calc.add(num1);
		choice2calc.add(num2);
		calc(depth + 1, choice1calc, choice2calc);
		choice1calc.remove(choice1calc.size() - 1);
		choice2calc.remove(choice2calc.size() - 1);

		// 둘다 안넣기
		calc(depth + 1, choice1calc, choice2calc);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			pan = new int[N][N];

			MaxBenefit = Integer.MIN_VALUE;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					pan[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j <= N - M; j++) {
					// choice1
					if (j + M - 1 <= N - 1) {
						choice1 = new ArrayList<>();
						for (int k = 0; k < M; k++) {
							choice1.add(pan[i][j + k]);
						}
					}

					/*
					// 옆에 오는 판단
					int rowGap = 0;
					while (j + M + M + rowGap <= N - 1) {
						choice2 = new ArrayList<>();
						for (int k = 0; k < M; k++) {
							choice2.add(pan[i][j + M + rowGap + k]);
						}
						calc(0, new ArrayList<>(), new ArrayList<>());
						rowGap++;
					}
					*/
					
					// 옆에 오는 판단 (같은 행에서 오른쪽 구간)
					int rowGap = 0;
					while (j + M + rowGap + (M - 1) <= N - 1) { // ✅ 올바른 범위 검사
					    choice2 = new ArrayList<>();
					    for (int k = 0; k < M; k++) {
					        choice2.add(pan[i][j + M + rowGap + k]);
					    }
					    calc(0, new ArrayList<>(), new ArrayList<>());
					    rowGap++;
					}


					// 아래쪽 조합
					int colGap = 1;
					while (i + colGap < N) {
						rowGap = 0;
						while (rowGap + (M - 1) <= (N - 1)) {
							choice2 = new ArrayList<>();
							for (int k = 0; k < M; k++) {
								choice2.add(pan[i + colGap][rowGap + k]);
							}
							calc(0, new ArrayList<>(), new ArrayList<>());
							rowGap++;
						}
						colGap++;
					}
				}
			}
			bw.write("#" + tc + " " + MaxBenefit);
			bw.write("\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
