package test;

import java.util.*;
import java.io.*;

public class Main2 {
	
	class Piece {
	  int r, c;     // 현재 위치 (0~N-1)
	  int dir;      // 0=위, 1=오른쪽, 2=아래, 3=왼쪽 (예시)
	}
	
	public static void main(String[] args) throws Exception {
		
		 // 파일 경로 설정 (프로젝트 폴더 기준)
        FileInputStream fileInputStream = new FileInputStream("input.txt");
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader br = new BufferedReader(inputStreamReader);
        BufferedWriter bw =  new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine().trim());
		// 각 칸에 들어있는 말들의 번호를 아래→위 순서로 저장
		List<Integer>[][] board = new ArrayList[N][N];
		for (int i = 0; i < N; i++)
		  for (int j = 0; j < N; j++)
		    board[i][j] = new ArrayList<>();
		// 초기에는 pieces[i]에 설정된 (r,c)에 i를 추가
		
		int[][] color = new int[N][N];  // 0=흰, 1=빨, 2=파
	}

}
