package src;

import java.io.*;
import java.util.*;

public class 소금쟁이_sol {
    // d: 1=상, 2=하, 3=좌, 4=우
    static final int[] di = {0, -1, 1, 0, 0};
    static final int[] dj = {0,  0, 0,-1, 1};
    static int N;

    static class Bug {
        int i, j, d;
        Bug(int i, int j, int d) { this.i=i; this.j=j; this.d=d; }
    }

    static boolean in(int i, int j){ return 0<=i && i<N && 0<=j && j<N; }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();

        for (int tc=1; tc<=T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            Bug[] bugs = new Bug[M];
            boolean[] alive = new boolean[M];
            Arrays.fill(alive, true);

            for (int i=0;i<M;i++){
                st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                bugs[i] = new Bug(r,c,d);
            }

            // 위에서부터(입력 순서대로) 처리
            for (int i=0;i<M;i++){
                if (!alive[i]) continue;

                int[] jump = {3,2,1};
                for (int step : jump){
                    int ni = bugs[i].i + di[bugs[i].d] * step;
                    int nj = bugs[i].j + dj[bugs[i].d] * step;

                    // 범위 밖 => 현재 벌레 죽음
                    if (!in(ni,nj)) { alive[i] = false; break; }

                    // (1) 먼저 뛴 벌레가 머무른 곳에 도달하면 => 현재 벌레 죽음
                    boolean diedByEarlier = false;
                    for (int p=0;p<i;p++){
                        if (alive[p] && bugs[p].i==ni && bugs[p].j==nj){
                            alive[i]=false; diedByEarlier=true; break;
                        }
                    }
                    if (diedByEarlier) break;

                    // (2) 먼저 뛴 벌레가 도착한 곳이 배치된(아직 안 뛴) 벌레의 위치라면 => 배치된 벌레가 죽음
                    for (int q=i+1;q<M;q++){
                        if (alive[q] && bugs[q].i==ni && bugs[q].j==nj){
                            alive[q]=false;
                        }
                    }

                    // 현재 벌레 위치 갱신
                    bugs[i].i = ni; bugs[i].j = nj;
                }
            }

            int cnt=0;
            for (boolean a: alive) if (a) cnt++;
            sb.append('#').append(tc).append(' ').append(cnt).append('\n');
        }
        System.out.print(sb);
    }
}
