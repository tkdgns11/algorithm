package test;

import java.io.*;
import java.util.*;

public class Baekjoon1920수찾기 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine().trim());
        
        int[] pan = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++) {
        	pan[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(pan);
        
        int M = Integer.parseInt(br.readLine().trim());

        st = new StringTokenizer(br.readLine());
        
        for(int i=0; i<M; i++) {
        	if(Arrays.binarySearch(pan, Integer.parseInt(st.nextToken())) >= 0) {
        		bw.write(1 +"\n");
        	} else {
        		bw.write(0 +"\n");
        	}
        }
        bw.flush();
        bw.close();
        br.close();
	}

}
