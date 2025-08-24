package test;

import java.io.*;
import java.util.*;

public class Baekjoon1181단어정렬arr {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine().trim());
		
		String[] pan = new String[N];
		
		for(int i=0; i<N; i++) {
			pan[i] = br.readLine().trim();
		}
		
		Arrays.sort(pan, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if(o1.length() != o2.length()) {
					return o1.length() - o2.length();
				} 
				return o1.compareTo(o2);
			}
		});
		
		System.out.println(pan[0]);
		for(int i=1; i<pan.length; i++) {
			if(!pan[i].equals(pan[i-1])) {
				System.out.println(pan[i]);
			}
		}

	}

}
