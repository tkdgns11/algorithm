package test;

import java.io.*;
import java.util.*;

/**
 * 수빈이는 동생과 숨바꼭질을 하고 있다. 수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 동생은 점 K(0 ≤ K ≤
 * 100,000)에 있다. 수빈이는 걷거나 순간이동을 할 수 있다. 만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로
 * 이동하게 된다. 순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동하게 된다. 수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을
 * 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하는 프로그램
 * 
 * 둘째 줄에는 가장 빠른 시간으로 수빈이가 동생을 찾는 방법의 수를 출력한다.
 */

class Baekjoon12851숨바꼭질2 {
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 출발 위치

		int M = Integer.parseInt(st.nextToken()); // 도착 위치
		
		int Max = 100000;

		int[] dist = new int[Max + 1];

		int[] ways = new int[Max + 1];

		Arrays.fill(dist, -1);
		
		int minTime = Integer.MAX_VALUE; // 맨 처음에 큰 값으로 초기화

		Queue<Integer> q = new LinkedList<>();
		dist[N] = 0;
		ways[N] = 1;
		q.add(N);

		while (!q.isEmpty()) {
			int cur = q.poll();
			
			if(dist[cur] + 1 > minTime) continue; // 현재 기준 하나 더 갔더니 minTime보다 크면 의미x

			int[] nx = { cur + 1, cur - 1, cur * 2 };

			for (int i = 0; i < nx.length; i++) {
				if (nx[i] < 0 || nx[i] > 100000) continue;
				if (dist[nx[i]] == -1) { // 처음 방문이면
					dist[nx[i]] = dist[cur] + 1;
					ways[nx[i]] = ways[cur];
					q.offer(nx[i]);
				} else if(dist[nx[i]] == dist[cur] + 1) { // 최단시간 또 도착
					ways[nx[i]] += ways[cur];
				}
				
				if(nx[i] == M) {
					minTime = dist[nx[i]];
				}
				
			}
		}

		bw.write("" + dist[M]);
		bw.write("\n");
		bw.write("" + ways[M]);

		bw.flush();
		bw.close();
		br.close();
	}
}
