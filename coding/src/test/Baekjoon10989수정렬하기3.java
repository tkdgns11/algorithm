package test;

import java.util.*;
import java.io.*;

public class Baekjoon10989수정렬하기3 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine().trim());
		
		int[] pan = new int[N];
		
		for(int i=0; i< N; i++) {
			pan[i] = Integer.parseInt(br.readLine().trim());
		}
		
		Arrays.sort(pan);
		
		for(int i=0; i< N; i++) {
			bw.write("" + pan[i]);
			bw.write("\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
