package src;

import java.io.*;
import java.util.*;

public class SW균형점0903 {
	
	static List<Node> pan;
	static int N;
	static class Node {
		int p;
		int m;
		
		public Node(int positition, int m) {
			this.p = positition;
			this.m = m;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int tc=1; tc <= T; tc++ ) {
			N = Integer.parseInt(br.readLine().trim());
			
			pan = new ArrayList<>();
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int i=0; i<N; i++) {
				int p = Integer.parseInt(st.nextToken());
				pan.add(new Node(p, 0));
			}
			
			for(int i=0; i<N; i++) {
				int m = Integer.parseInt(st.nextToken());
				pan.get(i).m = m;
			}
			
			/**
			 * 균형점은 사이에 있다.
			 */
			for(int i=0; i<N-1; i++) {
				Node startNode = pan.get(i);
				Node endNode = pan.get(i+1);
				while(startNode.p < endNode.p) {
					
				}
			}
		}
	}
}
