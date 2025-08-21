package test;

import java.io.*;
import java.util.*;

class Baekjoon19236청소년상어 {
	static int totalScore;
	
	// ↑, ↖, ←, ↙, ↓, ↘, →, ↗ 
	static int[] dirX = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dirY = {-1, -1, 0, 1, 1, 1, 0, -1};
	
	static class Fish {
		int num;
		int dir;
		int i;
		int j;
		
		Fish(int num, int dir){
			this.num = num;
			this.dir = dir;
		}
		
		public String toString() {
			return "[" + this.num + ", " + this.dir + "]";
		}
	}
	
	static class Shark extends Fish{
		Shark(int num, int dir) {
			super(num, dir);
		}
	}
	
	static class Moolgogi extends Fish{
		Moolgogi(int num, int dir) {
			super(num, dir);
		}
		
	}
	
	// 상어 움직일 수 있는 칸 목록()
	static Fish[] canGoSharkArea(Fish[] moolgogiPan, Shark shark) {
		return null;
	}
	
	// 물고기 이동 
	static Fish[] moolgogiMove(Fish[] moolgogiPan, Shark shark) {
		return null;
	}
	
	static void dfs(Shark shark, int sum, Moolgogi[] moolgogiPan, Fish[] pan) {
		// 최대 갱신
		totalScore = Math.max(totalScore, sum);
		
		// 물고기 이동. 상어의 위치에따라 그때그때 물고기의 위치가 다름. 
		// 이때 상어의 위치 기준 물고기들의 위치, 방향이 담긴 판을 받기.
		
		// 상어가 움직일 수 있는 모든 경로
	}

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		/* 공간의 각 칸은 (x, y)와 같이 표현하며, x는 행의 번호, y는 열의 번호
		 * 각 칸의 물고기의 번호는 1보다 크거나 같고, 16보다 작거나 같은 자연수
		 * 방향은 8가지 방향(상하좌우, 대각선) 중 하나이다.
		 * 
		 * 물고기 이동.
		 * 물고기는 번호가 작은 물고기부터 순서대로 이동
		 * 한 칸을 이동할 수 있고, 이동할 수 있는 칸은 빈 칸과 다른 물고기가 있는 칸, 이동할 수 없는 칸은 상어가 있거나, 공간의 경계를 넘는 칸
		 * 각 물고기는 방향이 이동할 수 있는 칸을 향할 때까지 방향을 45도 반시계 회전시킨다. 만약, 이동할 수 있는 칸이 없으면 이동을 하지 않는다. 그 외의 경우에는 그 칸으로 이동을 한다.
		 * 물고기가 다른 물고기가 있는 칸으로 이동할 때는 서로의 위치를 바꾸는 방식으로 이동한다. -> 이때 방향은 ? 방향도 그대로 가져옴.

		 * 물고기의 이동이 모두 끝나면 상어가 이동한다. 상어는 방향에 있는 칸으로 이동할 수 있는데, 한 번에 여러 개의 칸을 이동할 수 있다. 
		 * 상어가 물고기가 있는 칸으로 이동했다면, 그 칸에 있는 물고기를 먹고, 그 물고기의 방향을 가지게 된다. 이동하는 중에 지나가는 칸에 있는 물고기는 먹지 않는다. 
		 * 물고기가 없는 칸으로는 이동할 수 없다. 
		 * 
		 * 상어가 이동할 수 있는 칸이 없으면 공간에서 벗어나 집으로 간다. 
		 * 
		 * 마지막 문단 해석. 상어가 먹을 수 있는 물고기 번호의 합의 최댓값을 구해보자. 가 문제니까, 
		 * 
		 * */
		
		Fish[][] pan = new Fish [4][4];
		
		for(int i=0; i<4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<4; j++) {
				pan[i][j] = new Moolgogi(Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken()) - 1);
			}
		}
		
		
		// 1. 특정 칸에 상어 넣고 dfs
		// 2. 상어 있는 상태에서 물고기 이동. 
		// -> 상어 이동가능한 모든 칸 + 판 넣고 dfs
		
		//상어를 (0,0) 에 넣고 dfs 호출 
		
//		for(int i=0; i<4; i++) {
//			for(int j=0; j<4; j++) {
//				System.out.print(pan[i][j]);
//			}
//			System.out.println();
//		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
