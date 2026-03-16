package src;

import java.io.*;
import java.util.*;

public class Baekjoon11279최대힙 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        int N = Integer.parseInt(br.readLine().trim());

        for(int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine().trim());

            if(num == 0) {
                if(maxHeap.isEmpty()) {
                    bw.write("0\n");
                } else {
                    bw.write(maxHeap.poll() + "\n");
                }
            } else {
                maxHeap.offer(num);
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
