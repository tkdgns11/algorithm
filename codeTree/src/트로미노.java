package src;

import java.util.Scanner;

/**
 * 강사님 풀이. 이 문제 전용. 중심점 잡고 중심점부터 옮기기.
 */
public class 트로미노 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] grid = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        int[] dx = new int[] {-1, 0, 1, 0};
        int[] dy = new int[] {0, -1, 0, 1};

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k1 = 0; k1 < 4; k1++) {
                    for (int k2 = k1 + 1; k2 < 4; k2++) {
                        int x1 = i + dx[k1];
                        int y1 = j + dy[k1];
                        int x2 = i + dx[k2];
                        int y2 = j + dy[k2];

                        int v1 = (0 <= x1 && x1 < n && 0 <= y1 && y1 < m ? grid[x1][y1] : 0);
                        int v2 = (0 <= x2 && x2 < n && 0 <= y2 && y2 < m ? grid[x2][y2] : 0);
                        
                        answer = Math.max(answer, grid[i][j] + v1 + v2);
                    }
                }
            }
        }

        System.out.println(answer);
    }
}
