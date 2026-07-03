package algorithm;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/43162
 */
class 네트워크 {

	static int n;
	static int[][] computers;
	static boolean[] visited;

	public int solution(int n, int[][] computers) {

		this.n = n;
		this.computers = computers;
		visited = new boolean[n];

		int answer = 0;

		for(int i=0; i<n; i++) {
			if(!visited[i]) {   // 아직 어느 네트워크에도 안 묶인 컴퓨터
				dfs(i);         // 이 컴퓨터가 속한 네트워크 전체 방문
				answer++;       // 네트워크 1개 발견
			}
		}

		return answer;
	}

	// i번 컴퓨터에서 연결된 모든 컴퓨터를 방문 처리
	static void dfs(int i) {

		visited[i] = true;

		for(int j=0; j<n; j++) {
			// j와 연결(==1)돼 있고, 아직 방문 안 했으면 타고 들어감
			if(computers[i][j] == 1 && !visited[j]) {
				dfs(j);
			}
		}
	}
}