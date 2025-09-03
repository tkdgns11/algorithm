package src;

import java.io.*;
import java.util.*;

public class SW14510나무높이 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		/**
		 * N개의 나무가 있다. 초기의 각 나무의 키가 주어진다. 하루에 한 나무에 물을 줄 수 있다. 
		 * 첫 날은 물을 준 나무의 키가 1 자라고, 둘째 날은 물을 준 나무의 키가 2 자라고, 셋째 날은 물을 준 나무의 키가 1 자라는 식으로, 
		 * 홀수 번째 날은 키가 1 자라고 짝수 번째 날은 키가 2 자란다. 
		 * 모든 나무의 키가 처음에 가장 키가 컸던 나무와 같아지도록 할 수 있는 최소 날짜 수를 계산하라. 
		 * 어떤 날에는 물을 주는 것을 하지 않을 수도 있다.
		 * 
           예를 들어 나무가 2그루이고 각각의 높이가 4와 2라고 하자. 
           첫째 날에 물을 주게 되면, 나무의 높이를 모두 4로 만들기 위해서는 3일째까지 물을 주어야 한다. 
           둘째 날은 아무 일도 안 하게 된다. 하지만, 첫째 날을 쉬고 둘째 날에 물을 주면 2일 만에 나무의 높이가 모두 4가 된다.

           N 제한 100, 나무 높이 최대 120
        */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int tc=1; tc<=T; tc++) {
			int N = Integer.parseInt(br.readLine().trim());
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int pan[] = new int[N];
			
			int Max = Integer.MIN_VALUE;
			
			for(int i=0; i<N; i++) {
				pan[i] = Integer.parseInt(st.nextToken());
				Max = Math.max(Max, pan[i]);
			}
			
			for(int i=0; i<N; i++) {
				pan[i] = Max - pan[i];
			}
			
			int days = 0;
			int count1 = 0;
			int count2 = 0;
			
			for(int i=0; i<N; i++) {
				days += (pan[i]/3) * 2;
				pan[i] = pan[i] % 3;
				if(pan[i] == 1) count1++;
				if(pan[i] == 2) count2++;
			}
			
			
			
			int gyoCount = Math.min(count1, count2);
			
			days += gyoCount * 2;
			count1 -= gyoCount;
			count2 -= gyoCount;
			
			if(count1 == 0 && count2 == 0) {
				// 처리 끝
			} else if(count1 > 0) {
				//1만 남음
				//2일에 하나씩 처리.
				if(count1 == 1) {
					if(days % 2 == 1) {
						days += 2;
					} else {
						days += 1;
					}
				} else {
					if(days % 2 == 1) {
						days += (count1 * 2) + 1;
					} else {
						days += (count1 * 2) - 1;
					}
				}
			} else {
				// 2만 남음
				// 2일에 3씩. 4일에 6.
				if(count2 >= 3) {
					days += (count2 / 3) * 4;
					count2 %= 3;
				}
				if(count2 == 1) {
					if(days % 2 == 1) {
						days += 1;
					} else {
						days += 2;
					}
				} else if (count2 == 2) {
				    if (days % 2 == 1) {   // 다음 날이 짝수날
				        days += 4;         // 2개 처리 최소 4일
				    } else {               // 다음 날이 홀수날
				        days += 3;         // 홀(1), 짝(2), 홀(1)로 3일에 가능
				    }
				}
			}
			bw.write("#" + tc + " " + days+ "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
