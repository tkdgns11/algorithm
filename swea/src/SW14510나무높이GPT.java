package src;

import java.io.*;
import java.util.*;

public class SW14510나무높이GPT {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine().trim());
            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] h = new int[N];
            int max = 0;
            for (int i = 0; i < N; i++) {
                h[i] = Integer.parseInt(st.nextToken());
                if (h[i] > max) max = h[i];
            }

            // 전체 필요량을 (+1, +2) 카운트로 분해: diff = 2*twos + ones
            int ones = 0;  // 남은 +1의 총 개수
            int twos = 0;  // 남은 +2의 총 개수
            for (int i = 0; i < N; i++) {
                int diff = max - h[i];
                twos += diff / 2;
                ones += diff % 2;
            }

            // 두 가지 시작 케이스 중 최소:
            // - 홀수날부터 시작
            // - 첫날 쉬고(=짝수날부터 시작) 진행 → 초기 쉬는 하루를 +1 해줌
            int dOddStart  = simulate(ones, twos, true);   // day1 = 홀수
            int dEvenStart = 1 + simulate(ones, twos, false); // day1 쉬고 day2=짝수부터

            int ans = Math.min(dOddStart, dEvenStart);
            bw.write("#" + tc + " " + ans + "\n");
        }
        bw.flush();
    }

    /**
     * ones, twos가 남아있을 때 최소 일수 시뮬레이션.
     * startOdd=true  → 첫 날이 홀수(+1일)
     * startOdd=false → 첫 날이 짝수(+2일)
     *
     * 규칙:
     *  - 홀수날: ones>0 이면 ones--; 그렇지 않으면 twos>0을 1개 분해(= twos--, ones++) 후 오늘 1 소비(→ 결과적으로 ones 그대로)
     *  - 짝수날: twos>0 이면 twos--; 없으면 스킵(아무 것도 못함)
     */
    private static int simulate(int ones0, int twos0, boolean startOdd) {
        int ones = ones0, twos = twos0;
        int days = 0;
        boolean odd = startOdd;

        while (ones > 0 || twos > 0) {
            days++;
            if (odd) {
                if (ones > 0) {
                    ones--;                 // 홀수날 +1 소모
                } else if (twos > 0) {
                    // +2 하나를 (1+1)로 분해해서 오늘 1, 다음에 1
                    twos--;
                    // 분해로 +1이 두 개 생겼는데 오늘 1을 바로 써버리면
                    // 남는 +1은 1개이므로 ones++
                    ones++;
                } // 둘 다 0이면 루프가 끝났을 테니 도달하지 않음
            } else {
                if (twos > 0) {
                    twos--;                 // 짝수날 +2 소모
                } // twos==0이면 스킵(아무 것도 못함)
            }
            odd = !odd; // 날짜 홀/짝 전환
        }
        return days;
    }
}
