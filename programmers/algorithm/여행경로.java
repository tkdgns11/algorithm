package algorithm;

import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/43164
 */
public class 여행경로 {
	
	static boolean[] used;
	static int ticketCount;
	static String answer;
	
	public String[] solution(String[][] tickets) {
		
		ticketCount = tickets.length;
		used = new boolean[ticketCount];
		answer = null;
		
		dfs(tickets, "ICN", "ICN");
		
		return answer.split(" ");
	}
	
	static boolean dfs(String[][] tickets, String cur, String path) {
		
		if(path.split(" ").length == ticketCount + 1) {
			answer = path;
			return true;
		}
		
		// 현재 갈 수 있는 티켓을 도착지 알파벳 순으로 시도
		List<Integer> candidates = new ArrayList<>();
		for(int i=0; i<ticketCount; i++) {
			if(!used[i] && tickets[i][0].equals(cur)) candidates.add(i);
		}
		candidates.sort((a,b) -> tickets[a][1].compareTo(tickets[b][1]));
		
		for(int idx : candidates) {
			used[idx] = true;
			if(dfs(tickets, cur = tickets[idx][1], path + " " + tickets[idx][1])) return true;
			used[idx] = false;
		}
		return false;
	}
}
