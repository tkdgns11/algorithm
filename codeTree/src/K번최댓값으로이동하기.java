package src;

import java.util.Scanner;

public class K번최댓값으로이동하기 {
	
	static int n,k;
	static int[][] grid;
	static int minI, minJ;
	static boolean[][] visited;
	
	static final int[] dx = {0, 0, -1, 1};
	static final int[] dy = {-1, 1, 0, 0};

	static int maxValue;

	static void dfs(int i, int j, int base) {
		int cur = grid[i][j];

		if (cur < base) {
			if (cur > maxValue || (cur == maxValue && (i < minI || (i == minI && j < minJ)))) {
				maxValue = cur;
				minI = i;
				minJ = j;
			}
		}

		for (int d = 0; d < 4; d++) {
			int nj = j + dx[d];
			int ni = i + dy[d];
			if (nj >= 0 && nj < n && ni >= 0 && ni < n) {
				if (!visited[ni][nj] && grid[ni][nj] < base) {
					visited[ni][nj] = true;
					dfs(ni, nj, base);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		k = sc.nextInt();
		grid = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				grid[i][j] = sc.nextInt();
		int r = sc.nextInt();
		int c = sc.nextInt();

		minI = r - 1;
		minJ = c - 1;

		for (int step = 0; step < k; step++) {
			visited = new boolean[n][n];
			visited[minI][minJ] = true;

			int curI = minI, curJ = minJ;
			int base = grid[curI][curJ];

			maxValue = -1;
			minI = Integer.MAX_VALUE;
			minJ = Integer.MAX_VALUE;

			dfs(curI, curJ, base);

			if (maxValue == -1) {
				minI = curI;
				minJ = curJ;
				break;
			}
		}

		System.out.println((minI + 1) + " " + (minJ + 1));
	}
}
