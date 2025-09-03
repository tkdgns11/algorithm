package src;

import java.io.*;
import java.util.*;

public class SW7730나무깎는홍준 {

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            int N; // 나무의 수
            int M; // 원하는 원목 길이

            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            int[] namus = new int[N];

            st = new StringTokenizer(br.readLine());

            int max = 0;
            for (int i = 0; i < N; i++) {
                namus[i] = Integer.parseInt(st.nextToken());
                max = Math.max(max, namus[i]); // 최댓값 찾기
            }

            int left = 0, right = max;
            int answer = 0;

            while (left <= right) {
                int mid = (left + right) / 2;

                long sum = 0;
                for (int h : namus) {
                    if (h > mid) sum += (h - mid);
                }

                if (sum >= M) {
                    // 원하는 길이를 만족하면 더 높이 자를 수 있음
                    answer = mid;
                    left = mid + 1;
                } else {
                    // 원하는 길이를 못 채우면 더 낮게 잘라야 함
                    right = mid - 1;
                }
            }

            bw.write("#" + tc + " " + answer + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
