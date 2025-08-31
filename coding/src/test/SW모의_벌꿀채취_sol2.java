package test;

import java.util.Arrays;
import java.util.Scanner;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;

/**
 * SWEA 모의 - 벌꿀채취(변형)
 *
 * 규칙 요약 - N x N 벌통에서 두 명(A, B)이 각각 가로로 연속한 M칸을 '선택'한다. (선택 구간은 서로 겹치면 안 됨) - 단,
 * 선택한 M칸 모두를 채취하는 게 아니라 각자 그 M칸의 '부분집합'을 골라 채취할 수 있다. - 개인의 채취량(부분집합 원소 합)은 C를
 * 넘을 수 없고, 이때 이익은 (선택한 칸의 꿀 양)^2 의 합. - 목표: A와 B의 이익 합을 최대화.
 *
 * 풀이 개요 1) 가로로 길이 M인 모든 구간을 A의 후보로 훑는다. 2) A 구간에 대해, 그 M칸에서 '부분집합'을 선택하는
 * 완전탐색(2^M)을 통해 A의 최대 이익(maxA)을 구한다. 3) B는 A와 같은 행에서는 A 구간과 겹치지 않는 오른쪽 구간들만,
 * 아래쪽 행에서는 모든 구간을 후보로 하여 각 구간마다 동일하게 2^M 완전탐색으로 최대 이익을 구하고 그중 최댓값(maxB)을 취한다.
 * 4) answer = max(answer, maxA + maxB)
 *
 * 시간복잡도(대략) - 부분집합 탐색: O(2^M) (M ≤ 8이므로 최대 256) - A 구간 수: N * (N - M + 1) - 각
 * A에 대해 B 구간을 살펴보는 수: O(N * (N - M + 1)) 수준 => 전체 대략 O(N^2 * N^2 * 2^M) ≈ O(N^4
 * * 2^M) 정도지만 N, M이 작아 통과 가능.
 */
class SW모의_벌꿀채취2 {
	// 문제 제약 상 최대 크기에 맞춰 여유롭게 잡아둔 배열
	static int honey[][] = new int[16][16];
	static int n, m, c;

	// 길이 M 구간의 값을 담아 재활용하는 버퍼
	static int honeyA[] = new int[8];
	static int honeyB[] = new int[8];

	/**
	 * 부분집합 완전탐색(재귀): - arr[0..m-1]에서 임의 부분집합을 골라 합(=amount)이 C를 넘지 않도록 하면서 이익(=제곱합
	 * sum)을 최대화.
	 *
	 * @param amount 현재까지 선택한 꿀의 총합
	 * @param sum    현재까지의 이익(제곱합)
	 * @param depth  arr에서 몇 번째 원소를 보고 있는지(0..m)
	 * @param arr    길이 m의 선택 구간 값
	 * @return 가능한 최대 이익(제곱합). 제한 위반 시 -1을 반환해 가지치기 효과.
	 */
	static int dogHoney(int amount, int sum, int depth, int[] arr) {
		// 제약 위반: 더 볼 필요 없음
		if (amount > c)
			return -1;

		// 모든 원소를 다 봤다면 현재의 이익 합계를 반환
		if (depth == m)
			return sum;

		// 현재 칸을 '고르는' 경우
		int ret1 = dogHoney(amount + arr[depth], sum + arr[depth] * arr[depth], depth + 1, arr);
		// 현재 칸을 '고르지 않는' 경우
		int ret2 = dogHoney(amount, sum, depth + 1, arr);

		// 두 경우 중 최댓값
		return Math.max(ret1, ret2);
	}

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());

			// 벌통 입력
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					honey[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int answer = 0;

			// A의 가로 구간 시작점(i, j)을 전부 훑음
			for (int i = 0; i < n; i++) {
				for (int j = 0; j <= n - m; j++) {
					// A가 고른 길이 m 구간을 honeyA로 복사
					System.arraycopy(honey[i], j, honeyA, 0, m);

					// A 구간에서 가능한 '부분집합' 중 최대 이익
					int maxA = dogHoney(0, 0, 0, honeyA);

					// B 구간 최대 이익을 매번 다시 계산
					int maxB = 0;

					// (1) 같은 행 i에서, A 구간과 '겹치지 않는' 오른쪽 구간만 탐색
					// 아래 루프는 jj를 "구간의 끝 인덱스"로 잡고 시작점을 (jj - m + 1)로 환산한다.
					// jj의 최소값을 (j + 2*m - 1)로 잡으면 시작점이 (j + m) 이상이 되어 A 오른쪽만 보게 됨.
					// 같은 행 i, A의 오른쪽만
					for (int s = j + m; s <= n - m; s++) { // s: B 구간의 시작
						System.arraycopy(honey[i], s, honeyB, 0, m);
						maxB = Math.max(maxB, dogHoney(0, 0, 0, honeyB));
					}

					// (2) 아래쪽 행들(ii > i)은 같은 행이 아니므로 어떤 구간이든 겹치지 않음.
					for (int ii = i + 1; ii < n; ii++) {
						for (int jj = 0; jj <= n - m; jj++) {
							System.arraycopy(honey[ii], jj, honeyB, 0, m);
							maxB = Math.max(maxB, dogHoney(0, 0, 0, honeyB));
						}
					}

					// A+B 최댓값 갱신
					answer = Math.max(answer, maxA + maxB);
				}
			}

			System.out.println("#" + test_case + " " + answer);
		}
	}
}
