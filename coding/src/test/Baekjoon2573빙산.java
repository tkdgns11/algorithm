package test;

import java.io.*;
import java.util.*;

class Baekjoon2573빙산 {

	public static void main(String args[]) throws Exception {

		/**
		 * 2차원 배열에서 동서남북 방향으로 붙어있는 칸들은 서로 연결되어 있다고 말한다.
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 행 갯수
		int M = Integer.parseInt(st.nextToken()); // 열 갯수 
		
		int[][] pan = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				pan[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		/**
		 * BFS DFS 의미가 있나. 매 초마다 전체 배열 돌면서 4방향 탐색해서 없애고, 
		 * 매초마다 하나 선택해서 DFS 해서 count 2이상 나오면 break;
		 */
		
		// 종료조건 : 빙산이 분리되는 최초의 시간
		
		// 매 초마다 녹는 빙산을 모아놓고 한번에 flush
		
		bw.flush();
		bw.close();
		br.close();
	}
}
