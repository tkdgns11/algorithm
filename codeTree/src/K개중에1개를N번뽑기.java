package src;

import java.util.Scanner;
import java.io.*;

public class K개중에1개를N번뽑기 {

    static BufferedWriter bw;
    static int[] number;
    static int k;
    static int n;

    static void print() throws Exception {
        for(int i=0; i<n; i++) {
            bw.write(Integer.toString(number[i]));
            bw.write(' ');
        }
         bw.write("\n");
    }

    static void dfs(int depth) throws Exception {
        if(depth == n) {
            print();
            return;
        }

        for(int i=1; i<=k; i++) {
            number[depth] = i;
            dfs(depth+1);
        }
    }

    public static void main(String[] args) throws Exception {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Scanner sc = new Scanner(System.in);
        k = sc.nextInt();
        n = sc.nextInt();
        number = new int[n]; 
        dfs(0);
        bw.flush();
        bw.close();
    }
}