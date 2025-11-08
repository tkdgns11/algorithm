package src;

import java.io.*;
import java.util.*;

public class 수열편집 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken()); // 수열의 길이
			int M = Integer.parseInt(st.nextToken()); // 추가 횟수 
			int L = Integer.parseInt(st.nextToken()); // 출력 인덱스 번
		}
	}
}