package test;

import java.util.*;
import java.io.*;

public class SW모의_벌꿀채취_sol {

    static int N, M, C;
    static int[][] pan;

    static int MaxBenefit;      // 정답
    static int[][] best;        // best[i][j]: (i,j) 시작 길이 M 구간의 최대 이익 (없으면 -1)
    static final int UNVIS = -1;

    // --- 한 구간(길이 M)에 대한 백트래킹 + 메모이제이션 ---
    static int[] seg;           // 현재 평가 중인 구간 값들 (길이 M)
    static int[][] dp;          // dp[idx][sum] = idx부터, 현재 합 sum에서 가능한 최대 제곱합

    static int dfs(int idx, int sum) {
        if (idx == M) return 0;
        if (dp[idx][sum] != UNVIS) return dp[idx][sum];

        // 1) 이번 원소 안 고름
        int res = dfs(idx + 1, sum);

        // 2) 이번 원소 고름 (용량 체크)
        int v = seg[idx];
        if (sum + v <= C) {
            res = Math.max(res, v * v + dfs(idx + 1, sum + v));
        }
        return dp[idx][sum] = res;
    }

    // (i,j) 시작 구간의 최대 이익을 on-demand로 캐싱해서 반환
    static int bestAt(int i, int j) {
        if (j > N - M) return 0;        // 구간 불가
        if (best[i][j] != UNVIS) return best[i][j];

        seg = new int[M];
        for (int k = 0; k < M; k++) seg[k] = pan[i][j + k];

        dp = new int[M + 1][C + 1];
        for (int a = 0; a <= M; a++) Arrays.fill(dp[a], UNVIS);

        return best[i][j] = dfs(0, 0);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            pan = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    pan[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            MaxBenefit = 0;
            best = new int[N][N];
            for (int i = 0; i < N; i++) Arrays.fill(best[i], UNVIS);

            // 바깥 구조 유지: (i,j) 구간 하나 잡고, 같은 행 오른쪽/아래 행 모든 구간과 합치기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j <= N - M; j++) {
                    int b1 = bestAt(i, j);

                    // 같은 행 오른쪽 구간들 (겹치지 않게 j2 >= j+M)
                    int rowGap = 0;
                    while (j + M + rowGap <= N - M) {   // j2 = j+M+rowGap
                        int j2 = j + M + rowGap;
                        int b2 = bestAt(i, j2);
                        MaxBenefit = Math.max(MaxBenefit, b1 + b2);
                        rowGap++;
                    }

                    // 아래 행의 모든 구간 (행 다르면 겹침 X)
                    int colGap = 1;
                    while (i + colGap < N) {
                        int j2 = 0;
                        while (j2 <= N - M) {
                            int b2 = bestAt(i + colGap, j2);
                            MaxBenefit = Math.max(MaxBenefit, b1 + b2);
                            j2++;
                        }
                        colGap++;
                    }
                }
            }

            bw.write("#" + tc + " " + MaxBenefit + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
