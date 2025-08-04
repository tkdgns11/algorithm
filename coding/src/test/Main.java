package test;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter  bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());          // 보드 크기
        int K = Integer.parseInt(br.readLine());          // 사과 개수
        
        int[][] pan = new int[N+1][N+1];                  // 1~N 사용
        for(int i=0; i<K; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pan[r][c] = 1;                                // 사과 위치 표시
        }
        
        int L = Integer.parseInt(br.readLine());          // 방향 전환 횟수
        Map<Integer,Character> turn = new HashMap<>();
        for(int i=0; i<L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            char d = st.nextToken().charAt(0);           // 'L' or 'D'
            turn.put(t, d);
        }
        
        // 방향: 0=오른쪽,1=아래,2=왼쪽,3=위
        int[] dx = { 0, 1, 0, -1 };
        int[] dy = { 1, 0, -1, 0 };
        int dir = 0;                 // 처음엔 오른쪽
        
        Deque<int[]> snake = new ArrayDeque<>();
        boolean[][] snakeMap = new boolean[N+1][N+1];
        
        // 뱀 초기 위치 (1,1)
        snake.addLast(new int[]{1,1});
        snakeMap[1][1] = true;
        int headX = 1, headY = 1;
        
        int time = 0;
        while (true) {
            time++;
            int nx = headX + dx[dir];
            int ny = headY + dy[dir];
            
            // 1) 벽 충돌
            if (nx < 1 || nx > N || ny < 1 || ny > N) break;
            // 2) 자기 몸 충돌
            if (snakeMap[nx][ny]) break;
            
            // 3) 머리 이동
            snake.addFirst(new int[]{nx, ny});
            snakeMap[nx][ny] = true;
            
            // 4) 사과가 있으면 → 꼬리 고정(길이 증가)
            if (pan[nx][ny] == 1) {
                pan[nx][ny] = 0;
            }
            // 5) 사과 없으면 → 꼬리 하나 제거(길이 유지)
            else {
                int[] tail = snake.removeLast();
                snakeMap[tail[0]][tail[1]] = false;
            }
            
            headX = nx;
            headY = ny;
            
            // 6) 시간에 해당하면 방향 전환
            if (turn.containsKey(time)) {
                char c = turn.get(time);
                if (c == 'L') dir = (dir + 3) % 4;   // 왼쪽 90도
                else          dir = (dir + 1) % 4;   // 오른쪽 90도
            }
        }
        
        // 게임이 끝난 시각 출력
        bw.write(String.valueOf(time));
        bw.newLine();
        bw.flush();
        br.close();
    }
}
