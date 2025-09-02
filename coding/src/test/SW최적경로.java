package test;

import java.util.*;
import java.io.*;

/**
 * 회사 -> 고객들 2 ~ 10 -> 집
 */

public class SW최적경로 {
	static int N;
	static List<int[]> list; // 좌표
	static int[][] arr; // 거리
	static boolean[] visited;
	static int result;
	
	static void dfs(int depth, int dist, int currentNode) {
		if(depth == N) {
			result = Math.min(result, dist);
			return;
		}
		
		if(dist > result) return; 
		
		for(int i=0; i<N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				if(depth+1 == N) {
					dfs(depth+1, dist + arr[currentNode+2][i+2] + arr[i+2][1], i);
				} else {
					dfs(depth+1, dist + arr[currentNode+2][i+2], i);
				}
				visited[i] = false;
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc<= T; tc++) {
			N = Integer.parseInt(br.readLine().trim()); // 고객의 수
			visited = new boolean[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());

			list = new ArrayList<>();
			
			for(int i=0; i < N + 2; i++) {
				int row = Integer.parseInt(st.nextToken());
				int col = Integer.parseInt(st.nextToken());
				
				list.add(new int[] {row, col});
			}
			
			arr = new int[N+2][N+2]; // 0 ~ N+1
			
			/**
			 * list, arr의 0, 1 인덱스 -> 회사, 집 
			 */
			for(int i=0; i < N + 1; i++) {
				int[] node1 = list.get(i);
				for(int j=i+1; j < N + 2; j++) {
					int[] node2 = list.get(j);
					int dist = Math.abs(node1[0] - node2[0]) + Math.abs(node1[1] - node2[1]);
					arr[i][j] = dist;
					arr[j][i] = dist;
				}
			}
			
			result = Integer.MAX_VALUE;
			
			for(int i=0; i<N; i++) {
				visited[i] = true;
				dfs(1, arr[0][i+2], i); 
				visited[i] = false;
			}
			
			bw.write("#" + tc + " " + result + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
