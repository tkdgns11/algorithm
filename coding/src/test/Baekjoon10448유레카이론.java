package test;

import java.io.*;
import java.util.*;

public class Baekjoon10448유레카이론 {

	static int N;
	static int[] numbers;
	static boolean find;
	static List<Integer> list;
	
	static void check() {
		int i = 0;
		for(int ii : numbers) {
			i += ii;
		}
		if(i == N) find = true;
	}
	
	static void dfs(int depth) {
		if(depth == 3) {
			check();
			return;
		}
		
		if(find) {
			return;
		}
		
		for(int i=0; i<list.size(); i++) {
			numbers[depth] = list.get(i);
			dfs(depth+1);
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			
			list = new ArrayList<>();
			
			numbers = new int[3];
			
			// 삼각수 모음
			int i = 1;
			while(true) {
				int value = i*(i+1)/2;
				if(value >= N) {
					break;
				}
				list.add(value);
				i++;
			}
			
			find = false;
			dfs(0);
			bw.write("" + (find ? 1 : 0));
			bw.write("\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}

}
