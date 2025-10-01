package src;

import java.util.Arrays;
import java.util.Scanner;

public class 가장오래걸리는학생2 {
	
	static int n,m;
	static int[][] pan;
	static int result;
	static boolean[] visited;
	static int[] values;
	
	static void dfs(int cur, int time) {
		if(cur == n-1) {
			result = Math.min(result, time);
			return;
		}
		
		for(int i=0; i<n; i++) {
			if(!visited[i] && pan[cur][i] !=0) {
				visited[i] = true;
				dfs(i, time + pan[cur][i]);
				visited[i] = false;
			}
		}
	}
	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        pan = new int[n][n];
        
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt()-1;
            int b = sc.nextInt()-1;
            int d = sc.nextInt();
            pan[a][b] = d;
        }
        // Please write your code here.
        values = new int[n];
        visited = new boolean[n];
        for(int i=0; i<n; i++) {
        	result = Integer.MAX_VALUE;
        	visited[i] = true;
        	dfs(i, 0);
        	values[i] = result;
        	visited[i] = false;
        }
        Arrays.sort(values);
        System.out.println(values[n-1]);
    }
}
