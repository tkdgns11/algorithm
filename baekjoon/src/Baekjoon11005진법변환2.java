package src;

import java.io.*;
import java.util.*;

// 10진수의 수를 B진법으로 변환
public class Baekjoon11005진법변환2 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());

		int B = Integer.parseInt(st.nextToken());

		char[] charArr = new char[36];
		for (int i = 0; i < charArr.length; i++) {
			if (i >= 0 && i <= 9) {
				charArr[i] = (char) ('0' + i); // (""+i).charAt(0);
			} else {
				charArr[i] = (char) ('A' + (i - 10));
			}
		}

		List<Character> list = new ArrayList<>();

		while (N >= B) {
			list.add(charArr[N % B]);
			N /= B;
		}
		
		StringBuilder sb = new StringBuilder();

		sb.append(charArr[N]);
	//	System.out.println(charArr[N]);
		
		for(int i=list.size()-1; i>=0; i--) {
			sb.append(list.get(i));
		//	System.out.println(list.get(i));
		}
		
		System.out.println(sb.toString());
	}
}