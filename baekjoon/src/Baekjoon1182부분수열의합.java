package src;

import java.io.*;
import java.util.*;

class Baekjoon1182부분수열의합 {
    static int N, S;
    static int[] pan;     // 입력 수열
    static long answer;   // 부분수열 개수 (최대 2^20 ≈ 10^6, int도 충분하지만 안전하게 long)
    static BufferedWriter bw;

    /**
     * DFS
     * @param idx     현재 고려 중인 인덱스 (0..N)
     * @param sum     지금까지 선택한 원소들의 합
     * @param selCnt  지금까지 선택한 원소 개수 (공집합 제외를 위해 필요)
     */
    static void dfs(int idx, int sum, int selCnt) {
        if (idx == N) {
            if (selCnt > 0 && sum == S) {
                answer++;
            }
            return;
        }

        // 1) 현재 원소를 "선택하지 않음"
        dfs(idx + 1, sum, selCnt);

        // 2) 현재 원소를 "선택함"
        dfs(idx + 1, sum + pan[idx], selCnt + 1);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        pan = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) pan[i] = Integer.parseInt(st.nextToken());

        answer = 0;
        dfs(0, 0, 0);

        bw.write(String.valueOf(answer));
        bw.write("\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
