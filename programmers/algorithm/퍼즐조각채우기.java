package algorithm;

import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/84021
 */
public class 퍼즐조각채우기 {
	
	static int n;
	static int[] dy = {0, 0, 1, -1};
	static int[] dx = {1, -1, 0, 0};
	
	public int solution(int[][] game_board, int[][] table) {
		
		n = game_board.length;
		
		List<List<int[]>> blanks = getGroups(game_board, 0);
		List<List<int[]>> pieces = getGroups(table, 1);
		
		boolean[] usedPiece = new boolean[pieces.size()];
		int answer = 0;
		
		// 빈칸마다 안쓴 조각 회전해서 맞는거 채우기
		for(List<int[]> blank : blanks) {
			List<int[]> blankNorm = normalize(blank);
			
			for(int i=0; i<pieces.size(); i++) {
				if(usedPiece[i]) continue;
				
				if(match(blankNorm, pieces.get(i))) {
					usedPiece[i] = true;
					answer += blank.size();
					break;
				}
			}
		}
		return answer;
	}
	
	static List<List<int[]>> getGroups(int[][] board, int target) {
		
		List<List<int[]>> groups = new ArrayList<>();
		boolean[][] visited = new boolean[n][n];
		
		for(int y=0; y<n; y++) {
			for(int x=0; x<n; x++) {
				if(board[y][x] == target && !visited[y][x]) groups.add(bfs(board, visited, y, x, target));
			}
		}
		return groups;
	}
	
	static List<int[]> bfs(int[][] board, boolean[][] visited, int sy, int sx, int target) {
		
		List<int[]> cells = new ArrayList<>();
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {sy, sx});
		visited[sy][sx] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			cells.add(cur);
			
			for(int d=0; d<4; d++) {
				int ny = cur[0] + dy[d]; 
				int nx = cur[1] + dx[d];
				if(ny<0 || nx<0 || ny>=n || nx>=n || visited[ny][nx] || board[ny][nx] != target) continue;
				visited[ny][nx] = true;
				q.add(new int[] {ny, nx});
			}
		}
		return cells;
	}
	
	static boolean match(List<int[]> blankNorm, List<int[]> piece) {
		
		List<int[]> cur = piece;
		for(int rot=0; rot<4; rot++) {
			if(same(blankNorm, normalize(cur))) return true;
			cur = rotate(cur);
		}
		return false;
	}
	
	// 90도 회전
	static List<int[]> rotate(List<int[]> cells) {
		
		List<int[]> res = new ArrayList<>();
		for(int[] p : cells) res.add(new int[] {p[1], -p[0]});
		return res;
	}
	
	static List<int[]> normalize(List<int[]> cells){
		
		int minY = Integer.MAX_VALUE, minX = Integer.MAX_VALUE;
		for(int[] p : cells) {
			minY = Math.min(minY, p[0]);
			minX = Math.min(minX, p[1]);
		}
		
		List<int[]> res = new ArrayList<>();
		for(int[] p : cells) res.add(new int[] {p[0]-minY, p[1]-minX});
		
		// 세로 -> 가로
		res.sort((a,b) -> a[0] != b[0] ? a[0]-b[0] : a[1]-b[1]);
		return res;
	}
	
	static boolean same(List<int[]> a, List<int[]> b) {
		
		if(a.size() != b.size()) return false;
		for(int i=0; i<a.size(); i++) 
			if(a.get(i)[0] != b.get(i)[0] || a.get(i)[1] != b.get(i)[1]) return false;
		return true;
	}
}
