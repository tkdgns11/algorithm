package src;

import java.io.*;
import java.util.*;

public class SW_sum_pra {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for(int tc=1; tc<=10; tc++) {
			
			int N = Integer.parseInt(br.readLine().trim());
			
			int[][] pan = new int[100][100];
			
			for(int i=0; i<100; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<100; j++) {
					pan[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int max = 0, daegak1 = 0, daegak2 = 0;
			
			for(int i=0; i<100; i++) {
				int rowSum = 0, colSum = 0;
				for(int j=0; j<100; j++) {
					rowSum += pan[i][j];
					colSum += pan[j][i];
				}
				if(max < rowSum) max = rowSum;
				if(max < colSum) max = colSum;
				
				daegak1 += pan[i][i];
				daegak2 += pan[i][99-i];
			}
			
			if(max < daegak1) max = daegak1;
			if(max < daegak2) max = daegak2;
			
			bw.write("#" + tc + " " + max);
			bw.write('\n');
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
