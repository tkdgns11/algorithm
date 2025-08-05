package test;

import java.io.*;
import java.util.*;

class Baekjoon17822원판 {

	public static void main(String args[]) throws Exception {
		// 파일 경로 설정 (프로젝트 폴더 기준)
		FileInputStream fileInputStream = new FileInputStream("input.txt");
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
		BufferedReader br = new BufferedReader(inputStreamReader);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// 첫째 줄에 N, M, T이 주어진다.
		int N, M, T;

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 원판의 갯수
		M = Integer.parseInt(st.nextToken()); // 4로 고정. 상하좌우 (x) 여기서 에러
		T = Integer.parseInt(st.nextToken()); // T번 회전

		int[][] pan = new int[N][M]; // 위:0, 우:1, 아:2, 좌:3

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				pan[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// i번째 회전할때 사용하는 변수 xi, di, ki가 주어진다.
		// * 번호가 xi의 배수인 원판을 di방향으로 ki칸 회전시킨다.

		int[][] rotateInfoArr = new int[T][3]; // T번째 회전정보

		for (int i = 0; i < T; i++) {

			int[] rotateInfo = new int[3]; // xi, di, ki

			st = new StringTokenizer(br.readLine());

			rotateInfo[0] = Integer.parseInt(st.nextToken());
			rotateInfo[1] = Integer.parseInt(st.nextToken()); // di가 0인 경우는 시계 방향, 1인 경우는 반시계 방향
			rotateInfo[2] = Integer.parseInt(st.nextToken()); // ki 칸 이동

			rotateInfoArr[i] = rotateInfo;
		}
		
		boolean[][] deletePan = new boolean[N][M];

		// 회전시키기
		for (int i = 0; i < T; i++) { // 횟수
			for (int k = 0; k < N; k++) { // k번째 원판
				if ((k+1) % rotateInfoArr[i][0] == 0) { // xi의 배수인 원판만 이동
					int[] tmpArr = new int[M]; // 회전시킨 판 임시저장
					// int[][] pan = new int[N][N]; // 위:0, 우:1, 아:2, 좌:3

					if (rotateInfoArr[i][1] == 0) { // 시계방향 : 우로 한칸
						for (int j = 0; j < M; j++) {
							int rotatePositon = (j + (rotateInfoArr[i][2] % M) ) % M;
							tmpArr[rotatePositon] = pan[k][j];
						}
						pan[k] = tmpArr;
					} else { // 반시계 방향 : 좌로 한칸
						for (int j = 0; j < M; j++) {
							int rotatePositon = ((j + M) - (rotateInfoArr[i][2]) % M) % M;
							//System.out.println(rotateInfoArr[i][2]);
							tmpArr[rotatePositon] = pan[k][j];
						}
						pan[k] = tmpArr;
					}
				}
			}
			
			// 인접 위치 제거
			deletePan = new boolean[N][M];
			boolean deleteYn = false;
			for (int x = 0; x < N; x++) {
				for (int j = 0; j < M; j++) {
					// 같은 원 안에서 양옆 체크
					// 왼쪽 체크
					if (pan[x][j] > 0 && pan[x][j] == pan[x][((j + M) - 1) % M]) {
						deletePan[x][j] = true;
						deletePan[x][((j + M) - 1) % M] = true;
						deleteYn = true;
					}

					// 오른쪽 체크
					if (pan[x][j] > 0 && pan[x][j] == pan[x][(j + 1) % M]) {
						deletePan[x][j] = true;
						deletePan[x][((j + M) + 1) % M] = true;
						deleteYn = true;
					}

					// 다른 원에서 같은 위치 체크
					// (1) 이전 원과 비교
					if (pan[x][j] > 0 && x != 0 && pan[x][j] == pan[x - 1][j]) {
						deletePan[x][j] = true;
						deletePan[x - 1][j] = true;
						deleteYn = true;
					}

					// (2) 다음 원과 비교
					if (pan[x][j] > 0 && x != N - 1 && pan[x][j] == pan[x + 1][j]) {
						deletePan[x][j] = true;
						deletePan[x + 1][j] = true;
						deleteYn = true;
					}
				}
			}
			
			for (int x = 0; x < N; x++) {
				for (int j = 0; j < M; j++) {
					if(deletePan[x][j]) {
						pan[x][j] = -1;
					}
				}
			}
			
			
			if(!deleteYn) {
				int sum = 0;
				int count = 0;
				
				// sum 구하기
				for (int x = 0; x < N; x++) {
					for (int j = 0; j < M; j++) {
						if (pan[x][j] > 0) {
							sum += pan[x][j];
							count++;
						} 
					}
				}
				
				double avg = count == 0 ? 0 : (double) sum / count;
				
				if(count != 0) {
					for (int x = 0; x < N; x++) {
						for (int j = 0; j < M; j++) {
							if ((pan[x][j] > 0) && avg > (double) pan[x][j]) { // 평균보다 작은 수
								pan[x][j]++;
							} else if((pan[x][j] > 0) && avg < (double) pan[x][j]) { // 평균보다 큰 수
								pan[x][j]--;
							}
						}
					}
				}
			}
		}

		/*
		 * 인접하다 : 같은 원 안에서 양옆칸 + 다른 원에서 같은 위치
		 * 
		 * 원판에 수가 남아 있으면, 인접하면서 수가 같은 것을 모두 찾는다. 그러한 수가 있는 경우에는 원판에서 인접하면서 같은 수를 모두 지운다.
		 * 지울 칸들을 모아놨다가 flush
		 * 
		 * 없는 경우에는 원판에 적힌 수의 평균을 구하고, 평균보다 큰 수에서 1을 빼고, 작은 수에는 1을 더한다. 원판을 T번 회전시킨 후 원판에
		 * 적힌 수의 합을 구해보자.
		 */

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print("" + pan[i][j]);
			}
			System.out.println();
		}
		
		/*
		 * 없는 경우에는 원판에 적힌 수의 평균을 구하고, 평균보다 큰 수에서 1을 빼고, 작은 수에는 1을 더한다. 원판을 T번 회전시킨 후 원판에
		 * 적힌 수의 합을 구해보자.
		 * 
		 */
		
		int result = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(pan[i][j] > 0) {
					result += pan[i][j];
				}
			}
		}

		bw.write("" + result);

		bw.flush();
		bw.close();
		br.close();
	}
}
