package algorithm;

import java.util.*;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42627
 * 
 */
public class 디스크컨트롤러 {
	
	public int solution(int[][] jobs) { // 작업 번호에 대해 [요청시점, 소요 시간]
		
		int n = jobs.length;
		
		Arrays.sort(jobs, (a,b) -> a[0] - b[0]); // 요청시점 오름차순
		
		PriorityQueue<int[]> pq = new PriorityQueue<>(
				// 소요시간도 같고, 요청시각도 같은 두 작업은 뭘 먼저 처리하든 결과는 같음. 전체 순서 출력 x
				(a,b) -> a[1] != b[1] ? a[1] - b[1] : a[0] - b[0]
		);
		
		int now = 0; // 현재 시각
		int i = 0; // 다음 job 인덱스
		int done = 0; // 처리 완료 개수
		int total = 0; // 반환시간 합
		
		while(done < n) {
			
			// 미래 작업 큐에 x.현재 now보다 먼 미래면 아예 큐에 안넣어서 작업될일 없게
			while(i<n && jobs[i][0] <= now) {
				pq.add(jobs[i]);
				i++;
			}
			
			if(pq.isEmpty()) {
				now = jobs[i][0]; // 다음 작업까지 시간 점프
			} else {
				int[] job = pq.poll();
				now += job[1]; // 소요시간 더하기
				total += now - job[0]; // 반환 시각 += 현재시각 - 요청시각 = 대기시각 + 작업시각
				done++;
			}
 		}
		return total / n;
	}
}
