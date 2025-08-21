package test;

import java.io.*;
import java.util.*;

public class 큐_contact {

	static Node maxNumArr;

	static class Node {
		int sum;
		int number;

		Node(int sum, int number) {
			this.sum = sum;
			this.number = number;
		}
	}

	static void bigyo(List<Node> endNodes) {
		for (int i = 0; i < endNodes.size(); i++) {
			Node node = endNodes.get(i);
			if (maxNumArr.sum < node.sum) {
				maxNumArr = node;
			} else if (maxNumArr.sum == node.sum && maxNumArr.number < node.number) {
				maxNumArr = node;
			}
		}
	}

	static boolean[] isExistNumber;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = 10;

		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken()); // 입력받는 데이터의 길이
			int startNumber = Integer.parseInt(st.nextToken()); // 시작점

			boolean[] visited = new boolean[101]; // 0 ~ 100

			int[][] mang = new int[101][101];

			st = new StringTokenizer(br.readLine());

			while (st.hasMoreTokens()) {
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());

				mang[start][end] = 1;
			}

			Queue<Node> q = new LinkedList<>();

			Node startNode = new Node(0, startNumber);

			q.offer(startNode);

			visited[startNode.number] = true;

			maxNumArr = startNode;

			while (!q.isEmpty()) {
				Node start = q.poll();

				boolean isExistEnd = false;

				List<Node> endNodes = new ArrayList<>();

				for (int i = 0; i < mang[start.number].length; i++) {
					int end = mang[start.number][i];
					if (end == 1 && !visited[i]) {

						Node endNode = new Node(start.sum + 1, i);

						q.offer(endNode);
						visited[i] = true;

						isExistEnd = true;
						endNodes.add(endNode);
					}

					if (isExistEnd && i == mang[start.number].length - 1) {
						// 기존꺼랑 비교
						bigyo(endNodes);
					}
				}
			}
			bw.write("#" + test_case + " " + maxNumArr.number);
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}
}