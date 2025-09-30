package src;

import java.util.Scanner;

public class 네방향탈출가능여부판별하기 {
	// 상 하 좌 우
	static final int[] dx = {0, 0, -1, 1};
	static final int[] dy = {-1, 1, 0, 0};
	static int[][] grid;
	static int n,m;
	static boolean find;
	
	static void dfs(int startI, int startJ) {
		if(startI == n-1 && startJ == m-1) {
			find = true;
			return;
		}
		
		if(find) return;
			
		for(int i=0; i<4; i++) {
			int nx = startJ + dx[i];
			int ny = startI + dy[i];
			if(nx >=0 && nx < m && ny >= 0 && ny < n) {
				if(grid[ny][nx] == 1) {
					grid[ny][nx] = 0;
					dfs(ny, nx);
				}
			}
		}
	}
	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        grid = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                grid[i][j] = sc.nextInt();
        // Please write your code here.
        
        // 뱀이 없는 경우 1, 있는경우 0
        find = false;
        dfs(0, 0);
        System.out.println(find ? 1 : 0);
    }
}