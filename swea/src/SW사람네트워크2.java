package src;

import java.io.*;
import java.util.*;

public class SW사람네트워크2 {
	
	static int N;
	static int[][] pan;
	static int[] cc;
	static int[][] dist;
	static boolean[] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			
			pan = new int[N][N];
			dist = new int[N][N];
			
			for(int i=0; i<N; i++) {
				Arrays.fill(dist[i], Integer.MAX_VALUE);
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					pan[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0; i<N; i++) {
				
				visited = new boolean[N];
				
				Queue<int[]> q = new LinkedList<>();
				
				q.add(new int[] {i, 0});
				
				for(int j=0; j<N; j++) {
					if(i != j && dist[i][j] != Integer.MAX_VALUE) {
						visited[j] = true;
						q.add(new int[] {j, dist[i][j]});
					}
				}
				
				while(!q.isEmpty()) {
					int[] node = q.poll();
					
					for(int ii=0; ii<N; ii++) {
						if(i!=ii && !visited[ii] && pan[node[0]][ii] == 1 && dist[i][ii] == Integer.MAX_VALUE) {
							visited[ii] = true;
							dist[i][ii] = node[1] + 1;
							q.offer(new int[] {ii, node[1] + 1});
						}
					}
				}
			}
			
			cc = new int[N];
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(i!=j) {
						cc[i] += dist[i][j];
					}
				}
			}
			
			Arrays.sort(cc);
			
			bw.write('#');
			bw.write(Integer.toString(tc));
			bw.write(' ');
			bw.write(Integer.toString(cc[0]));
			bw.write('\n');
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
