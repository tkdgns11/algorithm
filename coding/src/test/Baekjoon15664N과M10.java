package test;

import java.io.*;
import java.util.*;

/**
 * 자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오. 
 * N개의 자연수 중에서 M개를 고른 수열
 * 중복 수열 제거
 * 동일 깊이에서 last 가지치기로 중복 수열 제거
 * 고른 수열은 비내림차순이어야 한다.
    길이가 K인 수열 A가 A1 ≤ A2 ≤ ... ≤ AK-1 ≤ AK를 만족하면, 비내림차순이라고 한다.
 * 
 * visited: “같은 인덱스(카드) 두 번 쓰지 마!”
   last: “같은 값으로 시작하는 분기 두 번 타지 마!”(= 깊이별 중복 값 제거)
 */
class Baekjoon15664N과M10 {
	static boolean[] visited;
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
		
		int last = Integer.MIN_VALUE; // 이 깊이에서 마지막으로 사용한 값. 이번 depth에서 이미 쓴 값
		
		for (int i = start; i < N; i++) {
            if (input[i] == last) continue; // 같은 깊이에서 같은 값으로 시작하는 가지는 스킵
			if(!visited[i]) {
				visited[i] = true;
				numbers[depth] = input[i];
				last = input[i];   
				dfs(depth + 1, i);
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

		visited = new boolean[N];
		
		numbers = new int[M];
		
		input = new int[N];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(input); // input을 오름차순으로 정렬

		dfs(0, 0);

		bw.flush();
		bw.close();
		br.close();
	}
}
