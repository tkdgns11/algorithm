package src;

import java.io.*;
import java.util.*;

public class SW회문2_0908 {

	static char[][] pan;
	
	static boolean garoCheck(int row, int start, int length) {
		int end = start + length - 1;
		while(start < end) {
			if(pan[row][start] != pan[row][end]) {
				return false;
			}
			start++;
			end--;
		}
		return true;
	}
	
	static boolean seroCheck(int col, int start, int length) {
		int end = start + length - 1;
		while(start < end) {
			if(pan[start][col] != pan[end][col]) {
				return false;
			}
			start++;
			end--;
		}
		return true;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for (int tc = 1; tc <= 10; tc++) {
			int test = Integer.parseInt(br.readLine().trim());
			pan = new char[100][100];

			for (int i = 0; i < 100; i++) {
				String str = br.readLine();
				for (int j = 0; j < 100; j++) {
					pan[i][j] = str.charAt(j);
				}
			}

			int maxLength = -1;
			
			outer : while(true) {
				for(int i=100; i>0; i--) { // 회문 문자열 길이
					for(int ii=0; ii<100; ii++) {
						for(int j=0; j<=100-i; j++) { //가로
							if(garoCheck(ii, j, i)) {
								maxLength = i;
								break outer; 
							}
						}
					}
					
					for(int ii=0; ii<100; ii++) {
						for(int j=0; j<=100-i; j++) { // 세로
							if(seroCheck(ii, j, i)) {
								maxLength = i;
								break outer; 
							}
						}
					}
				}
			}
			bw.write('#');
			bw.write(Integer.toString(test));
			bw.write(' ');
			bw.write(Integer.toString(maxLength));
			bw.write('\n');
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
