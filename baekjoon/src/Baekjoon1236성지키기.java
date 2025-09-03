package src;

import java.util.*;
import java.io.*;

public class Baekjoon1236성지키기 {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		char[][] pan = new char[N][M];
		
		boolean[] panGaro = new boolean[M];
		boolean[] panSero = new boolean[N];
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<M; j++) {
				pan[i][j] = str.charAt(j);
				if(pan[i][j] == 'X') {
					panGaro[j] = true;
					panSero[i] = true;
				}
			}
		}
		
		int countGaro = 0;
		for(int i=0; i<panGaro.length;i++) {
			if(!panGaro[i]) countGaro++;
		}
		
		int countSero = 0;
		for(int i=0; i<panSero.length;i++) {
			if(!panSero[i]) countSero++;
		}
		
		bw.write("" + Math.max(countGaro, countSero));
		bw.flush();
		bw.close();
		br.close();
	}
}