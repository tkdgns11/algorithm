package src;

import java.util.Scanner;

public class K번최댓값으로이동하기 {
	static int n,k;
	static int[][] grid;
	static int minI, minJ;
	static boolean[][] visited;
	
	// 상 하 좌 우
	static final int[] dx = {0, 0, -1, 1};
	static final int[] dy = {-1, 1, 0, 0};
	static boolean canMove;
	static int maxValue;
	
	static void dfs(int startI, int startJ, int value) {
		int gridValue = grid[minI][minJ];
		
		if(gridValue > maxValue) {
			maxValue = gridValue;
			minI = startI;
			minJ = startJ;
		} else if(gridValue == maxValue) {
			if(gridValue > maxValue) {
				if(minI > startI) {
					minI = startI;
					minJ = startJ;
					maxValue = grid[minI][minJ];
				} else if(minI == startI && minJ > startJ) {
					minJ = startJ;
					maxValue = grid[minI][minJ];
				}
			}
		}
		
		for(int i=0; i<4; i++) {
			int nx = startJ + dx[i];
			int ny = startI + dy[i];
			if(nx >=0 && nx < n && ny >= 0 && ny < n) {
				if(grid[ny][nx] < value) {
					visited[ny][nx] = true;
					dfs(ny, nx, value);
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
        // Please write your code here.
        canMove = false;
        minI = r-1;
        minJ = c-1;
        for(int i=0; i<k; i++) {
        	visited = new boolean[n][n];
        	visited[minI][minJ] = true;
        	dfs(minI, minJ, grid[minI][minJ]);
        }
        System.out.println(minI + " " + minJ);
    }
}