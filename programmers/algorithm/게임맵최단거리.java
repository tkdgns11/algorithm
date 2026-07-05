package algorithm;

import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/1844
 */
public class 게임맵최단거리 {
	
	// 동 서 남 북
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	public int solution(int[][] maps ) {
		
		int n = maps.length;
		int m = maps[0].length;
		
		boolean[][] visited = new boolean[n][m];
		int[][] dist = new int[n][m]; 
		
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[]{0,0});
		visited[0][0] = true;
		dist[0][0] = 1;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0], y = cur[1];
			
			if(x == n-1 && y == m-1) return dist[x][y];
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
				if(maps[nx][ny] == 0) continue;
				if(visited[nx][ny]) continue;
				
				visited[nx][ny] = true;
				dist[nx][ny] = dist[x][y] + 1; 
				q.add(new int[] {nx,ny});
			}
		}
		return -1;
	}
}
