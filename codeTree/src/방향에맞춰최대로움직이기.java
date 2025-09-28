package src;

import java.util.Scanner;

public class 방향에맞춰최대로움직이기 {
	static int n;
	static final int[] dx = {0, 0, 1, 1, 1, 0, -1, -1, -1};
	static final int[] dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};
	static int [][] num, moveDir;
	static int maxDepth;
	
	static void dfs(int i, int j, int depth) {
		maxDepth = Math.max(depth, maxDepth);
		
		int mValue = moveDir[i][j];
		
		int nx, ny;
		for(int ii=1; ii<n; ii++) {
			nx = j + dx[mValue] * ii;
			ny = i + dy[mValue] * ii;
			
			if(inRange(ny,nx)) {
				if(num[ny][nx] > num[i][j]) {
					dfs(ny, nx, depth+1);
				}
			} else break;
		}
	}
	
	static boolean inRange(int i, int j) {
		return i>=0 && i<n && j>=0 && j<n;
	}
	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        num = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                num[i][j] = sc.nextInt();
            }
        }
        
        moveDir = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                moveDir[i][j] = sc.nextInt();
            }
        }
        
        int r = sc.nextInt();
        int c = sc.nextInt();
        // Please write your code here.
        maxDepth = Integer.MIN_VALUE;
        dfs(r-1, c-1, 0);
        System.out.println(maxDepth);
    }
}