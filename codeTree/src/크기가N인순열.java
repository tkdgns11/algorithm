package src;

import java.io.*;
import java.util.*;

public class 크기가N인순열 {
    static int n;
    static boolean[] visited;
    static int[] numbers;
    static BufferedWriter bw;

    static void print() throws Exception {
        for(int i : numbers){
            bw.write(Integer.toString(i));
            bw.write(' ');
        }
        bw.write('\n');
    }

    static void dfs(int depth) throws Exception {
        if(depth == n) {
            print();
            return;
        }

        for(int i=1; i<=n; i++) {
            if(!visited[i]) {
                visited[i] = true;
                numbers[depth] = i;
                dfs(depth+1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        visited = new boolean[n+1]; // 0~n
        numbers = new int[n];
        dfs(0);
        bw.flush();
        bw.close();
    }
}