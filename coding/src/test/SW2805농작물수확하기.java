package test;

import java.io.*;
import java.util.*;

public class SW2805농작물수확하기 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			
			int N = Integer.parseInt(br.readLine().trim());
			
			int[][] pan = new int[N][N];
			
			int totalSum = 0;
			
			for(int i=0; i<N; i++) {
				String str = br.readLine();
				for(int j=0; j<N; j++) {
					pan[i][j] = Integer.parseInt(str.charAt(j)+"");
					totalSum += pan[i][j]; 
				}
			}
			
			int mosuri = N/2;
			
			int mosuriSum = 0;
			
			int ii = 0;
			
			while(mosuri > 0) {
				for(int i=0; i<mosuri; i++) {
					mosuriSum += pan[ii][i];
					mosuriSum += pan[ii][N-1-i];
					mosuriSum += pan[N-1-ii][i];
					mosuriSum += pan[N-1-ii][N-1-i];
				}
				mosuri--;
				ii++;
			}
			bw.write("#" + tc + " " + (totalSum - mosuriSum) + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}

}
