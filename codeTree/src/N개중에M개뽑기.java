package src;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class N개중에M개뽑기 {
	static BufferedWriter bw;
	static int n, m;
	static boolean[] visited;
	static int[] numbers;
	
	static void dfs(int depth, int in) throws IOException {
		if(depth == m) {
			print();
			return;
		}
		
		for(int i=in; i<n; i++) {
			if(!visited[i]) {
				visited[i] = true;
				numbers[depth] = i+1;
				dfs(depth+1, i+1);
				visited[i] = false;
			}
		}
	}
	
	static void print() throws IOException {
		for(int i=0; i<numbers.length; i++) {
			bw.write(Integer.toString(numbers[i]));
			bw.write(' ');
		}
		bw.write('\n');
	}
	
	public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        // Please write your code here.
        
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        numbers = new int[m];
        visited = new boolean[n];
        
        dfs(0, 0);
        bw.flush();
        bw.close();
    }
}
