package src;

import java.io.*;
import java.util.*;

public class SW_Ladder {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for(int tc=1; tc<=10; tc++) {
			int N = Integer.parseInt(br.readLine().trim());
			
			int[][] pan = new int[100][100];
			
			int x = 0, y = 0;
			 
			for(int i=0; i<100; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<100; j++) {
					pan[i][j] = Integer.parseInt(st.nextToken());
					if(pan[i][j] == 2) {
						x = j;
						y = i;
					}
				}
			}
			
			while(y>0) {
				// 왼쪽에 길이 있으면 
				// 끝까지 가고, 위로 한칸
				if(x>0 && pan[y][x-1] == 1) {
					while(x>0 && pan[y][x-1] == 1) x -= 1;
					y -= 1;
				} else if(x<99 && pan[y][x+1] == 1) { 
					// 오른쪽에 길이 있으면
					// 끝까지 가고, 위로 한칸
					while(x<99 && pan[y][x+1] == 1) x += 1;
					y -= 1;
				} else { // 위로 한칸
					y -= 1;
				}
			}
			bw.write("#" + tc + ' ' + x);
			bw.write('\n');
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
