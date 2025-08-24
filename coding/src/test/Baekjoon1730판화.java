package test;

import java.io.*;
import java.util.*;

/**
 * 로봇팔이 지나지 않은 점은 '.'으로, 
 * 로봇팔이 수직 방향으로만 지난 점은 '|'으로, 
 * 로봇팔이 수평 방향으로만 지난 점은 '-'으로, 
 * 수직과 수평 방향 모두로 지난 점은 '+'로 표기하도록 한다. 
 * 네 문자의 ASCII 코드는 각각 46, 124, 45, 43이다.
 */
public class Baekjoon1730판화 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine().trim());
		
		String str = br.readLine();
		
		char[][] pan = new char[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				pan[i][j] = (char)46;
			}
		}
		
		//위쪽으로 이동은 'U', 아래쪽으로 이동은 'D', 왼쪽으로 이동은 'L', 오른쪽으로 이동은 'R'로 표시된다.
		
		int cx = 0;
		int cy = 0;
		
		for(int i=0; i<str.length(); i++) {
			
			char curChar = pan[cy][cx];
			
			char dir = str.charAt(i);
			
			int nx;
			int ny;
			
			if(dir == 'U') {
				nx = cx;
				ny = cy -1;
			} else if(dir == 'D') {
				nx = cx;
				ny = cy + 1;
			} else if(dir == 'L') {
				nx = cx - 1;
				ny = cy;
			} else { // R
				nx = cx + 1;
				ny = cy;
			}
			
			if(nx >= 0 && nx < N && ny >= 0 && ny < N) {
				// curChar 처리
				if(curChar == '.') {
					if(dir == 'U' || dir == 'D') {
						pan[cy][cx] = '|';
					} else {
						pan[cy][cx] = '-';
					}
				} else if(curChar == '|' && (dir == 'R' || dir == 'L')) {
					pan[cy][cx] = '+';
				} else if(curChar == '-' && (dir == 'U' || dir == 'D')) {
					pan[cy][cx] = '+';
				}
				
				char nextChar = pan[ny][nx];
				
				// nextChar 처리
				if(nextChar == '.') {
					if(dir == 'U' || dir == 'D') {
						pan[ny][nx] = '|';
					} else {
						pan[ny][nx] = '-';
					}
				} else if(nextChar == '|' && (dir == 'R' || dir == 'L')) {
					pan[ny][nx] = '+';
				} else if(nextChar == '-' && (dir == 'U' || dir == 'D')) {
					pan[ny][nx] = '+';
				}
				
				cx = nx;
				cy = ny;
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				bw.write(pan[i][j]);
			}
			bw.write("\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
