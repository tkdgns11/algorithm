package src;

import java.io.*;
import java.util.*;

/**
 * 남학생은 남학생끼리, 여학생은 여학생끼리 한 방에는 같은 학년의 학생들을 배정 한 방에 한 명만 배정하는 것도 가능 한 방에 배정할 수
 * 있는 최대 인원 수 K
 * 
 * 조건에 맞게 모든 학생을 배정하기 위해 필요한 방의 최소 개수를 구하는 프로그램
 */
public class Baekjoon13300방배정 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());

		int K = Integer.parseInt(st.nextToken());

		int[][] arr = new int[7][2]; // 0~6
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int 성별 = Integer.parseInt(st.nextToken()); // 0:여 1:남
			int 학년 = Integer.parseInt(st.nextToken());

			arr[학년][성별]++;
		}

		int count = 0;

		for (int i = 1; i <= 6; i++) {
			for (int j = 0; j < 2; j++) {
				if (arr[i][j] != 0) {
					int check = arr[i][j] / K;
					count += check;
					int tmp = arr[i][j] % K;
					if (tmp != 0) {
						count++;
					}
				}
			}
		}
		System.out.println(count);
	}
}
