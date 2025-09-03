package src;
import java.util.*;
import java.io.*;


public class SW1486장훈이의높은선반 {
	static int height;
	static int N;
	static int B;
	static int[] input;
	static boolean[] visited;
	static boolean find;
	
	static void check(int result) {
		if(result >= B) {
			height = Math.min(height, result);
			find = true;
		}
	}
	
	static void dfs(int depth, int heightCalc) {
		if(depth >= N) return;
		System.out.println("진행중");
		if(find && height <= heightCalc) return;
		check(heightCalc);
		
		for(int i=0; i<input.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				dfs(depth+1, heightCalc + input[i]);
				visited[i] = false;
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			input = new int[N];
			
			visited = new boolean[N];

			st = new StringTokenizer(br.readLine());
			
			int tmp = 0;
			while(st.hasMoreTokens()) {
				input[tmp] = Integer.parseInt(st.nextToken());
				tmp++;
			}
			
			height = Integer.MAX_VALUE;
			find = false;
			dfs(0, 0);
			
			bw.write("#" + tc + " " + (height - B));
			bw.write("\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
