package src;

import java.io.*;
import java.util.*;

/**
 * 토큰이 한줄에 다 안들어오는 경우 처리
 */
public class SWGNS0908 {

	static class Node implements Comparable<Node> {
		String str;
		int value;

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.value, o.value);
		}

		public Node(String str, int value) {
			super();
			this.str = str;
			this.value = value;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {

			StringTokenizer st = new StringTokenizer(br.readLine());

			st.nextToken();
			int N = Integer.parseInt(st.nextToken());

			PriorityQueue<Node> q = new PriorityQueue<>();

			st = null;
			
			int read = 0;
			
			while (read < N) {
			    if (st == null || !st.hasMoreTokens()) { // 토큰 떨어지면 다음 줄 보충
			        st = new StringTokenizer(br.readLine());
			        continue;
			    }
			    String str = st.nextToken();
			    switch (str) {
			        case "ZRO": q.add(new Node("ZRO", 0)); break;
			        case "ONE": q.add(new Node("ONE", 1)); break;
			        case "TWO": q.add(new Node("TWO", 2)); break;
			        case "THR": q.add(new Node("THR", 3)); break;
			        case "FOR": q.add(new Node("FOR", 4)); break;
			        case "FIV": q.add(new Node("FIV", 5)); break;
			        case "SIX": q.add(new Node("SIX", 6)); break;
			        case "SVN": q.add(new Node("SVN", 7)); break;
			        case "EGT": q.add(new Node("EGT", 8)); break;
			        case "NIN": q.add(new Node("NIN", 9)); break;
			    }
			    read++;
			}
			bw.write("#");
			bw.write(Integer.toString(tc));
			bw.write("\n");
			
			while(!q.isEmpty()) {
				Node node = q.poll();
				bw.write(node.str);
				bw.write(' ');
			}
			bw.write("\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
