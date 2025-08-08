package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Baekjoon2577숫자의개수 {

	public static void main(String args[]) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int a = Integer.parseInt(br.readLine().trim());
		int b = Integer.parseInt(br.readLine().trim());
		int c = Integer.parseInt(br.readLine().trim());
		
		int[] numberArr = new int[10]; // 0 ~ 9
		
		long d = a * b * c;
		
		String str = "" + d;
		
		for(int i=0; i<str.length(); i++) {
			numberArr[(int)(str.charAt(i)-'0')]++;
		}
		
		for(int i=0; i<numberArr.length; i++) {
			bw.write("" + numberArr[i]);
			bw.write("\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
