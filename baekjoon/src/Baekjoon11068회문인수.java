package src;

import java.io.*;
import java.util.*;

public class Baekjoon11068회문인수 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
		int T = Integer.parseInt(br.readLine().trim());
		
		char[] charArr = new char[64];
		
		for (int i = 0; i < charArr.length; i++) {
			if (i >= 0 && i <= 9) {
				charArr[i] = (char) ('0' + i); // (""+i).charAt(0);
			} else {
				charArr[i] = (char) ('A' + (i - 10));
			}
		}

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine().trim());
			
			boolean result2 = false; // 회문 찾음
			
			outer : for(int B=2; B<=64 && !result2; B++) {
				List<Character> list = new ArrayList<>();

				int Ncopy = N; 
				
				while (Ncopy >= B) {
					list.add(charArr[Ncopy % B]);
					Ncopy /= B;
				}
				
				StringBuilder sb = new StringBuilder();

				sb.append(charArr[Ncopy]);
				
				for(int i=list.size()-1; i>=0; i--) {
					sb.append(list.get(i));
				}
				
				String result1 = sb.toString(); 
				
				for(int i=0; i<result1.length()/2; i++) {
					if(result1.charAt(i) != result1.charAt(result1.length()-1-i)) { // 다른거 찾는순간 멈추고 다음 진법으로
						continue outer;
					}
				}
				result2 = true;
			}
			bw.write((result2 ? 1 : 0) + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}