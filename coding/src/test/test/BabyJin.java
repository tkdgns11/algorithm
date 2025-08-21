package test;

import java.util.*;
import java.io.*;

/**
 * 6자리의 숫자를 입력받아 babyjin인지 여부를 판단. 3장의 숫자가 같으면 triplet, 3장의 카드가 연속이면 run
 */
public class BabyJin {

	static int[] input;
	static boolean isFind;
	static int[] numbers;
	static boolean[] visited;
	
	static boolean isTripletOrRun(int x, int y, int z) {
		if(x == y && y == z) return true;
		
		int[] checkArr = new int[] {x, y, z};
		
		Arrays.sort(checkArr);
		
		return (checkArr[1] == checkArr[0] + 1) && (checkArr[2] == checkArr[1] + 1);
	} 
	
	static void dfs(int depth) {
		if(isFind) return; // 이미 찾음 -> 조기 종료 
		
		if(depth == 6) {
			isFind = isTripletOrRun(numbers[0], numbers[1], numbers[2]) 
					&& isTripletOrRun(numbers[3], numbers[4], numbers[5]);
			return;
		}
			
		Set<Integer> usedThisDepth = new HashSet<>();
		
		for(int i=0; i<input.length; i++) {
			if(!usedThisDepth.add(input[i])) continue; // 처음 넣는 값이면 true 반환 (그리고 집합에 추가됨) 이미 들어있던 값이면 false 반환 (집합은 그대로)
			if(!visited[i]) {
				visited[i] = true;
				numbers[depth] = input[i]; // numbers[depth] 에 추가
				dfs(depth + 1);
				if (isFind) return; // 찾은경우 형제노드도 빠르게 조기종료 
				visited[i] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String str = br.readLine().trim();

		input = new int[6];
		visited = new boolean[6];
		numbers = new int[6];

		for (int i = 0; i < input.length; i++) {
			input[i] = Integer.valueOf(str.charAt(i) - '0');
		}
		
		dfs(0);
		
		bw.write("" + (isFind ? "babyJin O" : "babyJin X"));
		bw.flush();
		bw.close();
		br.close();
	}
}
