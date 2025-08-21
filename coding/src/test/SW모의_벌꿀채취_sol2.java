package test;

import java.util.Arrays;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;

class SW모의_벌꿀채취2 {
	static int honey[][] = new int[16][16];
	static int n, m, c;

	static int honeyA[] = new int[8];
	static int honeyB[] = new int[8];

	static int dogHoney(int amount, int sum, int depth, int[] arr) {

		if (amount > c)
			return -1;

		if (depth == m) {
			return sum;
		}
		// System.out.println(depth + " " + Arrays.toString(arr));
		int ret1 = dogHoney(amount + arr[depth], sum + arr[depth] * arr[depth], depth + 1, arr);
		int ret2 = dogHoney(amount, sum, depth + 1, arr);

		return Math.max(ret1, ret2);
	}

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T;
		T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			String input = br.readLine();
			StringTokenizer st = new StringTokenizer(input);

			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());

			for (int i = 0; i < n; i++) {
				input = br.readLine();
				st = new StringTokenizer(input);
				for (int j = 0; j < n; j++) {
					int temp = Integer.parseInt(st.nextToken());
					honey[i][j] = temp;
					// System.out.print(honey[i][j]);
				}
				// System.out.println();
			}
			int answer = 0;

			for (int i = 0; i < n; i++) {
				for (int j = 0; j <= n - m; j++) {

					System.arraycopy(honey[i], j, honeyA, 0, m);
					// System.out.println(Arrays.toString(honeyA));
					int maxA = dogHoney(0, 0, 0, honeyA);
					int maxB = 0;
					// System.out.println(maxA);
					for (int jj = j + 2 * m - 1; jj < n; jj++) {
						System.arraycopy(honey[i], jj - m + 1, honeyB, 0, m);

						maxB = Math.max(maxB, dogHoney(0, 0, 0, honeyB));
					}

					for (int ii = i + 1; ii < n; ii++) {

						for (int jj = 0; jj <= n - m; jj++) {
							System.arraycopy(honey[ii], jj, honeyB, 0, m);

							maxB = Math.max(maxB, dogHoney(0, 0, 0, honeyB));
						}
					}
					answer = Math.max(answer, maxA + maxB);
				}
			}

			System.out.println("#" + test_case + " " + answer);
		}

	}
}