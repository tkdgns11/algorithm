package src;

import java.io.*;
import java.util.*;

public class SW1289원재의메모리복구하기 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc<=T; tc++) {
			
			// 초기값은 모든 비트가 0
			
			String str = br.readLine().trim();
			
		    char cur = '0';
		    int count = 0;
			for(int i=0; i<str.length(); i++) {
				if(cur != str.charAt(i)) {
					cur = str.charAt(i);
					count++; 
				}
			}
			
			bw.write("#" + tc + " " + count + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}

}
