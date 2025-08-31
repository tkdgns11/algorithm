package test;

import java.util.*;
import java.io.*;

public class SW1954달팽이숫자 {

	// 우 하 좌 상
	static final int[] dx = new int[] {1, 0, -1, 0};
	static final int[] dy = new int[] {0, 1, 0, -1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int tc=1; tc<= T; tc++) {
			int N = Integer.parseInt(br.readLine().trim());
		
			int[][] pan = new int[N][N];
			
			int cur = 0;
			int cx = 0;
			int cy = 0;
			
			for(int i=1; i<= N*N; i++) {
				pan[cy][cx] = i;
				
				int nx = cx + dx[cur];
				int ny = cy + dy[cur];
				
				if(nx < 0 || nx == N || ny < 0 || ny == N || pan[ny][nx] != 0) {
					cur = (cur + 1) % 4;
					nx = cx + dx[cur];
					ny = cy + dy[cur];
				}
				cx = nx;
				cy = ny;
			}
			
			bw.write("#" + tc + "\n");
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					bw.write(pan[i][j] + " ");
				}
				bw.write("\n");
			}
		}
		
		bw.flush();
		bw.close();
		br.close();
	}

}
