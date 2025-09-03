package src;

import java.io.*;
import java.util.*;

public class SW1959두개의숫자열 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {

			StringTokenizer st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[] num1 = new int[N];
			
			st = new StringTokenizer(br.readLine());
			
			for(int i=0; i<N; i++) {
				num1[i] = Integer.parseInt(st.nextToken());
			}
			
			int[] num2 = new int[M];
			
			st = new StringTokenizer(br.readLine());
			
			for(int i=0; i<M; i++) {
				num2[i] = Integer.parseInt(st.nextToken());
			}
			
			int Max = Integer.MIN_VALUE;
			int i = 0;
			
			if(num1.length <= num2.length) {
				while(true) {
					if(num2.length - num1.length < i) {
						break;
					}
					int sum = 0;
					for(int ii = 0; ii<num1.length; ii++ ) {
						sum += num1[ii] * num2[ii + i]; 
					}
					Max = Math.max(Max, sum);
					i++;
				}
			} else {
				while(true) {
					if(num1.length - num2.length < i) {
						break;
					}
					int sum = 0;
					for(int ii = 0; ii<num2.length; ii++ ) {
						sum += num2[ii] * num1[ii + i]; 
					}
					Max = Math.max(Max, sum);
					i++;
				}
			}
			
			bw.write("#" + tc + " " + Max + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}

}
