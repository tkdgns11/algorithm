package test;

import java.io.*;
import java.util.*;

public class 스택2계산기3 {

	private static int priority(char c) {
		if (c == '*') return 2;
		else return 1; // '+'
	}
	
	public static void main(String[] args) throws Exception {

		// 파일 경로 설정 (프로젝트 폴더 기준)
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = 3;

		for (int test_case = 1; test_case <= T; test_case++) {
			
			int N = Integer.parseInt(br.readLine().trim());
			
			String str = br.readLine();
			
			StringBuilder sb = new StringBuilder();
			
			Stack<Character> stack = new Stack<>();
			
			for(int i=0; i<N; i++) {
			  char c = str.charAt(i);
			  
			  if(c >= '0' && c<= '9') { // 숫자
				  sb.append(c);
			  } else if(c == '+' || c == '*') { // + or *
				  while (!stack.isEmpty() && stack.peek() != '('
						  && priority(stack.peek()) >= priority(c)) {
					  sb.append(stack.pop());
				  }
				  stack.push(c);
			  } else { // ( or )
				  if(c == '(') {
					  stack.push(c);
				  } else { // )
					  while (!stack.isEmpty() && stack.peek() != '(') {
						  sb.append(stack.pop());
					  }
					  stack.pop();
				  }
			  }
			}

			while(!stack.isEmpty()){
				sb.append(stack.pop());
			}

			String convertResult = sb.toString();
			
			Stack<Integer> stack1 = new Stack<>();  
			
			for(int i=0; i<convertResult.length(); i++) {
				char c2 = convertResult.charAt(i);
				if(c2 >= '0' && c2 <= '9') {
					stack1.push(Integer.valueOf(c2 - '0'));
				} else if(c2 == '*') {
					int num1 = Integer.valueOf(stack1.pop());
					int num2 = Integer.valueOf(stack1.pop());
					
					stack1.push((num1 * num2));
				} else { // +
					int num1 = Integer.valueOf(stack1.pop());
					int num2 = Integer.valueOf(stack1.pop());
					
					stack1.push((num1 + num2));
				}
			}
			// #1 672676
			bw.write("#" + test_case + " " + stack1.pop());
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}
}