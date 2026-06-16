package src;

import java.io.*;
import java.util.*;

public class SW_magnetic {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for(int tc=1; tc<=10; tc++) {
			int N = Integer.parseInt(br.readLine().trim());
			
			int[][] pan = new int[N][N];
			
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					pan[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int count = 0;
			
			for(int j=0; j<N; j++) {
				boolean seenN = false;
				for(int i=0; i<N; i++) {
					if(pan[i][j] == 1) seenN = true;
					else if(pan[i][j] == 2) {
						if(seenN) {
							count++;
							seenN = false;
						}
					}
				}
		    }
			bw.write("#" + tc + ' ' + count);
			bw.write('\n');
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
