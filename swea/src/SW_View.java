package src;

import java.io.*;
import java.util.*;

public class SW_View {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int T = 10;

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine().trim());
            int[] b = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) b[i] = Integer.parseInt(st.nextToken());

            int ans = 0;

            // 양 옆 2칸이 존재하는 구간만 체크
            for (int i = 2; i <= N - 3; i++) {
                int neighborMax = Math.max(
                        Math.max(b[i - 2], b[i - 1]),
                        Math.max(b[i + 1], b[i + 2])
                );

                if (b[i] > neighborMax) ans += (b[i] - neighborMax);
            }

            bw.write("#" + tc + " " + ans);
            bw.write('\n');
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
