package test;

import java.io.*;
import java.util.*;

public class 스택2계산기3 {
	
	public static void main(String[] args) throws Exception {

		// 파일 경로 설정 (프로젝트 폴더 기준)
		FileInputStream fileInputStream = new FileInputStream("input.txt");
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
		BufferedReader br = new BufferedReader(inputStreamReader);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = 1;

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
				  if(stack.isEmpty()) {
					  stack.push(c);
				  } else {
					  char c1 = stack.peek();
					  if(c1 == '+' && c == '*') { // 스택에 위치한 연산자의 우선순위가 낮으면
						  stack.push(c);
					  } else if((c1 == '*' && c == '+') || (c1 == '*' && c == '*') || (c1 == '+' && c == '+')) { // 스택에 위치한 연산자의 우선순위가 높거나 같으면 *
						  while(!stack.isEmpty() && stack.peek() !='(' ) {
							  char c3 = stack.peek();
							  if((c3 == '*' && c == '+') || (c3 == '*' && c == '*') || (c3 == '+' && c == '+')) {
								  sb.append(stack.pop());
							  }
						  }
						  stack.push(c);
					  } else if(c1 == '(' && c == '+') {
						  stack.push(c);
					  }
				  }
			  } else { // ( or )
				  if(c == '(') {
					  stack.push(c);
				  } else { // )
					  while(true) {
						  if(stack.peek() == '(') {
							  stack.pop();
							  break;
						  }
						  sb.append(stack.pop());
					  }
				  }
			  }
			}
			
			String convertResult = sb.toString();
			
			Stack<Integer> stack1 = new Stack<>();  
			
			System.out.println(convertResult);
			
			for(int i=0; i<convertResult.length(); i++) {
				char c2 = convertResult.charAt(i);
				if(c2 >= '0' && c2 <= '9') {
					stack1.push(Integer.valueOf(i));
				} else if(c2 == '*') {
					int num1 = Integer.valueOf(stack1.pop());
					int num2 = Integer.valueOf(stack1.pop());
					
					stack1.push((num1 * num2)); // 50
				} else { // +
					int num1 = Integer.valueOf(stack1.pop());
					int num2 = Integer.valueOf(stack1.pop());
					
					stack1.push((num1 + num2)); // 50
				}
			}
			
			// #1 672676
			bw.write("#" + test_case + " " + stack1.pop());
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
		br.close();
		inputStreamReader.close();
		fileInputStream.close();
	}

}