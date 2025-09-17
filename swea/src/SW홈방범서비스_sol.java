package src;

import java.io.*;
import java.util.*;

public class SW홈방범서비스_sol {

    static int N, M;
    static int[][] pan;

    static int cost(int K) {
        return K * K + (K - 1) * (K - 1);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            pan = new int[N][N];

            int totalHouses = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    pan[i][j] = Integer.parseInt(st.nextToken());
                    if (pan[i][j] == 1) totalHouses++;
                }
            }

            int answer = 0;

            for (int ci = 0; ci < N; ci++) {
                for (int cj = 0; cj < N; cj++) {

                    int cnt = 0; // 현재 중심에서 K를 늘리며 누적되는 집 개수

                    for (int K = 1; K <= N + 1; K++) {
                        int r = K - 1;

                        if (K == 1) {
                            if (pan[ci][cj] == 1) cnt++;
                        } else {
                            // 맨해튼 거리 == r 인 "링"만 추가
                            // |di| + |dj| = r
                            for (int di = -r; di <= r; di++) {
                                int dj = r - Math.abs(di);

                                int i1 = ci + di;
                                int j1 = cj + dj;
                                if (i1 >= 0 && i1 < N && j1 >= 0 && j1 < N) {
                                    if (pan[i1][j1] == 1) cnt++;
                                }

                                // dj가 0이면 위에서 이미 추가했으므로 중복 방지
                                if (dj != 0) {
                                    int i2 = ci + di;
                                    int j2 = cj - dj;
                                    if (i2 >= 0 && i2 < N && j2 >= 0 && j2 < N) {
                                        if (pan[i2][j2] == 1) cnt++;
                                    }
                                }
                            }
                        }
                        int c = cost(K);
                        if (cnt == totalHouses) break; 
                        if (cnt * M >= c && cnt > answer) answer = cnt;
                    }
                }
            }
            out.append('#').append(tc).append(' ').append(answer).append('\n');
        }
        System.out.print(out.toString());
    }
}
