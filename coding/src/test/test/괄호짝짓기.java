package test;

import java.util.*;
import java.io.*;

public class 괄호짝짓기 {

	public static void main(String[] args) throws Exception {

		// 파일 경로 설정 (프로젝트 폴더 기준)
		FileInputStream fileInputStream = new FileInputStream("input.txt");
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
		BufferedReader br = new BufferedReader(inputStreamReader);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = 10;

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine().trim()); // 문자열의 길이
			String str = br.readLine().trim();

			// 4 종류의 괄호문자들 '()', '[]', '{}', '<>'
			Stack<Character> stack1 = new Stack<>();
			Stack<Character> stack2 = new Stack<>();
			Stack<Character> stack3 = new Stack<>();
			Stack<Character> stack4 = new Stack<>();
			
			boolean check = false;

			for (int i = 0; i < N; i++) {
				char c = str.charAt(i);
				if (c == '(') {
					stack1.push(c);
				} else if (c == '[') {
					stack2.push(c);
				} else if (c == '{') {
					stack3.push(c);
				} else if (c == '<') {
					stack4.push(c);
				} else if (c == ')') {
					if(!stack1.isEmpty()) {
						stack1.pop();
					} else {
						check = false; 
						break;
					}
				} else if (c == ']') {
					if(!stack2.isEmpty()) {
						stack2.pop();
					} else {
						check = false; 
						break;
					}
				} else if (c == '}') {
					if(!stack3.isEmpty()) {
						stack3.pop();
					} else {
						check = false; 
						break;
					}
				} else if (c == '>') {
					if(!stack4.isEmpty()) {
						stack4.pop();
					} else {
						check = false; 
						break;
					}
				}
				check = stack1.isEmpty() && stack2.isEmpty() && stack3.isEmpty() && stack4.isEmpty();
			}
			
			// (1 - 유효함, 0 - 유효하지 않음).
			bw.write("#" + test_case + " " + (check ? "1" : "0"));
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