package src;

import java.io.*;
import java.util.*;

public class SW5521상원이의생일파티 {

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine().trim());
        
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            
            List<Integer>[] pan = new ArrayList[N+1];
            for (int i = 1; i <= N; i++) {
                pan[i] = new ArrayList<>();
            }
            
            boolean[] visited = new boolean[N+1];
            
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine().trim());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                
                pan[s].add(e);
                pan[e].add(s);
            }
            
            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[]{1, 0}); // 여기서 왜 [1,0] 이렇게 넣었는지.. ? 아, depth
            visited[1] = true;
            
            int count = 0;
            
            while (!q.isEmpty()) {
                int[] cur = q.poll();
                int person = cur[0];
                int depth = cur[1];
                
                if (depth > 0 && depth <= 2) {
                    count++;
                }
                
                if (depth == 2) continue;
                
                for (int next : pan[person]) {
                    if (!visited[next]) {
                        visited[next] = true;
                        q.offer(new int[]{next, depth + 1});
                    }
                }
            }
            bw.write("#" + tc + " " + count + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
