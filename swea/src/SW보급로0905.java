package src;

import java.io.*;
import java.util.*;

public class SW보급로0905 {
	static int N;
	static int[][] pan;
	// 상 하 좌 우
	static final int[] dx = {0, 0, -1, 1};
	static final int[] dy = {-1, 1, 0, 0};
	static int[][] dist;

	static class Node implements Comparable<Node>{
		int i;
		int j;
		int value;
		
		public Node(int i, int j, int value) {
			this.i = i;
			this.j = j;
			this.value = value;
		}

		@Override
		public int compareTo(Node o) {
			return value - o.value;
		}
	}

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			pan = new int[N][N];
			dist = new int[N][N];
			
			for(int i=0; i<N; i++) {
				String str = br.readLine();
				for(int j=0; j<N; j++) {
					pan[i][j] = Integer.parseInt(String.valueOf(str.charAt(j)));
				}
			}
			
			for(int i=0; i<N; i++) {
				Arrays.fill(dist[i], Integer.MAX_VALUE/2);
			}
			
			PriorityQueue<Node> q = new PriorityQueue<>();
			
			q.add(new Node(0,0,0));
			
			bw.write("#");
			bw.write(Integer.toString(tc));
			bw.write(' ');
			
			while(!q.isEmpty()) {
				Node node = q.poll();
				
				if(node.i == N-1 && node.j == N-1) {
					bw.write(Integer.toString(node.value));
					bw.write('\n');
					break;
				}
				
				for(int i=0; i<4; i++ ) {
					int nx = node.j + dx[i];
					int ny = node.i + dy[i];
							
					if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
					
					if(dist[ny][nx] > node.value + pan[ny][nx]) {
						q.offer(new Node(ny, nx, node.value + pan[ny][nx]));
						dist[ny][nx] = node.value + pan[ny][nx];
					}
				}
			}
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
