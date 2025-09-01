package test;

import java.io.*;
import java.util.*;

/**
 * BOJ 17135 - 캐슬 디펜스
 * 전략:
 * 1) M개 열 중 궁수 3명 위치 조합 모두 생성
 * 2) 각 조합에 대해 보드 복사 후 시뮬레이션
 *    - 매 턴: 각 궁수가 BFS로 사정거리 D 내 "가장 가까운" 적 탐색 (동일거리면 더 왼쪽)
 *    - 3명의 타겟을 Set으로 모아 "동시에" 제거 (중복 타겟은 1회만 카운트)
 *    - 적들을 한 칸 아래로 이동
 * 3) 최대 처치 수 출력
 *
 * BFS 팁: 시작을 (N-1, archerCol)에서 dist=1로 두고, 탐색 방향은 {왼, 위, 오른} 순서로 하면
 *         동일 거리일 때 "왼쪽 우선" 규칙이 자연스럽게 충족.
 */
public class Baekjoon17135캐슬디펜스 {

    static int N, M, D;
    static int[][] origin;         // 원본 보드 (N x M)
    static final int[] dr = {0, -1, 0};   // 왼, 위, 오른
    static final int[] dc = {-1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력: 첫 줄 "N M D"
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        origin = new int[N][M];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                origin[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solve());
    }

    static int solve() {
        int maxKill = 0;

        // 궁수 3명 조합 (열 인덱스 0..M-1)
        for (int i = 0; i < M - 2; i++) {
            for (int j = i + 1; j < M - 1; j++) {
                for (int k = j + 1; k < M; k++) {
                    int[] archers = {i, j, k};
                    maxKill = Math.max(maxKill, simulate(archers));
                }
            }
        }
        return maxKill;
    }

    static int simulate(int[] archers) {
        // 보드 복사
        int[][] board = new int[N][M];
        for (int r = 0; r < N; r++) {
            board[r] = Arrays.copyOf(origin[r], M);
        }

        int kills = 0;

        // 최대 N턴: 적들이 N번 내려오면 보드에서 사라짐
        for (int turn = 0; turn < N; turn++) {
            // 1) 각 궁수 타겟 선정 (BFS)
            Set<Long> targets = new HashSet<>(); // (r<<16) | c 로 좌표 직렬화
            for (int col : archers) {
                int[] t = findTarget(board, col);
                if (t != null) {
                    long key = (((long) t[0]) << 16) | (t[1] & 0xFFFF);
                    targets.add(key);
                }
            }

            // 2) 동시 제거
            for (long key : targets) {
                int r = (int) (key >> 16);
                int c = (int) (key & 0xFFFF);
                if (board[r][c] == 1) {
                    board[r][c] = 0;
                    kills++;
                }
            }

            // 3) 적 한 칸 아래로 이동
            moveDown(board);

            // 적이 없으면 조기 종료 가능(선택)
            if (isEmpty(board)) break;
        }
        return kills;
    }

    /**
     * 궁수(행 = N, 열 = archerCol) 기준 BFS로 사정거리 D 내 최우선 타겟 찾기
     * 시작점을 (N-1, archerCol) / dist=1 로 두고, 방향은 왼-위-오른.
     * 반환: {r, c} 또는 null
     */
    static int[] findTarget(int[][] board, int archerCol) {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];

        // 시작 후보: 바로 위 칸
        if (N - 1 >= 0) {
            q.offer(new int[]{N - 1, archerCol, 1});
            if (in(N - 1, archerCol)) visited[N - 1][archerCol] = true;
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1], d = cur[2];

            if (!in(r, c)) continue;
            if (d > D) continue;

            if (board[r][c] == 1) {
                return new int[]{r, c};
            }

            // 왼, 위, 오른 순 → 동일 거리일 때 왼쪽 우선
            for (int dir = 0; dir < 3; dir++) {
                int nr = r + dr[dir];
                int nc = c + dc[dir];
                int nd = d + 1;
                if (in(nr, nc) && !visited[nr][nc] && nd <= D) {
                    visited[nr][nc] = true;
                    q.offer(new int[]{nr, nc, nd});
                }
            }
        }
        return null;
    }

    static boolean in(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    static void moveDown(int[][] board) {
        for (int r = N - 1; r >= 1; r--) {
            board[r] = board[r - 1];
        }
        Arrays.fill(board[0], 0);
    }

    static boolean isEmpty(int[][] board) {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (board[r][c] == 1) return false;
            }
        }
        return true;
    }
}
