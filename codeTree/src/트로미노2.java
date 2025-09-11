package src;

import java.util.Scanner;

/**
 * 내 풀이. 일반화 시킴.
 */
public class 트로미노2 {

    // 상 하 좌 우
    static final int[] dx = {0, 0, -1, 1};
    static final int[] dy = {-1, 1, 0, 0};
    static int n,m;
    static int result = Integer.MIN_VALUE;
    static boolean[][] visited;
    static int[][] grid;

    static void dfs(int depth, int startI, int startJ, int sum) {
        if(depth == 2) {
            result = Math.max(result, sum);
            return;
        }

        for(int i=0; i<4; i++) {
            int nx = startJ + dx[i];
            int ny = startI + dy[i];

            if(nx>=0 && nx<m && ny>=0 && ny<n && !visited[ny][nx]) {
                visited[ny][nx] = true;
                dfs(depth+1, ny, nx, sum + grid[ny][nx]);
                visited[ny][nx] = false;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        visited = new boolean[n][m];
        grid = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] = sc.nextInt();
            }
        }
        // Please write your code here.

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                visited[i][j] = true;
                dfs(0, i, j, grid[i][j]);
                visited[i][j] = false;
            }
        }
        System.out.println(result);
    }
}