package src;

import java.io.*;
import java.util.*;

public class SW단순2진암호코드 {

	static int N, M;
	static int[][] pan;
	static int[] checkArr;
	static boolean isOk;
	static int sum;

	static int isOk(int[] arr) {
		int[] check = new int[4];

		int tmpCount = 0;

		int count = 1;
		int tmp = 0;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] != arr[i - 1]) {
				check[tmp] = count;
				tmpCount += count;
				count = 1;
				tmp++;
			} else {
				count++;
			}
		}

		check[3] = 7 - tmpCount;

		StringBuilder sb = new StringBuilder();
		for (int i : check) {
			sb.append(i);
		}

		switch (sb.toString()) {
		case "3211":
			return 0;
		case "2221":
			return 1;
		case "2122":
			return 2;
		case "1411":
			return 3;
		case "1132":
			return 4;
		case "1231":
			return 5;
		case "1114":
			return 6;
		case "1312":
			return 7;
		case "1213":
			return 8;
		case "3112":
			return 9;
		}
		return -1;
	}

	static int check(int[] arr) {
		int[] partArr = new int[7];
		int[] result = new int[8];
		Arrays.fill(result, -1);

		for (int i = 0; i < arr.length; i++) {
			partArr[i % 7] = arr[i];
			if (i % 7 == 6) {
				int partCheck = isOk(partArr);
				for (int ii = 0; ii < result.length; ii++) {
					if (result[ii] < 0) {
						result[ii] = partCheck;
						break;
					}
				}
			}
		}

		int oddSum = 0;
		int evenSum = 0;

		for (int j = 0; j < result.length; j++) {
			if (j % 2 == 0) {
				evenSum += result[j];
			} else {
				oddSum += result[j];
			}
		}

		sum = (evenSum * 3) + oddSum;

		if (sum % 10 == 0) {
			return oddSum + evenSum;
		} else {
			return 0;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			pan = new int[N][M];

			checkArr = new int[56];

			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < M; j++) {
					pan[i][j] = Integer.parseInt(str.charAt(j) + "");
				}
			}

			isOk = false;

			outer: for (int i = 0; i < N; i++) {
				for (int j = M - 1; j >= 56; j--) {
					if (pan[i][j] == 1) {
						System.arraycopy(pan[i], j - 55, checkArr, 0, 56);
						break outer;
					}
				}
			}
			bw.write("#" + tc + " " + check(checkArr) + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
