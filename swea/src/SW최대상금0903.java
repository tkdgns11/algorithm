package src;

import java.io.*;
import java.util.*;

public class SW최대상금0903 {
	static int[] numbers;
	static Set<String> set;
	static int[] result;
	static int count;

	static void dfs(int depth, int[] arr) {
		if (depth == count) {
			boolean change = false;

			for (int i = 0; i < arr.length; i++) {
				if (result[i] == arr[i])
					continue;
				if (result[i] < arr[i]) {
					change = true;
					break;
				} else
					break;
			}

			if (change) {
				System.arraycopy(arr, 0, result, 0, arr.length);
			}
			return;
		}

		StringBuilder sb = new StringBuilder();
		sb.append(depth);
		for (int i = 0; i < arr.length; i++) {
			sb.append(arr[i]);
		}

		if (!set.add(sb.toString()))
			return;

		for (int i = 0; i < numbers.length-1; i++) {
			for (int j = i + 1; j < numbers.length; j++) {
				int tmp = arr[i];
				arr[i] = arr[j];
				arr[j] = tmp;
				dfs(depth + 1, arr);
				arr[j] = arr[i];
				arr[i] = tmp;
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			String nums = st.nextToken();

			numbers = new int[nums.length()];
			count = Integer.parseInt(st.nextToken());

			for (int i = 0; i < nums.length(); i++) {
				numbers[i] = Integer.parseInt("" + nums.charAt(i));
			}

			set = new HashSet<>();

			result = new int[nums.length()];
			dfs(0, numbers);

			bw.write("#" + tc + " ");
			for (int i = 0; i < result.length; i++) {
				bw.write("" + result[i]);
			}
			bw.write("\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}

}
