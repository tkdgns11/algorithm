package src;

import java.util.*;
import java.io.*;

public class SW모의_활주로건설 {

	static int N; // 6 ≤ N ≤ 20
	static int X; // 2 ≤ X ≤ 4
	static int[][] pan;
	static boolean[][] visitedGaro;
	static boolean[][] visitedSero;
	
	static boolean checkRow(int startL, int startR, int row, int[] arr) {
		int lValue = arr[startL];
		int rValue = arr[startR];
		
		List<int[]> list = new ArrayList<>();
		
		if(lValue < rValue ) {
			if(visitedGaro[row][startL]) return false;
			visitedGaro[row][startL] = true;
			list.add(new int[] {row, startL});
			for(int i=1; i<X; i++) {
				int ni = startL - i;
				if(ni < 0 || arr[ni] != lValue || visitedGaro[row][ni]) {
					for(int ii = 0; ii<list.size(); ii++) {
						int[] tmp = list.get(ii);
						visitedGaro[tmp[0]][tmp[1]] = false;
					}
					return false;
				}
				visitedGaro[row][ni] = true;
				list.add(new int[] {row, ni});
				lValue = arr[ni];
			}
		} else {
			if(visitedGaro[row][startR]) return false;
			visitedGaro[row][startR] = true;
			list.add(new int[] {row, startR});
			for(int i=1; i<X; i++) {
				int ni = startR + i;
				if(ni >= N || arr[ni] != rValue || visitedGaro[row][ni]) {
					for(int ii = 0; ii<list.size(); ii++) {
						int[] tmp = list.get(ii);
						visitedGaro[tmp[0]][tmp[1]] = false;
					}
					return false;
				}
				visitedGaro[row][ni] = true;
				list.add(new int[] {row, ni});
				rValue = arr[ni];
			}
		}
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc<= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			
			pan = new int[N][N];
			visitedGaro = new boolean[N][N];
			visitedSero = new boolean[N][N];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					pan[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int totalCount = 0;
			
			outer : for(int i=0; i<N; i++) {
				for(int j=1; j<N; j++) {
					if(pan[i][j] != pan[i][j-1]) {
						if(Math.abs(pan[i][j] - pan[i][j-1]) > 1 ) continue outer;
						if(!checkRow(j-1, j, i, pan[i])) continue outer;
					}
					
					if(j == N-1) {
						totalCount++;
					}
				}
			}
			
			outer : for(int i=0; i<N; i++) {
				for(int j=1; j<N; j++) {
					if(pan[j-1][i] != pan[j][i]) {
						if(Math.abs(pan[j-1][i] - pan[j][i]) > 1) continue outer;
						
						int uValue = pan[j-1][i];
						int dValue = pan[j][i];
						
						List<int[]> list = new ArrayList<>();
						
						if(uValue < dValue ) {
							if(visitedSero[j-1][i]) continue outer;
							visitedSero[j-1][i] = true;
							list.add(new int[] {j-1, i});
							for(int i1=1; i1<X; i1++) {
								int ni = j-1 - i1;
								if(ni < 0 || pan[ni][i] != uValue || visitedSero[ni][i]) {
									for(int ii = 0; ii<list.size(); ii++) {
										int[] tmp = list.get(ii);
										visitedSero[tmp[0]][tmp[1]] = false;
									}
									continue outer;
								}
								visitedSero[ni][i] = true;
								list.add(new int[] {ni, i});
								uValue = pan[ni][i];
							}
						} else { // uValue > dValue
							if(visitedSero[j][i]) continue outer;
							visitedSero[j][i] = true;
							list.add(new int[] {j, i});
							for(int i1=1; i1<X; i1++) {
								int ni = j + i1;
								if(ni >= N || pan[ni][i] != dValue || visitedSero[ni][i]) {
									for(int ii = 0; ii<list.size(); ii++) {
										int[] tmp = list.get(ii);
										visitedSero[tmp[0]][tmp[1]] = false;
									}
									continue outer;
								}
								visitedSero[ni][i] = true;
								list.add(new int[] {ni, i});
								dValue = pan[ni][i];
							}
						}
					}
					if(j == N-1) {
						totalCount++;
					}
				}
			}
			bw.write("#" + tc + " " + totalCount + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}


