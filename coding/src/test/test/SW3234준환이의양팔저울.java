package test;

import java.util.*;
import java.io.*;

/**
 * N개의 서로 다른 무게를 가진 무게 추
 * 각 추를 양팔저울의 왼쪽에 올릴 것인지 오른쪽에 올릴 것인지
 * 오른쪽 위에 올라가 있는 무게의 총합이 왼쪽에 올라가 있는 무게의 총합보다 더 커져서는 안 된다. -> 왼쪽목록, 오른쪽 목록 넘기기
 */
public class SW3234준환이의양팔저울 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc<= T; tc++) {
			int N = Integer.parseInt(br.readLine().trim());
		}
	}
}