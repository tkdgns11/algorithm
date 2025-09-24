package src;

import java.io.*;
import java.util.*;

public class 떨어지는_1자_블록0917 {
	
	static int N, M, K;
	static int[][] pan;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		pan = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				pan[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
