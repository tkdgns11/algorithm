package src;

import java.io.*;
import java.util.*;

/**
 * 매트릭스 체인 알고리즘으로 하면 훨씬 빠름. -> 공식이 있음
 */
public class SW화학물질2 {

	static int N;
	static int[][] pan;
	static List<int[]> list;
	static List<int[]> list2;
	static boolean[] visited;
	static int[] number;
	static int minCalc;
	static boolean findResult;
	static Map<String, Integer> memo;

	static void dfs(int depth, List<int[]> arr, int sum) {
		if (arr.size() == 1) {
			minCalc = Math.min(minCalc, sum);
			if(!findResult) findResult = true;
			return;
		}
		
		if(findResult && sum > minCalc) return;
		
		String key = makeKey(arr);
	    Integer best = memo.get(key);
	    if (best != null && best <= sum) return;
	    memo.put(key, sum);

		for (int i = 1; i < arr.size(); i++) {
			List<int[]> copy = new ArrayList<>();
			
			int[] prev = arr.get(i-1);
			int[] cur = arr.get(i);
			
			int[] newNode = new int[] {prev[0], cur[1]};
			
			int calcSum = prev[1] * prev[0] * cur[1];
			
			for(int ii=0; ii<arr.size(); ii++) {
				int[] origin = arr.get(ii);
				int[] arrCopy = new int[2];
				System.arraycopy(origin, 0, arrCopy, 0, 2);
				copy.add(arrCopy);
			}
			
			copy.remove(i-1);
			copy.remove(i-1);
			copy.add(i-1, newNode);
			dfs(depth-1, copy, sum + calcSum);
		}
	}
	
	static String makeKey(List<int[]> arr) {
	    StringBuilder sb = new StringBuilder(arr.size() * 8);
	    for (int[] rc : arr) {
	        sb.append(rc[0]).append(',').append(rc[1]).append(';'); // "r,c;..."
	    }
	    return sb.toString();
	}

	static void checkBox(int colCount, int startI, int j) {
		int rowCount = 0;
		for (int i = startI; i <= N; i++) {
			if (i == N || pan[i][j - 1] == 0) {
				list.add(new int[] { rowCount, colCount });
				return;
			} else {
				rowCount++;
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());

			pan = new int[N][N];
			list = new ArrayList<>();

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					pan[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < N; i++) {
				boolean visit = false;
				int count = 0;
				for (int j = 0; j < N; j++) {
					if ((1 <= pan[i][j] && pan[i][j] <= 9) && ((i == 0) || (i != 0 && pan[i - 1][j] == 0))) {
						if (!visit)
							visit = true;
						count++;
					} else if (pan[i][j] == 0) {
						if (visit) {
							checkBox(count, i, j);
							visit = false;
							count = 0;
						}
					} 
					
					if (j == N - 1) {
						if (visit) {
							checkBox(count, i, j);
							visit = false;
							count = 0;
						}
					}
				}
			}
			
			int index = -1;
			outer : for(int i=0; i<list.size(); i++) {
				int[] value = list.get(i);
				for(int j=0; j<list.size(); j++) {
					int[] value2 = list.get(j);
					if(value[0] == value2[1]) continue outer;
				}
				index = i;
			}
			
			list2 = new ArrayList<>();
			list2.add(list.get(index));
			
			for(int i=0; i<list.size(); i++) {
				int[] tmp = list.get(index);
				for(int j=0; j<list.size(); j++) {
					if(tmp[1] == list.get(j)[0]) {
						list2.add(list.get(j));
						index = j;
						break;
					}
				}
			}
			
			visited = new boolean[list.size()];
			number = new int[list.size()];
			minCalc = Integer.MAX_VALUE;
			findResult = false;
			memo = new HashMap<>();
			
			dfs(list.size(), list2, 0);
			
			bw.write('#');
			bw.write(Integer.toString(tc));
			bw.write(' ');
			bw.write(Integer.toString(minCalc));
			bw.write('\n');
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
