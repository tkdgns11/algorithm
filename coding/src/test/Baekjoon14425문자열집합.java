package test;

import java.io.*;
import java.util.*;

public class Baekjoon14425문자열집합 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		String[] pan = new String[N];
		
		for(int i=0; i<N; i++) {
			pan[i] = br.readLine().trim();
		}
		
		Arrays.sort(pan);
		
		int count = 0;
		
		for(int i=0; i<M; i++) {
			int start = 0;
			int end = N-1;
			String str = br.readLine().trim();
			
			int check = N/2;

			while(start <= end) {
				check = (start + end)/2;
				
				if(pan[check].equals(str)) {
					count++;
					break;
				}
				
				if(pan[check].compareTo(str) > 0) {
					end = check-1;
				} else {
					start = check+1;
				}
			}
		}
		bw.write(""+count);
		bw.flush();
		bw.close();
		br.close();
	}
}
