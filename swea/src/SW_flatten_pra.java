package src;

import java.io.*;
import java.util.*;

public class SW_flatten_pra {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for(int tc=1; tc<=10; tc++) {
			int dump = Integer.parseInt(br.readLine().trim());
			
			int[] heights = new int[101];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int i=0; i<100; i++) {
				int height = Integer.parseInt(st.nextToken());
				heights[height]++;
			}
			
			// 최저값, 최고값 찾기
			int max = 100;
			int min = 1;
			
			while(heights[max] == 0) max--;
			while(heights[min] == 0) min++;
			
			for(int i=0; i<dump; i++) {
				if(max - min <= 1) break;
				
				heights[max] --;
				heights[max-1] ++;
				if(heights[max] == 0) max--;
				
				heights[min] --;
				heights[min+1] ++;
				if(heights[min] == 0) min++;
			}
			bw.write("#" + tc + ' ' + (max-min));
			bw.write('\n');
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
