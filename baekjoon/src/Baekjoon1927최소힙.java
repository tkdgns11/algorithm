package src;

import java.io.*;
import java.util.*;

public class Baekjoon1927최소힙 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        int N = Integer.parseInt(br.readLine().trim());

        for(int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine().trim());

            if(num == 0) {
                if(minHeap.isEmpty()) {
                    bw.write("0\n");
                } else {
                    bw.write(minHeap.poll() + "\n");
                }
            } else {
                minHeap.offer(num);
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
