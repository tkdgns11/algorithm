package test;

import java.util.*;
import java.io.*;

class Solution2
{
	
	static void arriveValue(int[][] pan, int row, int col, int N) {
		// 아래로 끝까지 도착한1 보내기
        if(pan[row][col] == 1 && row == N-1) {
            pan[row][col] = 0;
        }
        
        // 위로 끝까지 도착한2 보내기
        if(pan[row][col] == 2 && row == 0) {
            pan[row][col] = 0;
        }
	}
	
	public static void main(String args[]) throws Exception
	{
		// 파일 경로 설정 (프로젝트 폴더 기준)
        FileInputStream fileInputStream = new FileInputStream("input.txt");
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader br = new BufferedReader(inputStreamReader);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
		int T = 10;
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
            int N = Integer.parseInt(br.readLine().trim());
            
            int[][] pan = new int[N][N]; // N =100
            for(int i=0; i<N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    pan[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            int[][] resultPan = new int[N][N];
            // 1: N극의 성질을 가짐-> 아랫쪽으로. 
            // 2: S극의 성질을 가짐 -> 위쪽으로
            // 0: x
            
            // 1. 오른쪽 그림 상태로 만들기
            // 2. 카운트. 위에서부터 연속된 1세고 0만나는 순간 result ++;
            
            
            for(int j=0; j<N; j++) {
                
                Set set = new HashSet();
                
                // 1 아래로 보내기
                for(int i=0; i<N; i++) {
                	arriveValue(pan, i, j, N);
                    // 아래로 보내기
                    if(pan[i][j] == 1 && pan[i+1][j] == 0 && !set.contains(i+1)) {
                        pan[i][j] = 0;
                        pan[i+1][j] = 1; 
                    } else if(pan[i][j] == 1 && pan[i+1][j] == 2) {
                        set.add(i);
                        set.add(i+1);
                    }
                }
                
                // 2 위로 보내기
                for(int k=N-1; k>=0; k--) {
                	arriveValue(pan, k, j, N);
                    // 위로 보내기
                    if(pan[k][j] == 2 && pan[k][k-1] == 0 && !set.contains(k-1)) {
                        pan[k][j] = 0;
                        pan[k-1][j] = 2; 
                    } else if(pan[k][j] == 2 && pan[k-1][j] == 1) {
                        set.add(k);
                        set.add(k-1);
                    }
                }
            }
            
            // 카운팅 
            int result = 0;
            for(int j=0; j<N; j++) {
                boolean visitN = false;
                for(int i=0; i<N; i++) {
                    if(pan[i][j] == 1 && visitN == true) {
                        continue;
                    } else if(pan[i][j] == 1 && visitN == false) {
                        visitN = true;
                    } else if(pan[i][j] == 2 && visitN == true) {
                        visitN = false;
                        result++; 
                    }
                }
            }
            
            // #1 471
            bw.write("#" + test_case + " " + result);
            bw.newLine();
		}
		bw.close();
		br.close();
	}
}