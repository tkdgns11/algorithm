package src;

import java.io.*;
import java.util.*;

public class Baekjoon11053가장긴증가하는부분수열 {

	static int N;
	static int[] numbers;
	static int[] memo;  // 메모이제이션 배열

	// idx번째 숫자를 마지막으로 하는 가장 긴 증가 부분 수열의 길이
	static int solve(int idx) {
		// 이미 계산했으면 바로 리턴
		if(memo[idx] != -1) {
			return memo[idx];
		}

		// 기본값: 자기 자신만 포함 (길이 1)
		memo[idx] = 1;

		// idx 이전의 모든 원소 확인
		for(int j = 0; j < idx; j++) {
			// numbers[j] < numbers[idx]면 연결 가능
			if(numbers[j] < numbers[idx]) {
				memo[idx] = Math.max(memo[idx], solve(j) + 1);
			}
		}

		return memo[idx];
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine().trim());
		numbers = new int[N];
		memo = new int[N];
		Arrays.fill(memo, -1);  // -1로 초기화 (아직 계산 안 됨)

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		int maxLength = 0;
		for(int i = 0; i < N; i++) {
			maxLength = Math.max(maxLength, solve(i));
		}

		System.out.println(maxLength);
	}
}