package src;

import java.util.Scanner;

public class 외판원순회 {
	
	static int n;
	static int[][] cost;
	static boolean[] visited;
	static int result;
	
	static void dfs(int node, int count, int acc) {
		if(count == n-1) {
			if(cost[node][0] != 0) {
				result = Math.min(result, acc + cost[node][0]);
			}
			return;
		}
		
		for(int i=1; i<n; i++) {
			if(!visited[i] && cost[node][i] != 0) {
				visited[i] = true;
				dfs(i, count+1, acc+ cost[node][i]);
				visited[i] = false;
			}
		}
	}
	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cost[i][j] = sc.nextInt();
            }
        }
        // Please write your code here.
        
        visited = new boolean[n];
        visited[0] = true;
        result = Integer.MAX_VALUE;
        for(int i=1; i<n; i++) {
        	if(cost[0][i] > 0) {
        		visited[i] = true;
        		dfs(i, 1, cost[0][i]);
        		visited[i] = false;
        	}
        }
        System.out.println(result);
    }
}