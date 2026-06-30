package algorithm;

import java.util.*;


// https://school.programmers.co.kr/learn/courses/30/lessons/468373?language=java
class 바이러스파이프 {

	static int n, k;                 // n: 노드 수, k: 최대 행동(파이프 여닫기) 횟수
	static List<int[]>[] adj;        // 인접리스트. adj[u] = {이웃노드, 파이프타입} 들의 리스트
	static int best;                 // 지금까지 찾은 최대 감염 수

	public int solution(int N, int infection, int[][] edges, int K) {
		//edges[i]는 [x, y, type]의 형태로 x번 노드의 배양체와 y번 노드의 배양체 사이가 type 종류의 파이프로 연결되어 있음을 의미합니다.

		n = N;
		k = K;

		// 타입까지 담은 인접리스트
		adj = new List[n+1];                                 // 1~n번 노드 → 인덱스 1~n 사용
		for(int i=1; i<=n; i++) adj[i] = new ArrayList<>();  // 각 노드 리스트 초기화
		for(int[] e : edges) {                               // e = {x, y, type}
			adj[e[0]].add(new int[]{e[1], e[2]});            // x 쪽에 (y, type) 등록
			adj[e[1]].add(new int[]{e[0], e[2]});            // y 쪽에 (x, type) 등록
		}

		boolean[] infected = new boolean[n+1];   // infected[i] = i번 노드가 감염됐는지
		infected[infection] = true;              // 최초 감염 노드 표시

		best = 1;                    // 아무것도 안 열어도 최초 1개는 감염 상태
		dfs(0, 0, infected);         // depth=0(아직 0번 행동), last=0(직전 타입 없음), 시작 감염상태

		return best;                 // 모든 순서 다 본 뒤의 최댓값
	}

	// depth : 지금까지 연 횟수,  last : 직전 타입(연속 중복 컷)
	static void dfs(int depth, int last, boolean[] infected) {

		best = Math.max(best, count(infected));  // k번 다 안 써도 멈출 수 있으니, 매 단계에서 갱신
		if(depth == k) return;                   // 행동 예산 다 씀 → 더 못 열고 종료

		for(int type=1; type<=3; type++) {       // 다음에 열 파이프 타입 고르기 (A/B/C)
			if(type == last) continue;           // 직전과 같은 타입은 더 번질 게 없음 → 건너뜀
			dfs(depth+1, type, spread(infected, type));  // type 열어 번진 결과로 한 단계 깊이 ↓
		}
	}

	// type 파이프 전부 열었을 때의 결과 (같은 타입 연결요소 통째로 감염)
	static boolean[] spread(boolean[] infected, int type) {

		boolean[] res = infected.clone();   // 원본 안 건드리게 복사본에 작업. 깊은복사.

		Queue<Integer> q = new LinkedList<>();              // BFS 큐
		for(int i=1; i<=n; i++) if(res[i]) q.add(i);        // 현재 감염된 노드 전부를 시작점으로

		while(!q.isEmpty()) {
			int u = q.poll();
			for(int[] e : adj[u]) {                         // u의 모든 인접 간선 확인
				if(e[1] == type && !res[e[0]]) {            // 이 타입 파이프 + 아직 감염 안 된 이웃이면
					res[e[0]] = true;                       // 감염시키고
					q.add(e[0]);                            // 그 노드에서 또 번질 수 있으니 큐에 추가 (체인 전파)
				}
			}
		}

		return res;                  // 이번에 type 열었을 때의 새 감염상태
	}

	static int count(boolean[] infected) {

		int cnt = 0;
		for(int i=1; i<=n; i++) if(infected[i]) cnt++;   // 감염된 노드 개수 세기
		return cnt;
	}
}