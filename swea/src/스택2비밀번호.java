package src;

import java.io.*;
import java.util.*;

public class 스택2비밀번호 {
	
	
	public static void main(String[] args) throws Exception {

		// 파일 경로 설정 (프로젝트 폴더 기준)
		FileInputStream fileInputStream = new FileInputStream("input.txt");
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
		BufferedReader br = new BufferedReader(inputStreamReader);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = 2;

		for (int test_case = 1; test_case <= T; test_case++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken()); // 총 문자의 수
			
			String numbers = st.nextToken(); // 0~9 로 구성
			
			Stack<Character> stack = new Stack<>();
			
			for(int i=0; i<N; i++) {
				if(!stack.isEmpty()) {
					char tmp = stack.peek();
					if(tmp == numbers.charAt(i)) {
						stack.pop();
					} else {
						stack.push(numbers.charAt(i));
					}
				} else {
					stack.push(numbers.charAt(i));
				}
			}
			
			StringBuilder sb = new StringBuilder();
			
			while(!stack.isEmpty()) {
				sb.append(stack.pop());
			}
			
			String result = sb.reverse().toString();
			
			bw.write("#" + test_case + " " + result);
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
		br.close();
		inputStreamReader.close();
		fileInputStream.close();
	}

}