package test;

import java.io.*;
import java.util.*;

public class Baekjoon10158개미 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int w = Integer.parseInt(st.nextToken());

		int h = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());

		int p = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());

		int t = Integer.parseInt(br.readLine());
		
		int x = t % (2 * w);
		int y = t % (2 * h);
		
		int dx = 1;
		int dy = 1;
		
		while(x > 0) {
			p += dx;
			if(p == w || p == 0) {
				dx *= -1;
			}
			x--;
		}
		
		while(y > 0) {
			q += dy;
			if(q == h || q == 0) {
				dy *= -1;
			}
			y--;
		}
		
		bw.write(p + " " + q);
		bw.flush();
		bw.close();
		br.close();
	}
}
