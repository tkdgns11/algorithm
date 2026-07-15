package algorithm;

import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42628
 * 트리맵
 * 
 * I 숫자	큐에 주어진 숫자를 삽입합니다.
   D 1	큐에서 최댓값을 삭제합니다.
   D -1	큐에서 최솟값을 삭제합니다.
 */
public class 이중우선순위큐 {

	public int[] solution(String[] operations) {
		
		TreeMap<Integer, Integer> map = new TreeMap<>();
		
		for(String op : operations) {
			String[] parts = op.split(" ");
			String cmd = parts[0];
			int num = Integer.parseInt(parts[1]);
			
			if(cmd.equals("I")) {
				map.put(num, map.getOrDefault(num, 0) + 1);
			} else {
				if(map.isEmpty()) continue;
				
				int key = (num == 1) ? map.lastKey() : map.firstKey();
				
				if(map.get(key) == 1) map.remove(key);
				else map.put(key, map.get(key) -1);
			}
		}
		if(map.isEmpty()) return new int[] {0,0};
		return new int[] {map.lastKey(), map.firstKey()};
	}
}
