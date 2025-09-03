package src;

import java.io.*;
import java.util.*;

public class SW벌꿀채취연습 {
	
	static int N;
	static int M;
	static int C;
	
	static int[] choiceA;
	static int[] choiceB;
	
	static int[][] pan;
	
	static int dfs(int amount, int depth, int sum, int[] arr) {
		if(amount>C) return -1;
		
		if(depth == M) return sum;
		
		// 현재칸을 고르는 경우
		int ret1 = dfs(amount + arr[depth], depth+1, sum + (arr[depth] * arr[depth]), arr); 
		// 현재칸을 고르지 않는 경우
		int res2 = dfs(amount, depth+1, sum, arr); 		
		
		// 두 경우 중 최댓값
		return Math.max(ret1, res2);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			choiceA = new int[M];
			choiceB = new int[M];
			
			pan = new int[N][N];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					pan[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int result = -1;
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<= N-M; j++) {
					System.arraycopy(pan[i], j, choiceA, 0, M);
					int maxA = dfs(0, 0, 0, choiceA);
					
					int maxB = -1;
					// 옆에 오는 경우
					for(int jj= j+M; jj<=N-M; jj++ ) {
						System.arraycopy(pan[i], jj, choiceB, 0, M);
						maxB = Math.max(maxB, dfs(0, 0, 0, choiceB));
					}
					
					// 아래쪽 전체 탐색
					for(int ii=i+1; ii<N; ii++) {
						for(int jj=0; jj<= N-M; jj++) {
							System.arraycopy(pan[ii], jj, choiceB, 0, M);
							maxB = Math.max(maxB, dfs(0, 0, 0, choiceB));
						}
					}
					result = Math.max(result, maxA + maxB);
				}
			}
			bw.write("#" + tc + " " + result+ "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
