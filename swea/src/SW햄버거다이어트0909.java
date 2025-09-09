package src;

import java.io.*;
import java.util.*;

public class SW햄버거다이어트0909 {

	static int N;
	static int maxCal;
	static int maxScore;
	static Food[] food;
	
	static class Food {
		int score;
		int cal;
		
		public Food(int score, int cal) {
			super();
			this.score = score;
			this.cal = cal;
		}
	}
	
	static void dfs(int depth, int cal, int score) {
		if(depth == N) {
			if(maxCal > cal) {
				maxScore = Math.max(maxScore, score);
			}
			return;
		}
		
		if(cal > maxCal) {
			return;
		}
		//선택 o
		dfs(depth+1, cal + food[depth].cal, score + food[depth].score);
		
		//선택 x
		dfs(depth+1, cal, score);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			maxCal = Integer.parseInt(st.nextToken());
			
			food = new Food[N];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				int score = Integer.parseInt(st.nextToken());
				int cal = Integer.parseInt(st.nextToken());
				
				food[i] = new Food(score, cal);
			}
			
			maxScore = Integer.MIN_VALUE;
			dfs(0, 0, 0);
			bw.write('#');
			bw.write(Integer.toString(tc));
			bw.write(' ');
			bw.write(Integer.toString(maxScore));
			bw.write('\n');
		}
		bw.flush();
		bw.close();
		br.close();
	}
}