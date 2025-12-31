package src;

import java.io.*;
import java.util.*;

public class SW5521상원이의생일파티2 {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int TC = Integer.parseInt(br.readLine().trim());
		
		for (int tc=1; tc<=TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken()); 
			int M = Integer.parseInt(st.nextToken()); 
			
			List<Integer>[] pan = new ArrayList[N+1];
			
		    for(int i=1; i<=N; i++) {
		    	pan[i] = new ArrayList<>();
		    }
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken()); 
				int e = Integer.parseInt(st.nextToken()); 
				
				pan[s].add(e);
				pan[e].add(s);
			}
			
			boolean[] visited = new boolean[N+1];
			
			int count = 0;
			
			Queue<int[]> q = new LinkedList<>();
			
			q.offer(new int[] {1,0});
			visited[1] = true;
			
			while(!q.isEmpty()) {
				int[] cur = q.poll();
				
				int person = cur[0];
				int depth = cur[1];
				
				if(depth == 1 || depth == 2) {
					count++;
				}
				
				if(depth == 2) continue;
				
				for(int next : pan[person]) {
					if(!visited[next]) {
						visited[next] = true;
						q.offer(new int[] {next, depth+1});
					}
				}
			}
			bw.write("#" + tc + " " + count);
			bw.write('\n');
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
