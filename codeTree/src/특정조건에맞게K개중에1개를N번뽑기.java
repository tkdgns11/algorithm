package src;
import java.io.*;
import java.util.*;

public class 특정조건에맞게K개중에1개를N번뽑기 {
	
	static int k,n;
	static int[] numbers;
	static BufferedWriter bw;
	
	static void print() throws IOException {
		int num = numbers[0];
		int count = 1;
		
		for(int i=1; i<n; i++) {
			if(num != numbers[i]) {
				num = numbers[i];
				count = 1;
			} else {
				count++;
			}
			if(count == 3) return;
		}
		
		for(int printNum : numbers) {
			bw.write(Integer.toString(printNum));
			bw.write(' ');
		}
		bw.write('\n');
	}
	
	static void dfs(int depth) throws IOException {
		if(depth == n) {
			print();
			return;
		}
		
		for(int i=1; i<=k; i++) {
			numbers[depth] = i;
			dfs(depth+1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Scanner sc = new Scanner(System.in);
        k = sc.nextInt();
        n = sc.nextInt();
        numbers = new int[n];
        dfs(0);
        bw.flush();
        bw.close();
    }
}
