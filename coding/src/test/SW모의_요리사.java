package test;

import java.util.*;
import java.io.*;

public class SW모의_요리사 {
	static int N;
	static int[][] pan;
	static int synerge;
	
	static void check(List<Integer> left, List<Integer> right) {
		int leftSynerge = 0;
		int rightSynerge = 0;
		
		for(int i=0; i<left.size(); i++) {
			for(int j=i+1; j<left.size(); j++) {
				if(i != j) {
					leftSynerge += pan[left.get(i)][left.get(j)];
					leftSynerge += pan[left.get(j)][left.get(i)];
				}
			}
		}
		
		for(int i=0; i<right.size(); i++) {
			for(int j=0; j<right.size(); j++) {
				if(i != j) {
					rightSynerge += pan[right.get(i)][right.get(j)];
					rightSynerge += pan[right.get(j)][right.get(i)];
				}
			}
		}
		int result = Math.abs(leftSynerge - rightSynerge);
		synerge = Math.min(result, synerge);
	}
	
	static void dfs(int depth, List<Integer> left, List<Integer> right) {
		if(depth == N) {
			if(left.size() == right.size()) {
				check(left, right);
			}
			return;
		}
		
		if(left.size() > N/2 || right.size() > N/2) return;
	
		left.add(depth);
		dfs(depth+1, left, right);
		left.remove(Integer.valueOf(depth));
		
		right.add(depth);
		dfs(depth+1, left, right);
		right.remove(Integer.valueOf(depth));
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			
			pan = new int[N][N];
			
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					pan[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			synerge = Integer.MAX_VALUE;
			dfs(0, new ArrayList<>(), new ArrayList<>());
			
			bw.write("#" + tc + " " + synerge);
			bw.write("\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
