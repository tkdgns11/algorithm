package algorithm;

import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42898
 * 
 * 내부를 칠할때 변하고 겹치므로 *2 하는게 포인트. 
 */
public class 아이템줍기 {
	
	// 동서남북
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	static int[][] map = new int[102][102];
	
	public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
		
		// 모든 사각형의 변을 1로 
		for(int[] rect : rectangle) {
			int x1 = rect[0] * 2; // 좌측 하단 x
			int y1 = rect[1] * 2; // 좌측 하단 y
			int x2 = rect[2] * 2; // 우측 상단 x 
			int y2 = rect[3] * 2; // 우측 상단 y
			
			for(int x = x1; x<=x2; x++) {
				map[x][y1] = 1; // 아래변
				map[x][y2] = 1; // 윗변
			}
			
			for(int y = y1; y<=y2; y++) {
				map[x1][y] = 1; // 왼쪽 변
				map[x2][y] = 1; // 오른쪽 변
			}
		}
		
		// 내부를 -1로 덮기
		for(int[] rect: rectangle) {
			int x1 = rect[0] * 2; // 좌측 하단 x
			int y1 = rect[1] * 2; // 좌측 하단 y
			int x2 = rect[2] * 2; // 우측 상단 x 
			int y2 = rect[3] * 2; // 우측 상단 y
			
			for(int x = x1+1; x<x2; x++) {
				for(int y = y1+1; y<y2; y++) {
					map[x][y] = -1; 
				}
			}
		}
		
		int dist = bfs(characterX*2, characterY*2, itemX*2, itemY*2);
		
		return dist/2;
	}
		
		static int bfs(int sx, int sy, int ex, int ey) {
			
			boolean[][] visited = new boolean[102][102];
			int[][] dist = new int[102][102];
			
			Queue<int[]> q = new LinkedList<>();
			q.add(new int[] {sx, sy});
			visited[sx][sy] = true;
			dist[sx][sy] = 0;
			
			while(!q.isEmpty()) {
				int[] cur = q.poll();
				int x = cur[0], y = cur[1];
				
				if(x == ex && y == ey) return dist[x][y];
				
				for(int i = 0; i<4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					
					if(nx < 0 || ny < 0 || nx > 100 | ny > 100) continue;
					if(map[nx][ny] != 1) continue; // 테두리 아닐시 제외
					if(visited[nx][ny]) continue;
					
					visited[nx][ny] = true;
					dist[nx][ny] = dist[x][y] +1;
					q.add(new int[] {nx, ny});
				}
			}
			
			return -1;
		}
}
