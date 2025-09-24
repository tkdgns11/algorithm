package src;

import java.io.*;
import java.util.*;

public class 숫자가_더_큰_인접한_곳으로_이동0917 {
	
	// 상 하 좌 우
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	static int N;
	static int r, c;
	static int[][] pan;
	static boolean find;
	static boolean[][] visited;
	static BufferedWriter bw;
	
	static void dfs(List<Integer> printList, int i, int j) throws IOException {
		if(find) return;
		
		for(int i1=0; i1<4; i1++) {
			int nx = j + dx[i1];
			int ny = i + dy[i1];
			
			if(nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[ny][nx] && pan[ny][nx] > pan[i][j] && !find) {
					printList.add(pan[ny][nx]);
					dfs(printList, ny, nx);
			}
		}

		if(!find) {
			for(int i2=0; i2<printList.size(); i2++) {
				int num = printList.get(i2);
				bw.write(Integer.toString(num));
				bw.write(' ');
			}
		}
		find = true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken())-1;
		c = Integer.parseInt(st.nextToken())-1;
		
		pan = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				pan[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		find = false;
		visited = new boolean[N][N];
		List<Integer> list = new ArrayList<>();
		list.add(pan[r][c]);
		visited[r][c] = true;
		dfs(list, r, c);
		bw.flush();
		bw.close();
		br.close();
	}
}
