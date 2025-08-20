package test;

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
		
		boolean[][] panCheck = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<M; j++) {
				pan[i][j] = str.charAt(j);
				if(pan[i][j] == 'X') {
					for(int ii=0; ii < panCheck[i].length; ii++) {
						panCheck[i][ii] = true;
					}
					
					for(int ii=0; ii < panCheck.length; ii++) {
						panCheck[ii][j] = true;
					}
					continue;
				}
			}
		}
		
		int count = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(!panCheck[i][j]) {
					count++;
					for(int ii=0; ii < panCheck[i].length; ii++) {
						panCheck[i][ii] = true;
					}
					
					for(int ii=0; ii < panCheck.length; ii++) {
						panCheck[ii][j] = true;
					}
				} 
				continue;
			}
		}
		
		bw.write("" + count);
		bw.flush();
		bw.close();
		br.close();
	}
}