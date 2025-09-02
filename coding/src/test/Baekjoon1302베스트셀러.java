package test;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Baekjoon1302베스트셀러 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine().trim());
		
		Map<String, Integer> map = new HashMap<>();
		
		for(int i=0; i<N; i++) {
			String str = br.readLine().trim();
			if(!map.containsKey(str)) {
				map.put(str, 1);
			} else {
				int count = map.get(str);
				map.put(str, ++count);
			}
		}
		
	}

}
