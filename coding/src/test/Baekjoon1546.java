package test;

import java.io.*;
import java.util.*;

public class Baekjoon1546 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine().trim());
		
		int[] pan = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int Max = Integer.MIN_VALUE; 
		
		for(int i=0; i<N; i++) {
			pan[i] = Integer.parseInt(st.nextToken());
			Max = Math.max(Max, pan[i]);
		}
		
		double total = 0;
		for(int i=0; i<N; i++) {
			System.out.println(pan[i]);
			int tmp = pan[i];
			total += (double)tmp / Max * 100;
		}
		
		double avg = total / N;
		
		bw.write(avg + "\n"); 
		bw.flush();
		bw.close();
		br.close();
	}

}
