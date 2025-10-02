package src;

import java.util.*;
import java.io.*;

public class 최소경로로탈출하기 {
	
	static int n,m;
	static int[][] a;
	
	// 상 하 좌 우
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	
	static class Node {
		int i;
		int j;
		int cost;
		public int getI() {
			return i;
		}
		public void setI(int i) {
			this.i = i;
		}
		public int getJ() {
			return j;
		}
		public void setJ(int j) {
			this.j = j;
		}
		public int getCost() {
			return cost;
		}
		public void setCost(int cost) {
			this.cost = cost;
		}
		
		public Node() {}
		
		public Node(int i, int j, int cost) {
			super();
			this.i = i;
			this.j = j;
			this.cost = cost;
		}
		
	}
	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        a = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                a[i][j] = sc.nextInt();
        // Please write your code here.
        
        Queue<Node> q = new LinkedList<>();
        
        q.add(new Node(0, 0, 0));
        
        int result = -1;
        
        while(!q.isEmpty()) {
        	Node cur = q.poll();
        	
        	if(cur.i == n-1 && cur.j == m-1) {
        		result = cur.cost;
        		break;
        	}
        	
        	for(int i=0; i<4; i++) {
        		int ni = cur.i + dy[i];
        		int nj = cur.j + dx[i];
        		
        		if(ni >= 0 && ni < n && nj >= 0 && nj < m && a[ni][nj] == 1) {
        			a[ni][nj] = -1;
        			q.offer(new Node(ni, nj, cur.cost+1));
        		}
        	}
        }
        System.out.println(result);
    }
}