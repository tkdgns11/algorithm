//package test;
//
//import java.util.*;
//import java.io.*;
//
//// 가로 방향으로 이미 경사로 설치했을때 세로방향으로도 설치 가능한지 헷갈렸을떄 예시 확인해서 체크 잘함.
//public class SW모의_활주로건설 {
//
//	static int N;
//	static int X;
//	static int[][] pan;
//	
//	public static void main(String[] args) throws Exception {
//		
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//		
//		int T = Integer.parseInt(br.readLine().trim());
//		
//		for(int tc = 1; tc<= T; tc++) {
//			StringTokenizer st = new StringTokenizer(br.readLine());
//			
//			N = Integer.parseInt(st.nextToken());
//			X = Integer.parseInt(st.nextToken());
//			
//			pan = new int[N][N];
//			
//			for(int i=0; i<N; i++) {
//				st = new StringTokenizer(br.readLine());
//				for(int j=0; j<N; j++) {
//					pan[i][j] = Integer.parseInt(st.nextToken());
//				}
//			}
//			
//			int totalCount = 0;
//			
//			for(int i=0; i<N; i++) {
//				boolean garoCheck = false;
//				for(int j=0; j<N; j++) {
//					
//					// 가로 체크.
//					// 쭉 가다가 달라지면 체크. 커지면 이전걸 보고, 작아지면 다음걸 확인
//					if(pan[i][j])
//				}
//			}
//			
//		}
//	}
//}
package test;


