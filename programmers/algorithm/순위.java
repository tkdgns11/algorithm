package algorithm;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/49191
 * 플로이드 워셜로 간접 승패 전파
 */
public class 순위 {
	
	public int solution(int n, int[][] results ) {
		
		boolean[][] win = new boolean[n+1][n+1];
		
		for(int[] r: results) win[r[0]][r[1]] = true;
		
		for(int k=1; k<=n; k++) // 경유
			for(int i=1; i<=n; i++)
				for(int j=1; j<=n; j++)
					if(win[i][k] && win[k][j]) win[i][j] = true;
		
		int answer = 0;
		for(int i=1; i<=n; i++) {
			int count = 0;
			for(int j=1; j<=n; j++) {
				if(i==j) continue;
				if(win[i][j] || win[j][i]) count++;
			}
			if(count == n-1) answer++;
		}
		return answer;
	}

}
