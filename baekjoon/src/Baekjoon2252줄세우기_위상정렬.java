package src;

import java.io.*;
import java.util.*;

public class Baekjoon2252줄세우기_위상정렬 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<Integer>[] pan = new ArrayList[N + 1];
		int[] indegree = new int[N+1];
		
		for (int i = 1; i <= N; i++) pan[i] = new ArrayList<>();
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			// num1이 num2보다 앞에 있어야 한다.
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());
			pan[num1].add(num2);
			indegree[num2]++;
		}
		
		Queue<Integer> q = new LinkedList<>();
		
		for(int i=1; i<=N; i++) {
			if(indegree[i] == 0) {
				q.add(i);
			}
		}
		
		while(!q.isEmpty()) {
			int node = q.poll();
			bw.write(Integer.toString(node));
			bw.write(' ');
			
			for(int i=0; i<pan[node].size(); i++) {
				int next = pan[node].get(i);
					indegree[next]--;
				if(indegree[next] == 0) {
					q.offer(next);
				}
			}
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
