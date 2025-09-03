package src;

import java.io.*;
import java.util.*;

public class Baekjoon7785회사에있는사람 {

	static class Node implements Comparable<Node> {
		String name;
		String value;

		public Node(String name, String value) {
			this.name = name;
			this.value = value;
		}

		@Override
		public int compareTo(Node o) {
			if (!this.name.equals(o.name)) {
				return o.name.compareTo(this.name);
			}
			return this.value.compareTo(o.value);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());

		Node[] arr = new Node[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i] = new Node(st.nextToken(), st.nextToken());
		}

		Arrays.sort(arr);

		for (int i = 0; i < arr.length; i++) {
			if (arr[i].value.equals("enter")) {
				if(i < arr.length-1) {
					if(!arr[i].name.equals(arr[i+1].name)) {
						bw.write(arr[i].name + "\n");
					}
				} else {
					bw.write(arr[i].name + "\n");
				}
			}
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
