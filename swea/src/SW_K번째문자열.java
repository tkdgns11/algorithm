package src;

import java.io.*;
import java.util.*;

public class SW_K번째문자열 {
	
	static int K;
	static String str;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			
			K = Integer.parseInt(br.readLine().trim());
			
			str = br.readLine();
			
			int N = str.length();
			
			int length = N;
			
			TreeSet<String> set = new TreeSet<>(); 
			
			while(N>0) {
				for(int i=0; i<=length-N; i++) {
					set.add(str.substring(i, i+N));
				}
				N--;
			}
			
			String result = null; 
			for(int i=0; i<K; i++) {
				result = set.pollFirst();
			}
			bw.write("#");
			bw.write(Integer.toString(tc));
			bw.write(' ');
			bw.write(result == null ? "none" : result);
			bw.write('\n');
		}
		bw.flush();
		bw.close();
		br.close();
	}
}