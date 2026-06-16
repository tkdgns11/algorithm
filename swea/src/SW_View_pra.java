package src;

import java.io.*;
import java.util.*;

public class SW_View_pra {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for(int tc=1; tc<=10; tc++) {
			int N = Integer.parseInt(br.readLine().trim());
			StringTokenizer st = new StringTokenizer(br.readLine());

			int[] heights = new int[N];
			
			for(int i=0; i<N; i++) {
				int height = Integer.parseInt(st.nextToken());
				heights[i] = height;
			}
			int count = 0;
			
			for(int i=2; i<N-2; i++) {
				int maxHeight = Math.max(Math.max(heights[i-2], heights[i-1]), Math.max(heights[i+2], heights[i+1]));
				if(heights[i] > maxHeight) count += (heights[i] - maxHeight);
			}
			bw.write("#" + tc + ' ' + count);
			bw.write('\n');
		}
		bw.flush();
		bw.close();
		br.close();
	}
}