package src;

import java.util.*;

/**
 * 순열 순서신경 o > 조합 신경안씀  
 */
public class XOR연산최대만들기 {
	static int n, m;
	static int[] inputs;
	static int result;

	static void dfs(int depth, int start, int acc) {
		if (depth == m) {
			result = Math.max(acc, result);
			return;
		}

		if (n - start < m - depth) {
			return;
		}

		for (int i = start; i < n; i++) {
			dfs(depth + 1, i + 1, acc ^ inputs[i]);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		inputs = new int[n];
		for (int i = 0; i < n; i++) {
			inputs[i] = sc.nextInt();
		}
		// Please write your code here.

		result = Integer.MIN_VALUE;
		dfs(0, 0, 0);
		System.out.println(result);
	}
}
