package test;

import java.io.*;
import java.util.*;

public class SW암호코드 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int tc=1; tc<=T; tc++) {
			/**
			 * 
			 * 세로 2000, 가로 500
			 * 배열은 16진수로 이루어져 있으며 -> 이 배열을 이진수로 변환
			 * 배열에는 1개이상의 암호코드가 존재. 정상or비정상
			 * 
			 * 포함된 암호코드들의 고유번호와 검증코드를 확인하여 정상여부를 판별한 뒤 
			 * 이 정상 암호코드들에 적혀있는 숫자들의 합을 출력한다.
			   총 소요시간이 적을수록 성능이 좋은 것으로 간주된다.
			   
			   암호 코드 하나당 숫자 8개. -> 고유번호 7개, 검증코드 1개
			   
			   
			 */
			
			
			
		}

	}

}
