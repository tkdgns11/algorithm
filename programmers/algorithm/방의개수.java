package algorithm;

import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/49190
 *
 * 좌표*2
 */
public class 방의개수 {

	static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
	static int[] dy = {1, 1, 0, -1, -1, -1, 0, 1};
	
	public int solution(int[] arrows) {
		
		int answer = 0;
		
		Set<Long> visitedPoint = new HashSet<>();
		Set<String> visitedEdge = new HashSet<>();
		
		long x = 0, y = 0;
		visitedPoint.add(key(x, y));
		
		for(int arrow : arrows) {
			
			// 한 방향당 2칸씩 이동
			for(int step=0; step<2; step++) {
				long nx = x + dx[arrow];
				long ny = y + dy[arrow];
				
				// 양방향 기록
				String edge1 = key(x,y) + "->" + key(nx, ny);
				String edge2 = key(nx,ny) + "->" + key(x, y);
				
				// 이동 중 '이미 방문한 점'에 '새 간선'으로 도착하면 방 하나 완성.
				if(!visitedEdge.contains(edge1)) {
					if(visitedPoint.contains(key(nx,ny))) {
						answer ++;
					}
					visitedPoint.add(key(nx, ny));
					visitedEdge.add(edge1);
					visitedEdge.add(edge2);
				}
				x = nx;
				y = ny;
			}
		}
		return answer;
	}
	
	// 좌표를 하나의 long 키로 만들기
	static long key(long x, long y) {
		return x * 1000000L + y;
	}
}
