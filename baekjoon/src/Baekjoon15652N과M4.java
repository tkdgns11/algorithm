package src;

import java.io.*;
import java.util.*;

/**
 * 
 * 자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
   1부터 N까지 자연수 중에서 M개를 고른 수열
   같은 수를 여러 번 골라도 된다.
   고른 수열은 비내림차순이어야 한다.
   길이가 K인 수열 A가 A1 ≤ A2 ≤ ... ≤ AK-1 ≤ AK를 만족하면, 비내림차순이라고 한다.
 */
class Baekjoon15652N과M4 {
	static int N;
	static int M;
	static int[] numbers;
	static BufferedWriter bw;

	static void print() throws IOException {
		for (int i = 0; i < M; i++) {
			bw.write("" + numbers[i] + " ");
		}
		bw.write("\n");
	}

	static void dfs(int depth, int start) throws IOException {
		if (depth == M) {
			print();
			return;
		}

		for (int i = start; i <= N; i++) {
			numbers[depth] = i;
			dfs(depth + 1, i);
		}
	}

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		numbers = new int[M];

		// 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
		dfs(0, 1);

		bw.flush();
		bw.close();
		br.close();
	}
}
