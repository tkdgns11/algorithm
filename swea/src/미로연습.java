package src;

import java.util.*;
import java.io.*;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class 미로연습 {
	
	// 상 하 좌 우
	static final int[] dx = {0, 0, -1, 1};
	static final int[] dy = {-1, 1, 0, 0};
	static boolean find;
	static int[][] pan;
	
	static int Si;
	static int Sj;
	static int Ei;
	static int Ej;
	
	static void dfs(int I, int J) {
    	if(I == Ei && J == Ej) {
    		find = true;
    		return;
    	}
    	
    	for(int i =0; i<4; i++) {
    		int nx = J + dx[i];
    		int ny = I + dy[i];
    		
    		if(nx >= 0 && ny < 16 && pan[ny][nx] != 1) {
    			pan[ny][nx] = 1;
    			dfs(ny,nx);
    		}
    		if(find) return;
    	}
    }

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = 10;

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine().trim());

			find = false;
			pan = new int[16][16];

			for (int i = 0; i < 16; i++) {
				String str = br.readLine().trim();
				for (int j = 0; j < 16; j++) {
					pan[i][j] = Integer.valueOf(str.charAt(j) - '0');
					if(pan[i][j] == 2) {
						Si = i;
						Sj = j;
					} else if(pan[i][j] == 3) {
						Ei = i;
						Ej = j;
					}
				}
			}
			
			dfs(Si, Sj);
			
			bw.write("#" + test_case + " "+ (find ? 1 : 0));
			bw.write("\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}