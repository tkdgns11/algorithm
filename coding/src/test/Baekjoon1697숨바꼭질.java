package test;

import java.io.*;
import java.util.*;

/**
 * 수빈이는 동생과 숨바꼭질을 하고 있다. 수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 
 * 동생은 점 K(0 ≤ K ≤ 100,000)에 있다. 
 * 수빈이는 걷거나 순간이동을 할 수 있다. 
 * 만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다. 
 * 순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동하게 된다.
   수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하는 프로그램
 
 * 2*x 를 어떻게 표현할것인가.. 
 * 동생의 위치 보다 커도되고 0보다 작아도 됨. 완전탐색으로 할때 계속해서 무한대로 작아지거나 커지는 경우도 있음..
 *  -> 0 보다 크거나 같고 10만보다 작거나 같은 ..
 * BFS 로도 안되는게, 가중치가 있어서 최단거리라는 보장이 없음..
 * 그냥, 시작점 부터 빼고 더하고 2곱하고를 계속 반복해서 맨 처음 도착점 만나면 그게 최단거리.                              
 */

class Baekjoon1697숨바꼭질 {
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 출발 위치
		
		int M = Integer.parseInt(st.nextToken()); // 도착 위치
		
		int[] dist = new int[100001];
		
		Arrays.fill(dist, -1);
		
		Queue<Integer> q = new LinkedList<>();
		dist[N] = 0;
		q.add(N);
		
		
		while(!q.isEmpty()) {
			int currentPostion = q.poll();
			
			if(currentPostion == M) break;
			
			int nX;
			
			nX = currentPostion + 1;
			if(nX >= 0 && nX <= 100000 && dist[nX] == -1) {
				dist[nX] = dist[currentPostion] + 1;
				q.offer(nX);
			}
			
			nX = currentPostion - 1;
			if(nX >= 0 && nX <= 100000 && dist[nX] == -1) {
				dist[nX] = dist[currentPostion] + 1;
				q.offer(nX);
			}
			
			nX = currentPostion * 2;
			if(nX >= 0 && nX <= 100000 && dist[nX] == -1) {
				dist[nX] = dist[currentPostion] + 1;
				q.offer(nX);
			}
		}
		
		bw.write("" + dist[M]);
		
		bw.flush();
		bw.close();
		br.close();
	}
}
