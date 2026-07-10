package algorithm;

import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42626
 */
public class 더맵게 {

	public int solution(int[] scoville, int K) {

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int s : scoville) pq.add(s);

		int count = 0;

		while(pq.peek() < K) {

			if(pq.size() < 2) return -1;

			int first = pq.poll(); // 가장 작은 값
			int second = pq.poll(); // 두 번째로 작은 값
			pq.add(first + second * 2); // 섞어서 다시 넣기

			count++;
		}
		return count;
	}
}
