package test;

import java.io.*;
import java.util.*;

public class Baekjoon10250ACM호텔 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int H = Integer.parseInt(st.nextToken());
			
			int W = Integer.parseInt(st.nextToken());
			
			//int[][] pan = new int[H+1][W+1];
			
			int N = Integer.parseInt(st.nextToken());
			
			int sero = 0;
			int garo = 0; 
			
			outer : for(int i=1; i<=W; i++) {
				for(int j=1; j<=H; j++) {
					if(N == 1) {
						garo = i;
						sero = j;
						break outer ;
					}
					N--;
				}
			}
			
			bw.write(""+ sero + (garo <= 9 ? ("0"+garo) : garo) +"\n");
			
		}
		bw.flush();
		bw.close();
		br.close();
	}

}
