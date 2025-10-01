package src;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class 가장오래걸리는학생2_sol {
	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        List<int[]>[] pan = new ArrayList[n];
        for (int i = 0; i < n; i++) pan[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            int d = sc.nextInt();
            pan[b].add(new int[]{a, d});
        }

    	int[] cost;
        int result = Integer.MIN_VALUE;

        cost = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[n - 1] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[] {n - 1, 0});

        while(!pq.isEmpty()) {
            int[] node = pq.poll();
            if(node[1] > cost[node[0]]) continue;

            for (int[] e : pan[node[0]]) {
                int ii = e[0];
                int w  = e[1];
                int calc = node[1] + w;
                if (cost[ii] > calc) {
                    cost[ii] = calc;
                    pq.add(new int[]{ii, calc});
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (cost[i] != Integer.MAX_VALUE) {
                result = Math.max(result, cost[i]);
            }
        }
        System.out.println(result);
    }
}
