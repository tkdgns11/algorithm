package test;

import java.util.*;
import java.io.*;

public class Baekjoon3273두수의합 {

	static int N;
	static int[] input;
	static int X;
	static int count;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine().trim());
		
		input = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}

		X = Integer.parseInt(br.readLine().trim());
		
		Arrays.sort(input); // 1 2 3 5 7 9 10 11 12
		
		int i = 0;
		int j = input.length-1;
		
		while(i <= input.length-1 && j >= 0) {
			int Jvalue = input[j];
			if(j >= X) {
				j--;
				continue;
			} else {
				int ii = i;
				boolean find = false;
				while(ii < j) {
					if(input[ii] == X - Jvalue) {
						count++;
						i = ii;
						j--;
						find = true;
						break;
					}
					ii++;
				}
				
				if(!find) {
					j--;
				}
			}
			//System.out.println("i : " + i + " j : " + j);
		}
		
		System.out.println(count);
	}
}
