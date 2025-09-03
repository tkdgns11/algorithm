package src;

import java.io.*;
import java.util.*;

public class Baekjoon10814나이순정렬 {
	
	static class Node implements Comparable<Node> {

		int age;
		String name;
		int reg;
		
		@Override
		public int compareTo(Node o) {
			if (this.age != o.age) {
				return this.age - o.age;
			}
			return this.reg - o.reg;	
		}
		
		Node(int age, String name, int reg){
			this.age = age;
			this.name = name;
			this.reg = reg;
		}
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
		int N = Integer.parseInt(br.readLine().trim());
	
		Node[] arr = new Node[N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i] = new Node(Integer.parseInt(st.nextToken()), st.nextToken(), i);
		}
		
		Arrays.sort(arr);
		
		for(Node i : arr) {
			bw.write("" + i.age + " " + i.name + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}