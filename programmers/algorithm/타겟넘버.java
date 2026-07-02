package algorithm;

// https://school.programmers.co.kr/learn/courses/30/lessons/43165?language=java
public class 타겟넘버 {
	
	static int[] numbers;
	static int target;
	static int answer;
	
	public int solution(int[] numbers, int target) {
		this.numbers = numbers;
		this.target = target;
		answer = 0;
		
		dfs(0,0);
		
		return answer;
	}
	
	// index : 지금 고를 숫자, sum : 지금까지 누적합 
	static void dfs(int index, int sum) {
		if(index == numbers.length) {
			if(sum == target) answer++;
			return;
		}
		
		dfs(index+1, sum + numbers[index]);
		dfs(index+1, sum - numbers[index]);
	}
}
