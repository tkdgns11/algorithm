package src;

import java.io.*;
import java.util.*;

public class Baekjoon117232집합 {
	
	static BufferedWriter bw;
	static int N;
	static Set S;
	
	static void add(int x) {
		S.add(x);
	}
	
	static void remove(int x) {
		S.remove(x);
	}
	
	static void check(int x) {
		try {
			bw.write(S.contains(x) ? '1' : '0');
			bw.write('\n');
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	static void toggle(int x) {
		if(S.contains(x)) {
			S.remove(x);
		} else {
			S.add(x);
		}
	}
	
	static void all() {
		S = new HashSet<Integer>();
		for(int i=1; i<=20; i++) {
			S.add(i);
		}
	}
	
	static void empty() {
		S = new HashSet<Integer>();
	}

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine().trim());
		
		S = new HashSet<Integer>();
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x;
			String method = st.nextToken();
			switch(method) {
				case "add" :  x = Integer.parseInt(st.nextToken()); add(x);
				break;
				
				case "remove" :  x = Integer.parseInt(st.nextToken()); remove(x);
				break;
				
				case "check" :  x = Integer.parseInt(st.nextToken()); check(x);
				break;
				
				case "toggle" :  x = Integer.parseInt(st.nextToken()); toggle(x);
				break;
				
				case "all" :  all();
				break;
				
				case "empty" :  empty();
				break;
			}
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
