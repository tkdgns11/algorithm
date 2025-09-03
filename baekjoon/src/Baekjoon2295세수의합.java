package src;

import java.io.*;
import java.util.*;

public class Baekjoon2295세수의합 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine().trim());
        
        int[] pan = new int[N];
        
        for(int i=0; i<N; i++) {
        	pan[i] = Integer.parseInt(br.readLine().trim());
        }
	}

}
