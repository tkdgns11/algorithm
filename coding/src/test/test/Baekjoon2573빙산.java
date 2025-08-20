package test;

import java.io.*;
import java.util.*;


/**
 *  구현 중 빙산 높이가 음수가 되는 경우에도 그대로 넣음.
 *  벽이 0인경우에도 녹아야 되는데 체크 빠뜨림. 인접한거 찾을땐 벽면 포함 안하지만, 녹이는거 찾을땐 벽면도 포함. 
 */
class Baekjoon2573빙산 {
	static int time; // 시간
	
	static int N; // 행 갯수
	static int M; // 열 갯수 
	
	static boolean[][] visited;
	
	static class Ice {
		int i; 
		int j;
		int height;
		
		Ice(int i, int j, int height){
			this.i = i;
			this.j = j;
			this.height = height;
		}
	}
	
	static void iceSlug() {
		int[][] icePanNew = new int[N][M];
		List<Ice> iceListNew = new ArrayList<>();
		
		for(int i=0; i<iceList.size(); i++) {
			Ice ice = iceList.get(i);
			int count = 0;
			
			for(int j=0; j<4; j++) {
				int nX = ice.j + dX[j];
				int nY = ice.i + dY[j];
				
				if(nX >= 0 && nX < M && nY >= 0 && nY < N && icePan[nY][nX] == 0) {
					count++;
				}
			}
			int nextHeight = (ice.height - count) < 0 ? 0 : (ice.height - count);
			
			icePanNew[ice.i][ice.j] = nextHeight;
			
			if(ice.height - count > 0) {
				iceListNew.add(new Ice(ice.i, ice.j, nextHeight));
			}
		}
		icePan = icePanNew;
		iceList = iceListNew;
	}
	
	static List<Ice> iceList;
	
	static int[][] icePan;
	
	// 상 하 좌 우
	static final int[] dX = {0, 0, -1, 1};
	
	static final int[] dY = {-1, 1, 0, 0};
	
	static void dfs(int i, int j) {
		visited[i][j] = true;
		
		List<Ice> searchList = search(i, j);
		
		if(!searchList.isEmpty()) {
			for(Ice ice : searchList) {
				dfs(ice.i, ice.j);
			}
		}
	}
	
	/**
	 * 현재 위치 기준 4방향 탐색해서 height가 0보다 큰 ice 목록 반환
	 * @param i
	 * @param j
	 * @return
	 */
	static List<Ice> search(int positionI, int positionJ) {
		List<Ice> searchList = new ArrayList<>();
		
		for(int i=0; i<4; i++) {
			int nX = positionJ + dX[i];
			int nY = positionI + dY[i];
			
			if(nX > 0 && nX < M-1 && nY > 0 && nY < N-1 && !visited[nY][nX] && icePan[nY][nX] > 0) {
				searchList.add(new Ice(nY, nX, icePan[nY][nX]));
			}
		}
		return searchList;
	}

	public static void main(String args[]) throws Exception {

		/**
		 * 2차원 배열에서 동서남북 방향으로 붙어있는 칸들은 서로 연결되어 있다고 말한다.
		 * 매 초마다 녹는 빙산을 모아놓고 한번에 flush
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 행 갯수
		M = Integer.parseInt(st.nextToken()); // 열 갯수 
		
		icePan = new int[N][M];
		
		iceList = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				icePan[i][j] = Integer.parseInt(st.nextToken());
				if(icePan[i][j] != 0) {
					Ice ice = new Ice(i, j, icePan[i][j]);
					iceList.add(ice);
				}
			}
		}
		
		/**
		 * BFS DFS 의미가 있나. 매 초마다 전체 배열 돌면서 4방향 탐색해서 없애고, 
		 * -> 빙산이 있는것만 체크. 빙산의 i, j, height 를 묶어서 리스트로 저장해놓고 그 리스트만 돌면서 4방향 탐색.
		 * 매초마다 하나 선택해서 DFS 해서 count 2이상 나오면 break;
		 */
		time = 0;
		boolean bunlee = false;
		
		outer : while(iceList.size() > 0) {
			visited = new boolean[N][M]; // visited 초기화
			Ice ice = iceList.get(0);
			dfs(ice.i, ice.j);
			
			for(int i=0; i<iceList.size(); i++) {
				Ice iceLink = iceList.get(i);
				
				if(!visited[iceLink.i][iceLink.j]) {
					bunlee = true;
					break outer;
				}
			}
			iceSlug();
			time++;
		}
		
		// 종료조건 : 빙산이 분리되는 최초의 시간. 빙산이 다 녹을때까지 분리되지 않으면 0을 출력.
		if(bunlee) {
			bw.write("" + time);
		} else {
			bw.write("0");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
