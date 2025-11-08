package algorithm;

import java.io.*;
import java.util.*;

public class 이진수_표현 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int mask = (1 << N) -1;
			
			boolean isOn = (M & mask) == mask;
			
			bw.write("#" + tc + " " + (isOn ? "ON" : "OFF") + '\n');
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
