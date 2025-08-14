package test;

import java.util.*;
import java.io.*;

public class Test_정사각형방 {
	// 상 하 좌 우
	static final int[] dx = {0, 0, -1, 1};
	static final int[] dy = {-1, 1, 0, 0};
	static int N;
	static int[][] pan;
	static int count;
	static int bang;
	
	static void dfs(int depth, int curI, int curJ, int initI, int initJ) {
		if(count < depth) {
			count = depth;
			bang = pan[initI][initJ];
		} else if(count == depth) {
			bang = Math.min(bang, pan[initI][initJ]);
		}
		
		for(int i=0; i<4; i++) {
			int nx = curJ + dx[i];
			int ny = curI + dy[i];
			
			if(nx >= 0 && nx < N && ny >= 0 && ny < N && pan[ny][nx] == pan[curI][curJ] + 1) {
				dfs(depth+1, ny, nx, initI, initJ);
			}
		}
	}
	
	// 이동하려는 방에 적힌 숫자가 현재 방에 적힌 숫자보다 정확히 1 더 커야 한다.
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc<= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			
			pan = new int[N][N];
			
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					pan[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			count = Integer.MIN_VALUE;
			bang = Integer.MAX_VALUE;
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					dfs(1, i, j, i, j);
				}
			}
			bw.write("#" + tc + " " + bang + " " + count);
			bw.write("\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
