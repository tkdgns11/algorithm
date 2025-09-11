package src;

import java.io.*;
import java.util.*;

public class SW홈방범서비스 {
	static int N,M;
	static int[][] pan;
	// 상 하 좌 우
    static final int[] dx = {0, 0, -1, 1};
    static final int[] dy = {-1, 1, 0, 0};
    static boolean[][] visited;
    static boolean find;
    static int house;
    static Set<String> checkHouse;
    
    static void calc(int K) {
    	int cost = K * K + (K - 1) * (K - 1);
    	int findHouse = checkHouse.size();
    	if(findHouse * M >= cost) {
    		find = true;
    		house = findHouse;
    	}
    }
    
    static void dfs(int houseNum, int depth, int startI, int startJ) {
        if(depth == houseNum-1) return;

        for(int i=0; i<4; i++) {
            int nx = startJ + dx[i];
            int ny = startI + dy[i];

            if(nx>=0 && nx<N && ny>=0 && ny<N && !visited[ny][nx]) {
                visited[ny][nx] = true;
                if(pan[ny][nx] == 1) {
					checkHouse.add(""+ny+nx);
				}
                dfs(houseNum, depth+1, ny, nx);
                visited[ny][nx] = false;
            }
        }
    }
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			pan = new int[N][N];
			visited = new boolean[N][N];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					pan[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			find = false;
			house = 0;
			
			outer : for (int k = N - 1; k >= 0; k--) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						visited[i][j] = true;
						checkHouse = new HashSet<>();
						if(pan[i][j] == 1) {
							checkHouse.add(""+i+j);
						}
						dfs(k, 0, i, j);
						calc(k);
						if(find) break outer;
						visited[i][j] = false;
					}
				}
			}
			bw.write('#');
			bw.write(Integer.toString(tc));
			bw.write(' ');
			bw.write(Integer.toString(house));
			bw.write('\n');
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
