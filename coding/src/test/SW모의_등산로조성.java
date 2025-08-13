package test;

import java.util.*;
import java.io.*;

public class SW모의_등산로조성 {
	
	static class Node {
		int i;
		int j;
		int height;
		
		Node(int i, int j, int height) {
			this.i = i;
			this.j = j;
			this.height = height;
		}
	}
	
	static int[][] pan;
	static List<Node> start; // 시작점
	static int N; // 지도의 한 변의 길이
	static int K; // 최대 공사 가능 깊이  (1 ≤ K ≤ 5)

	/**
	 * 현재 노드보다 낮은 지형으로, 가로 or 세로로. 
	 * 가장 긴 한 곳
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int tc=1; tc<=T; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			
			K = Integer.parseInt(st.nextToken());
			
			start = new ArrayList<>();
			
			start.add(new Node(-1, -1, Integer.MIN_VALUE));
			
			pan = new int[N][N];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					pan[i][j] = Integer.parseInt(st.nextToken());
					
					if(pan[i][j] >= start.get(0).height) {
						if(pan[i][j] == start.get(0).height) {
							start.add(new Node(i,j,pan[i][j]));
						} else {
							start = new ArrayList<>();
							start.add(new Node(i,j,pan[i][j]));
						}
					}
				}
			}
			
		}
	}

}
