package src;

import java.io.*;
import java.util.*;

/**
 * 유니온 파인드
 */
public class SW15861같은팀 {

    static int[] parent;

    // find: 팀의 대표자 찾기
    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    // union: 두 팀 합치기
    static void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px != py) parent[py] = px;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine().trim());

            parent = new int[N + 1];
            for (int i = 0; i <= N; i++) {
                parent[i] = i;
            }

            int[][] order = new int[N][3];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 3; j++) {
                    order[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int yesCount = 0;

            for (int i = 0; i < N; i++) {
                if (order[i][0] == 1) {
                    union(order[i][1], order[i][2]);
                } else { // 2번 명령
                    if (find(order[i][1]) == find(order[i][2])) yesCount++;
                }
            }

            bw.write("#" + tc + " " + yesCount + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
