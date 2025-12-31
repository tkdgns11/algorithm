package src;

import java.io.*;

/**
 * 높은 곳의 상자를 낮은 곳에 옮기는 방식으로 최고점과 최저점의 간격을 줄이는 작업을 평탄화라고 한다.
 * 시뮬레이션.
 * 덤프(가장 높은 곳에 있는 상자를 가장 낮은 곳으로 옮기는 작업)하면 됨.
 * 관심사가 위치는 상관없이 주어진 덤프 횟수만큼 수행했을때 최고점과 최저점의 높이차만 궁금하다.
 */
class SW_flatten {
    public static void main(String args[]) throws Exception {
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter  bw = new BufferedWriter(new OutputStreamWriter(System.out));
 
        final int T = 10;
        
        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine().trim()); // 덤프 횟수
            String[] line = br.readLine().split(" ");
            
            /**
             *   순서(왼쪽→오른쪽) 는 완전히 버리고
				"높이 h인 박스가 몇 개냐”만 세는 거.
             */
         // 1) 카운팅 배열 초기화 (0~100)
            int[] cnt = new int[101];
         // 가로 길이는 항상 100으로 주어진다. -> 100번 반복
            for (int i = 0; i < 100; i++) {
                cnt[Integer.parseInt(line[i])] ++;
            }
 
            // 2) lo, hi 초 포인터 잡기. 현재 최저 높이, 현재 최고 높이
            int lo = 0, hi = 100;
            while (cnt[lo] == 0)  lo++;
            while (cnt[hi] == 0)  hi--;
 
            // 3) N회 덤프: O(1) 연산
            for (int d = 0; d < N; d++) {
            	 if (hi - lo <= 1) break;
                // 최고에서 하나 내려놓기
                cnt[hi]--;
                cnt[hi-1]++;
                if (cnt[hi] == 0) hi--;
 
                // 최저에서 하나 올려놓기
                cnt[lo]--;
                cnt[lo+1]++;
                if (cnt[lo] == 0) lo++;
            }
 
            // 4) 결과 출력
            bw.write("#" + tc + " " + (hi - lo));
            bw.newLine();
        }
 
        bw.flush();
        br.close();
        bw.close();
    }
}