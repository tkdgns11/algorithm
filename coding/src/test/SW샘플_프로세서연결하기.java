package test;

import java.io.*;
import java.util.*;

/**
 * 0은 빈 cell을 의미하며, 1은 core
 */
public class SW샘플_프로세서연결하기 {
	static int N;
	static int[][] pan;
	static boolean[][] visited;
	static List<Node> list;
	static int maxCount;
	
	static class Node {
		int i;
		int j;
		
		Node(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	
	static void check() {
		int count = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(pan[i][j] == 2) {
					count++;
				}
			}
		}
		maxCount = Math.max(maxCount, count);
	}

	/**
     * 백트래킹:
     * idx: 처리할 cores 인덱스
     * conn: 지금까지 연결한 코어 개수
     * len: 지금까지 깐 전선 길이
     */
	static void dfs(int idx, int conn, int len) {
		check(); // 연결 선 총 수 체크
		
		for(int i=0; i<list.size(); i++) {
			Node node = list.get(i);
			if(!visited[node.i][node.j]) {
				visited[node.i][node.j] = true;
				if(checkSun(node.i, node.j, 0)) {
				}
				visited[node.i][node.j] = false;
			}
		}
		// 연결 하거나(상 하 좌 우) 연결 안한거나
	}
	
	static boolean checkSun(int i, int j, int dir) { // 0:상 1:하 2:좌 3:우
		return false;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {

			N = Integer.parseInt(br.readLine().trim());

			StringTokenizer st;
			
			list = new ArrayList<>();

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					pan[i][j] = Integer.parseInt(br.readLine().trim());
					if(i != 0 && i != N-1 && j != 0 && j != N-1 && pan[i][j] == 1) {
						list.add(new Node(i,j));
					}
				}
			}
			maxCount = Integer.MIN_VALUE;
			bw.write("\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
