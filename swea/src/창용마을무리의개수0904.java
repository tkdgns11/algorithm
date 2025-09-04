package src;

import java.io.*;
import java.util.*;

public class 창용마을무리의개수0904 {
	static int N;
	static int M;
	static int[][] pan;
	static boolean[] visited;
	
	static void dfs(int node) {
		for(int i=1; i<=N; i++) {
			int nextNode = pan[node][i];
			if(nextNode == 1 && !visited[i]) {
				visited[i] = true;
				dfs(i);
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			pan = new int[N+1][N+1];
			visited = new boolean[N+1];
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int num1 = Integer.parseInt(st.nextToken());
				int num2 = Integer.parseInt(st.nextToken());
				
				pan[num1][num2] = 1;
				pan[num2][num1] = 1;
			}
			
			int count = 0;
			
			for(int i=1; i<=N; i++) {
				if(!visited[i]) {
					count++;
					visited[i] = true;
					dfs(i);
				}
			}
			bw.write("#" + tc + " " + count + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}

}
