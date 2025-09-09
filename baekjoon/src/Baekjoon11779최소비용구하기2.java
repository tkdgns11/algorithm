package src;

import java.io.*;
import java.util.*;

public class Baekjoon11779최소비용구하기2 {

	static class Node implements Comparable<Node> {
		int position;
		int value;

		public Node(int position, int value) {
			this.position = position;
			this.value = value;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(value, o.value);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine().trim());
		int M = Integer.parseInt(br.readLine().trim());

		int[][] pan = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			Arrays.fill(pan[i], Integer.MAX_VALUE);
		}

		StringTokenizer st;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());

			pan[num1][num2] = Math.min(pan[num1][num2], value);
		}

		st = new StringTokenizer(br.readLine());

		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		int[] dist = new int[N + 1];
		int[] path = new int[N + 1];

		Arrays.fill(dist, Integer.MAX_VALUE);

		PriorityQueue<Node> q = new PriorityQueue<>();

		dist[start] = 0;
		q.add(new Node(start, 0));

		while (!q.isEmpty()) {
			Node node = q.poll();

			if (node.value != dist[node.position])
				continue;

			if (node.position == end) {
				System.out.println(node.value);
				break;
			}

			for (int i = 1; i <= N; i++) {
				if (pan[node.position][i] == Integer.MAX_VALUE) {
					continue;
				}
				if (dist[i] > node.value + pan[node.position][i]) {
					dist[i] = node.value + pan[node.position][i];
					path[i] = node.position;
					q.offer(new Node(i, dist[i]));
				}
			}
		}
		Stack<Integer> stack = new Stack<>();
		int now = end;
		while (now != 0) {
			stack.push(now);
			now = path[now];
		}
		System.out.println(stack.size());
		while (!stack.isEmpty()) {
			System.out.print(stack.pop() + " ");
		}
	}
}
