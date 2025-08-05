package test;

import java.io.*;
import java.util.*;

class Baekjoon17825윷놀이 {
	
	class Node {
		Node[] next; // 다음 노드
		int score; //점수
		
	}
	
	
	public static void main(String args[]) throws Exception {
		// 파일 경로 설정 (프로젝트 폴더 기준)
        FileInputStream fileInputStream = new FileInputStream("input.txt");
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader br = new BufferedReader(inputStreamReader);
        BufferedWriter bw =  new BufferedWriter(new OutputStreamWriter(System.out));

		int T = 10;

		for (int test_case = 1; test_case <= T; test_case++) {
			int[] numberArr = new int[10];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int i=0; i<10; i++) {
				numberArr[i] = Integer.parseInt(st.nextToken());
			}
			
			// 윳놀이 판 만들기 
			
			

		}
		bw.flush();
		bw.close();
		br.close();
	}
}