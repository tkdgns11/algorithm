package src;

import java.io.*;
import java.util.*;

public class SW_최빈수구하기_pra {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int tc=1; tc<=T; tc++) {
			
			int N = Integer.parseInt(br.readLine().trim());
			
			int[] pan = new int[101];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int i=0; i<1000; i++) {
				int score = Integer.parseInt(st.nextToken());
				pan[score]++;
			}
			int maxbindoScore = 0; 
			int maxScore = 0;
			for(int i=0; i<101; i++) {
				if(maxbindoScore <= pan[i]) {
					maxbindoScore = pan[i];
					maxScore = i;
				}
			}
			bw.write("#" + tc + ' ' + maxScore);
			bw.write('\n');
		}
		bw.flush();
		bw.close();
		br.close();
	}
}