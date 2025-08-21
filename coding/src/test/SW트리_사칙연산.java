package test;

import java.io.*;
import java.util.*;

public class SW트리_사칙연산 {
	static int N;
	static int[] tree;

	static int postOrder(int node) {
		if(node*2 > N) {
			return tree[node];
		}
		
		int lRes = postOrder(node*2);
		int rRes = postOrder(node*2 + 1);
		
		if(tree[node] == '+') return lRes + rRes;
		else if(tree[node] == '-') return lRes - rRes;
		else if(tree[node] == '*') return lRes * rRes;
		else if(tree[node] == '/') return lRes / rRes;
		return 0;
	}

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = 10;

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine().trim()); // 정점의 개수
			
			tree = new int[N+1];
			
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				int num1 = Integer.parseInt(st.nextToken());
				
				String str2 = st.nextToken();
				
				char typeCheck = str2.charAt(0);
				
				if(typeCheck == '+' || typeCheck == '-' || typeCheck == '*' || typeCheck == '/'  ) {
					tree[num1] = typeCheck;
				} else {
					tree[num1] = Integer.parseInt(str2);
				}
			}
			
			int result = postOrder(1); // 루트 정점의 번호는 항상 1
			
			bw.write("#" + t + " " + result);
			bw.write("\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
