package src;

import java.util.*;
import java.io.*;

public class SW1961숫자배열회전 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {

			int N = Integer.parseInt(br.readLine().trim());

			int[][] pan = new int[N][N];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					pan[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					bw.write(pan[N-1-i][j] + "");
				}
				bw.write("\n");
			}

		}
		bw.flush();
		bw.close();
		br.close();
	}

}
