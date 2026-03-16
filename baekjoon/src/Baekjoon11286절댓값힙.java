package src;

import java.io.*;
import java.util.*;

public class Baekjoon11286절댓값힙 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 배열에서 절댓값이 가장 작은 값을 출력하고, 그 값을 배열에서 제거한다. 절댓값이 가장 작은 값이 여러개일 때는, 가장 작은 수를 출력하고, 그 값을 배열에서 제거한다.
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            // 절댓값이 다르면 → 절댓값 기준 오름차순 -3 4
            if (Math.abs(a) != Math.abs(b)) {
                return Math.abs(a) - Math.abs(b);
            }
            // 절댓값이 같으면 → 값 기준 오름차순 (음수가 먼저)
            return a - b;
        });

        int N = Integer.parseInt(br.readLine().trim());

        for(int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine().trim());

            if(num == 0) {
                if(pq.isEmpty()) {
                    bw.write("0\n");
                } else {
                    bw.write(pq.poll() + "\n");
                }
            } else {
                pq.offer(num);
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
