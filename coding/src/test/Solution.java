package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	
		private static int[][] dirs = {{-1,0}, {1,0}, {0,-1}, {0, 1}, {-1,-1}, {-1,1}, {1,-1}, {1,1}}; // 상 하 좌 우 왼쪽위 오른쪽위 왼쪽아래 오른쪽아래
	
		private static boolean isValidPosition(int x, int y, int N) {
			return 0 <= x && x < N && y >= 0 && y < N; 
		}
		
	    public static void main(String[] args) throws Exception {
	        
	            // 파일 경로 설정 (프로젝트 폴더 기준)
	            FileInputStream fileInputStream = new FileInputStream("input.txt");
	            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
	            BufferedReader br = new BufferedReader(inputStreamReader);
	            BufferedWriter bw =  new BufferedWriter(new OutputStreamWriter(System.out));
	            
	            // 첫 번째 줄 읽기 (정수)
	            int T = Integer.parseInt(br.readLine().trim());
	            
	            for(int test_case = 1; test_case <= T; test_case++) {
	            	// 두 번째 줄 읽기(정수)
		            int N = Integer.parseInt(br.readLine().trim());
	            	// 판 초기화
	            	char[][] pan = new char[N][N];
	            	StringTokenizer st ;
	            	
	            	for(int i=0; i<N; i++) {
	            		st = new StringTokenizer(br.readLine());
	            		for(int j=0; j<N; j++) {
	            			// 반환타입 String. char로 저장
	            			pan[i][j] = st.nextToken().charAt(0);
	            		}
	            	}
	            	
	            	int maxBuilding = Integer.MIN_VALUE;
	            	
	            	for(int i=0; i<N; i++) {
	            		for(int j=0; j<N; j++) {
	            			// 인근 공원 체크
	            			boolean park = false;
            				for(int k=0; k<dirs.length; k++) {
            					int x = i + dirs[k][0];
            					int y = j + dirs[k][1];
            					if(isValidPosition(x,y,N)) {
            						if(pan[x][y] == 'G') {
            							park = true;
            							break;
            						}
            					}
	            			}
	            			
	            			if(park) { // 인접한 곳에 공원 있으면 2
	            				maxBuilding = Math.max(2, maxBuilding);
	            			} else { // 없으면 자기자신 + 상하좌우 판 끝까지 이동 합
	            				int buildingNum = 0;
            					boolean goUp = true;
            					boolean goDown = true;
            					boolean goLeft = true;
            					boolean goRight = true;
            					int iCheck; 
            					int jCheck;
            					
            					// 자기자신
            					if(pan[i][j] == 'B') {
            						buildingNum++;
            					}
            					
            					// 상
            					iCheck = i-1;
            					while(goUp) {
            						if(isValidPosition(iCheck,j,N)) {
            							if(pan[iCheck][j] == 'B') {
            								buildingNum++;
            							}
            						} else {
            							goUp = false;
            						}
            						iCheck--;
            					}
            					
            					// 하
            					iCheck = i+1;
            					while(goDown) {
            						if(isValidPosition(iCheck,j,N)) {
            							if(pan[iCheck][j] == 'B') {
            								buildingNum++;
            							}
            						} else {
            							goDown = false;
            						}
            						iCheck++;
            					}
            					
            					// 좌
            					jCheck = j-1;
            					while(goLeft) {
            						if(isValidPosition(i,jCheck,N)) {
            							if(pan[i][jCheck] == 'B') {
            								buildingNum++;
            							}
            						} else {
            							goLeft = false;
            						}
            						jCheck--;
            					}
            					
            					// 우
            					jCheck = j+1;
            					while(goRight) {
            						if(isValidPosition(i,jCheck,N)) {
            							if(pan[i][jCheck] == 'B') {
            								buildingNum++;
            							}
            						} else {
            							goRight = false;
            						}
            						jCheck++;
            					}   					
            					maxBuilding = Math.max(buildingNum, maxBuilding);
	            			}
	            		}
	            	}
            	/*
	               [출력 예] 
					#1 7 
					#2 7 
					#3 5 
            	 */
        			bw.write("#" + test_case + " " + maxBuilding);
        			bw.newLine();
	    		}
	            // 리소스 정리
	            bw.flush();
	            bw.close();
	            br.close();
	            inputStreamReader.close();
	            fileInputStream.close();
	    }
	}