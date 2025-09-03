package src;

import java.io.*;
import java.util.*;

public class 색종이 {
	public static void main(String[] args) throws Exception {
		BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter  bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// 도화지 크기 100×100
		int[][] pan = new int[100][100];

		int N = Integer.parseInt(br.readLine().trim());  // 색종이 개수

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int startX = Integer.parseInt(st.nextToken());  // 왼쪽 변으로부터 거리 (0 ≤ startX ≤ 90)
			int startY = Integer.parseInt(st.nextToken());  // 아래 변으로부터 거리 (0 ≤ startY ≤ 90)

			// 수학적 좌표 (startX, startY) 에서 가로·세로 10칸
			for (int x = startX; x < startX + 10; x++) {
				for (int y = startY; y < startY + 10; y++) {
					int Y = 99 - y;  // 아래→위 수학적 Y를 위→아래 배열 인덱스로 변환
					int X = x;       // 수학적 X는 그대로 열 인덱스
					pan[Y][X] = 1;
				}
			}
		}

		// 채워진 칸(1)의 총합 계산
		int result = 0;
		for (int r = 0; r < 100; r++) {
			for (int c = 0; c < 100; c++) {
				result += pan[r][c];
			}
		}

		bw.write(String.valueOf(result));
		bw.flush();
		bw.close();
		br.close();
	}
}
