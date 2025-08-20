package test;

import java.util.*;
import java.io.*;

public class SW트리_중위순회 {
	static int N;
	static char[] pan;
	static StringBuilder sb;
	static boolean[] visited;

	static void inOrder(int T) {
		if (T > N)
			return; // 범위 초과시
		if (pan[T] != '\u0000') {
			inOrder(T * 2);
			visited[T] = true;
			sb.append(pan[T]);
			inOrder(T * 2 + 1);
		}
	}

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = 1;

		for (int t = 1; t <= T; t++) {

			N = Integer.parseInt(br.readLine().trim());

			pan = new char[N + 1];
			visited = new boolean[N + 1];

			StringTokenizer st;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int index = Integer.parseInt(st.nextToken());
				pan[index] = st.nextToken().charAt(0);
			}

			sb = new StringBuilder();

			inOrder(1);

			bw.write("#" + t + " " + sb.toString());
			bw.write("\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
