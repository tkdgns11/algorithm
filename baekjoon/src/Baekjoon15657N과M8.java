package src;

import java.io.*;
import java.util.*;

/**
 * 자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오. 첫째 줄에 N과 M이
 * 주어진다. (1 ≤ M ≤ N ≤ 8) 수열은 사전 순으로 증가하는 순서로 출력 중복 x
 */
class Baekjoon15657N과M8 {
	static int N;
	static int M;
	static int[] input;
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

		for (int i = start; i < N; i++) {
			numbers[depth] = input[i];
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

		input = new int[N];

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(input); // input을 오름차순으로 정렬

		dfs(0, 0);

		bw.flush();
		bw.close();
		br.close();
	}
}
