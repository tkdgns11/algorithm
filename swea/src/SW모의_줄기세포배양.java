//package test;
//
//import java.io.*;
//import java.util.*;
//
///**
// *  X시간 동안 비활성 상태이고 X시간이 지나는 순간 활성 상태. 활성상태에서 X시간 지나면 죽음. 공간은 그대로 차지.
// *  활성상태가 되면 1번만 번식 가능.
// *  K시간 후 살아있는 줄기 세포(비활성 상태 + 활성 상태)의 총 개수를 구하는 프로그램
// *  
// *  i
//	j
//	투입된 시간
//	생명력
//	
//	투입된 시간 0
//	생명력 2
//	
//	1초후의 상태 : 비활성 
//	2초후의 상태 : 활성
//	3초후의 상태 : 번식 
//	4초후의 상태 : 죽음
//	
//	비활성 -> 활성 -> 번식 -> 죽음
//*/
//public class SW모의_줄기세포배양 {
//
//	// 상 하 좌 우
//	static final int[] dx = {0, 0, -1, 1};
//	static final int[] dy = {-1, 1, 0, 0};
//	static Node[][] pan;
//	static int N; // 가로
//	static int M; // 세로
//	static int K; // 배양시간
//	static List<int[]> side; // 변경 대상. 리스트 안에 배열
//	static List<Integer>[][] sideAdjust; // 변경 적용 대상. 배열 안에 리스트
//	
//	static int[][] copyPan;
//	
//	static class Node {
//		int i;
//		int j;
//		int time; // 투입된 시간
//		int life; // 생명력
//		
//		Node(int i, int j, int time, int life) {
//			this.i = i;
//			this.j = j;
//			this.time = time;
//			this.life = life;
//		}
//	}
//	
//	public static void main(String[] args) throws Exception {
//		
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//
//		int T = Integer.parseInt(br.readLine().trim());
//		
//		for(int tc = 1; tc<=T; tc++) {
//			
//			pan = new Node[400][400];
//			
//			StringTokenizer st = new StringTokenizer(br.readLine());
//			
//			N = Integer.parseInt(st.nextToken());
//			M = Integer.parseInt(st.nextToken());
//			K = Integer.parseInt(st.nextToken());
//			
//			side = new ArrayList<>();
//			
//			for(int i=0; i<N; i++) {
//				 st = new StringTokenizer(br.readLine());
//				for(int j=0; j<M; j++) {
//					pan[200 + i][200 + j] = Integer.parseInt(st.nextToken());
//					if(pan[200 + i][200 + j] > 0) {
//						side.add(new int[] {200 + i, 200 + j});
//					}
//				}
//			}
//			
//			int Time = 1;
//			
//			while(K > Time) { //등호 들어갈 수도 있음.
//				// 번식
//				for(int i=0; i<400; i++) {
//					Arrays.fill(sideAdjust[i], new ArrayList<>());
//				}
//				
//				for(int i=0; i<side.size(); i++) {
//					int[] node = side.get(i);
//					int startI = node[0];
//					int startJ = node[1];
//					
//					for(int dir = 0; dir<4; dir++) {
//						int nx = startJ + dx[dir];
//						int ny = startI + dy[dir];
//						
//						if(pan[nx][ny] == 0) {
//							sideAdjust[ny][nx].add(pan[startI][startJ]); // 다음에 적용될 칸에 줄기세포의 생명력 값 추가
//						}
//					}
//				}
//				
//				for(int i=0; i<400; i++) {
//					for(int j=0; j<400; j++) {
//						if(sideAdjust[i][j].size() > 0) {
//							pan[i][j] = Collections.max(sideAdjust[i][j]);
//						}
//					}
//				}
//				
//				for(int i=0; i<pan.length; i++) {
//					for(int j=0; j<pan[i].length; j++) {
//						
//					}
//				}
//				
//				
//				Time++;
//			}
//			
//			
//		}
//	}
//
//}
package src;


