package src;

import java.util.*;
import java.io.*;

public class SW동철이의일분배 {
	
	static int N;
	static int[][] pan;
	static double max;
	static int[] numbers;
	static boolean[] visited;
	
	static void dfs(int depth, double value) {
		if(depth == N) {
			max = Math.max(max, value);
			return;
		}
		
		// 곱할 수록 작아진다, 0이 있는 경우 return
		if(max > value) return;
		
		if(value == 0) return;
		
		for(int i=0; i<N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				numbers[depth] = i;
				dfs(depth+1, value * (pan[depth][i]) / 100.0);
				visited[i] = false;
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			
			pan = new int[N][N]; // % 단위
			numbers = new int[N];
			visited = new boolean[N];
			 
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					pan[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			max = -1;
			dfs(0, 1.0);
			max *= 100;
			String strResult = String.format("%.6f", max);
			bw.write("#" + tc + " " + strResult + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
