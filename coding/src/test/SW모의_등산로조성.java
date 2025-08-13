package test;

import java.util.*;
import java.io.*;

public class SW모의_등산로조성 {
	
	// 상 하 좌 우
	static final int[] dx = {0, 0, -1, 1};
	static final int[] dy = {-1, 1, 0, 0};

	static class Node {
		int i;
		int j;
		int height;
		
		Node(int i, int j, int height) {
			this.i = i;
			this.j = j;
			this.height = height;
		}
	}
	
	static void check(int count) {
		maxCount = Math.max(maxCount, count);
	}
	
	// 나보다 높거나 같으면 깎거나, 안가거나, 낮은곳은 그냥 가고.
	static void dfs(int depth, int myI, int myJ, boolean used) { // count -> 남은 카운트
		check(depth);
		
		visited[myI][myJ] = true;
		for(int i=0; i<dx.length; i++) {
			int nx = myJ + dx[i];
			int ny = myI + dy[i];
			
			if(nx >=0 && nx <= N-1 && ny>=0 && ny <= N-1 && !visited[ny][nx]) {
				if(pan[ny][nx] < pan[myI][myJ]) {
					dfs(depth+1, ny, nx, used);
				} else { //다음칸이 크거나 같음. 차이가 1이 되게끔
					int gap= pan[ny][nx] - pan[myI][myJ];
					if(gap+1 <= K && !used) {
						int tmp = pan[ny][nx];
						pan[ny][nx] = pan[ny][nx] - (gap+1);
						dfs(depth+1, ny, nx, true);
						pan[ny][nx] = tmp;
					}
				}
			}
		}
		visited[myI][myJ] = false;
	}
	
	static int maxCount; // 갈 수 있는 최대 길이
	static int[][] pan;
	static List<Node> start; // 시작점
	static boolean[][] visited;
	static int N; // 지도의 한 변의 길이
	static int K; // 최대 공사 가능 깊이  (1 ≤ K ≤ 5)

	/**
	 * 현재 노드보다 낮은 지형으로, 가로 or 세로로. 
	 * 가장 긴 한 곳
	 * 필요한 경우 지형을 깎아 높이를 1보다 작게 만드는 것도 가능하다.
	 * **한번만 공사 가능
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int tc=1; tc<=T; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			
			K = Integer.parseInt(st.nextToken());
			
			start = new ArrayList<>();
			
			start.add(new Node(-1, -1, Integer.MIN_VALUE));
			
			pan = new int[N][N];
			visited = new boolean[N][N];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					pan[i][j] = Integer.parseInt(st.nextToken());
					
					if(pan[i][j] >= start.get(0).height) {
						if(pan[i][j] == start.get(0).height) {
							start.add(new Node(i,j,pan[i][j]));
						} else {
							start = new ArrayList<>();
							start.add(new Node(i,j,pan[i][j]));
						}
					}
				}
			}
			
			maxCount = Integer.MIN_VALUE;
			for(int i=0; i<start.size(); i++) {
				Node node = start.get(i);
				dfs(1, node.i, node.j, false);
			}
			bw.write("#" + tc + " " + maxCount);
			bw.write("\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}

}
