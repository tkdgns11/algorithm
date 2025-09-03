package src;

import java.io.*;
import java.util.*;

/* 자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
   1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
 * 
 * */
class Baekjoon15649N과M1 {
	static boolean[] visited;
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

	static void dfs(int depth) throws IOException {
		if (depth == M) {
			print();
			return;
		}

		for (int i = 1; i <= N; i++) {
			if (!visited[i]) { // 현재 경로에서 이미 쓴 숫자는 다시 못 쓰게 막는다. (중복 방지)
				visited[i] = true;
				numbers[depth] = i;
				dfs(depth + 1);
				visited[i] = false;
			}
		}
	}

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		visited = new boolean[N + 1];
		numbers = new int[M];

		// 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
		dfs(0);

		bw.flush();
		bw.close();
		br.close();
	}
}
