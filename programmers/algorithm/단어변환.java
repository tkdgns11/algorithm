package algorithm;

import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/43163
 */
public class 단어변환 {
	
	public int solution(String begin, String target, String[] words) {

		int n = words.length;
		boolean[] visited = new boolean[n];

		Queue<Object[]> queue = new LinkedList<>();
		queue.add(new Object[] {begin, 0});

		while(!queue.isEmpty()) {
			Object[] cur = queue.poll();
			String word = (String) cur[0];
			int step = (int) cur[1];

			if(word.equals(target)) return step;

			for(int i=0; i<n; i++) {
				if(!visited[i] && canChange(word, words[i])) {
					visited[i] = true;
					queue.add(new Object[]{words[i], step + 1});
				}
			}
		}
		return 0;
	}

	static boolean canChange(String a, String b) {
		int diff = 0;
		for(int i=0; i<a.length(); i++) {
			if(a.charAt(i) != b.charAt(i)) diff++;
			if(diff > 1) return false;
		}
		return diff == 1;
	}
}
