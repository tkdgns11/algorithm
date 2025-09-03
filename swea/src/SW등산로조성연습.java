package src;

import java.io.*;
import java.util.*;

/**
 * 방문여부 체크 어디서? -> 어차피 재방문할일 없음. 중복 안될때 visited
 */
public class SW등산로조성연습 {
	// 상 하 좌 우
	static final int[] dx = {0, 0, -1, 1};
	static final int[] dy = {-1, 1, 0, 0};
	static int N;
	static int K;
	static int Max;
	static int[][] pan;
	static boolean[][] visited;
	static int MaxCount;
	
	static void dfs(int depth, int i, int j, boolean used) {
		MaxCount = Math.max(MaxCount, depth);
		
		for(int ii=0; ii<4; ii++) {
			int nx = j + dx[ii];
			int ny = i + dy[ii];
			
			if(nx >= 0 && ny >=0 && nx < N && ny < N) {
				// 나보다 작으면 그냥 이동 가능. 
				
				if (visited[ny][nx]) continue;    

				if(pan[ny][nx] < pan[i][j]) {
					visited[ny][nx] = true;
					dfs(depth+1, ny, nx, used); 
					visited[ny][nx] = false;
				} else {
					// 나보다 크거나 같으면 공사가능하면 이동 
					if(K > pan[ny][nx] - pan[i][j] && !used) {
						int tmp = pan[ny][nx];
						pan[ny][nx] = pan[i][j] - 1;
						visited[ny][nx] = true;
						dfs(depth+1, ny, nx, true);
						visited[ny][nx] = false;
						pan[ny][nx] = tmp;
					}
				}
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine().trim());
		
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			pan = new int[N][N];
			
			Max = Integer.MIN_VALUE;
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					pan[i][j] = Integer.parseInt(st.nextToken());
					Max = Math.max(Max, pan[i][j]);
				}
			}
			
			MaxCount = 0;
			visited = new boolean[N][N];
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(pan[i][j] == Max) {
						visited[i][j] = true;
						dfs(1, i, j, false);
						visited[i][j] = false;
					}
				}
			}
			bw.write("#" + tc + " " + MaxCount + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
