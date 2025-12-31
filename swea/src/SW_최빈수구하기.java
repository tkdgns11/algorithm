package src;

import java.io.*;
import java.util.*;

/**
 * for문 2번.
 * “입력 1000개를 읽는 단계”와 “최빈값을 결정하는 단계”가 역할이 달라서
 * for문 하나로 줄여봤자, 더 느림. 비교 연산 더 많아서.. 1000 번 vs 100번.
 */
class SW_최빈수구하기 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine().trim());

        for (int test_case = 1; test_case <= T; test_case++) {
            int tcNo = Integer.parseInt(br.readLine().trim()); // 테스트 번호(입력), 없어도 됨

            int[] score = new int[101]; // 0~100
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < 1000; i++) {
                int s = Integer.parseInt(st.nextToken());
                score[s]++;
            }

            int maxCount = -1;
            int result = 0;

            for (int i = 0; i <= 100; i++) {
                if (score[i] >= maxCount) { // 동률이면 큰 점수
                    maxCount = score[i];
                    result = i;
                }
            }

            bw.write("#" + test_case + " " + result);
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
