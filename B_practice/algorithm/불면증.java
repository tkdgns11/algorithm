package algorithm;

import java.io.*;
import java.util.*;

public class 불면증 {
	
	static int[] numbers;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= T; tc++) {
			
			numbers = new int[10]; // 0~9
			
			int n = Integer.parseInt(br.readLine().trim());
			int x = 0;
			int count = 0; // 처음 발견한 숫자의 갯수
			
			while(count < 10) {
				x++;
				int testValue = n*x;
				while(testValue > 0) {
					int digit = testValue % 10;
					if(numbers[digit] == 0) count++;
					numbers[digit]++;
					testValue /= 10;
				}
			}
			bw.write("#" + tc + " " + n*x + '\n');
		}
		bw.flush();
		bw.close();
		br.close();
	}

}
