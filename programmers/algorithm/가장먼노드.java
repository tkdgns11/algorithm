package algorithm;

import java.util.*;

public class 가장먼노드 {
	
	public int solution(int n, int[][] edge) {
		
		List<Integer>[] graph = new List[n+1];
		
		for(int i=1; i<=n; i++) graph[i] = new ArrayList<>();
		for(int[] e : edge) {
			graph[e[0]].add(e[1]);
			graph[e[1]].add(e[0]);
		}
		
		int[] dist = new int[n+1];
		Arrays.fill(dist, -1);
		dist[1] = 0;
		
		Queue<Integer> q = new LinkedList<>();
		q.add(1);
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int next : graph[cur]) {
				if(dist[next] == -1) { // 첫방문 노드만
					dist[next] = dist[cur] + 1;
					q.add(next);
				}
			}
		}
		
		// 최대 거리 찾기
		int maxDist = 0;
		for(int i=1; i<=n; i++) maxDist = Math.max(maxDist, dist[i]); 
		
		// 최대 거리의 노드 찾기
		int count = 0;
		for(int i=1; i<=n; i++) if(dist[i] == maxDist) count++;
		
		return count;
	}
}
