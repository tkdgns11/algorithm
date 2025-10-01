package src;

import java.util.Scanner;
public class 수들의합최대화하기 {

    static int n;
    static int[][] grid;
    static boolean[] visited;
    static int result;
    
    static void dfs(int depth, int acc) {
    	if(depth == n) {
    		result = Math.max(acc, result);
    		return;
    	}
    	
    	for(int i=0; i<n; i++) {
    		if(!visited[i]) {
    			visited[i] = true;
    			dfs(depth+1, acc + grid[depth][i]);
    			visited[i] = false;
    		}
    	}
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        grid = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                grid[i][j] = sc.nextInt();
        // Please write your code here.
        result = -1;
        visited = new boolean[n];
        dfs(0, 0);
        System.out.println(result);
    }
}