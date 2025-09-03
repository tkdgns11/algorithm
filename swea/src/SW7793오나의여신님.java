package src;

import java.io.*;
import java.util.*;

public class SW7793오나의여신님 {

    static int N;
    static int M;

    static int si;
    static int sj;
    static int di;
    static int dj;

    // 상 하 좌 우 (row,col)
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            char[][] pan = new char[N][M];

            si = sj = di = dj = -1;

            for (int i = 0; i < N; i++) {
                String line = br.readLine().trim();
                for (int j = 0; j < M; j++) {
                    pan[i][j] = line.charAt(j);
                    if (pan[i][j] == 'S') {
                        si = i;
                        sj = j;
                    } else if (pan[i][j] == 'D') {
                        di = i;
                        dj = j;
                    }
                }
            }

            // -----------------------
            // 1. 악마 BFS
            // -----------------------
            Queue<int[]> devilQ = new LinkedList<>();
            int[][] devilTime = new int[N][M]; // 악마 도달 시간
            for (int i = 0; i < N; i++) {
                Arrays.fill(devilTime[i], Integer.MAX_VALUE);
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (pan[i][j] == '*') {
                        devilQ.offer(new int[]{i, j});
                        devilTime[i][j] = 0;
                    }
                }
            }

            while (!devilQ.isEmpty()) {
                int[] cur = devilQ.poll();
                int x = cur[0], y = cur[1];
                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];
                    if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                        if (pan[nx][ny] != 'X' && pan[nx][ny] != 'D'
                                && devilTime[nx][ny] == Integer.MAX_VALUE) {
                            devilTime[nx][ny] = devilTime[x][y] + 1;
                            devilQ.offer(new int[]{nx, ny});
                        }
                    }
                }
            }

            // -----------------------
            // 2. 수연 BFS
            // -----------------------
            Queue<int[]> q = new LinkedList<>();
            boolean[][] visited = new boolean[N][M];
            q.offer(new int[]{si, sj, 0}); // row, col, time
            visited[si][sj] = true;

            boolean success = false;

            while (!q.isEmpty()) {
                int[] cur = q.poll();
                int x = cur[0], y = cur[1], t = cur[2];

                if (x == di && y == dj) {
                    bw.write("#" + tc + " " + t + "\n");
                    success = true;
                    break;
                }

                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];
                    if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                        if (!visited[nx][ny] && pan[nx][ny] != 'X' && pan[nx][ny] != '*') {
                            // 수연이가 악마보다 먼저 도착해야만 가능
                            if (t + 1 < devilTime[nx][ny]) {
                                visited[nx][ny] = true;
                                q.offer(new int[]{nx, ny, t + 1});
                            }
                        }
                    }
                }
            }

            if (!success) {
                bw.write("#" + tc + " GAME OVER\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
