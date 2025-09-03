package src;

import java.util.Scanner;

public class SW나무높이_sol {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test = 1; test <= T; test++) {
            int N = sc.nextInt();
            int[] tree = new int[N];

            for (int i = 0; i < N; i++) {
                tree[i] = sc.nextInt();
            }

            int max = 0;
            for (int i = 0; i < N; i++) {
                if (max < tree[i]) max = tree[i];
            }

            // --- 여기부터 수정 핵심 ---
            int sum = 0;       // 총 필요한 성장량 S
            int oddCnt = 0;    // +1이 꼭 1번은 필요한 나무 수(필요량이 홀수인 나무 수)

            for (int i = 0; i < N; i++) {
                int diff = max - tree[i];
                sum += diff;
                if ((diff & 1) == 1) oddCnt++;
            }

            // ceil(2*sum/3) = (2*sum + 2)/3  (정수 나눗셈)
            int needByAmount = (2 * sum + 2) / 3;
            //22222 남아도 1 2 1 2 이런식으로 넣기에 2인곳에 1넣고
            /***
             * 2인곳에 2 넣고 다시 앞에 1 넣었던곳에 다시 1넣고 다른 2에 2넣고 
             * 할수 있어서 그냥 1.5씩 계산
             * 그러면 마지막에 넣는게 1만 넣어도되는건지 마지막에 2만 넣어도 되는건지만 파악
             * 
             * 그렇게하면 최소 홀수가 몇개인지 파악
             * or
             * 1.5로 짝수개 파악
             * 둘중에 큰거만 취함
             */
            int needByOdds   = (oddCnt > 0) ? (2 * oddCnt - 1) : 0;

            int day = Math.max(needByAmount, needByOdds);
            System.out.println("#"+test+" "+day);
            // --- 수정 끝 ---
        }
    }
}
