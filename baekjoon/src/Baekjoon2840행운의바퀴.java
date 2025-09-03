package src;

import java.io.*;
import java.util.*;

/**
 * 바퀴에 같은 글자는 두 번 이상 등장하지 않는다. 또, 바퀴는 시계방향으로만 돌아간다.
 */
public class Baekjoon2840행운의바퀴 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 바퀴 칸 수
		int K = Integer.parseInt(st.nextToken()); // 바퀴 돌리는 횟수

		char[] arr = new char[N];

		int curIdx = 0;
		boolean bulga = false;
		
		boolean[] used = new boolean[26];

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			char target = st.nextToken().charAt(0);
			
			s %= N;

			// curIdx = (curIdx + s) % N;
			if (curIdx + s > N - 1) {
				curIdx = s - (N - curIdx);
			} else {
				curIdx += s;
			}

			if ((int) arr[curIdx] == 0) {
				if(used[(int)(target-'A')]) {
					bulga = true;
					break;
				}
				arr[curIdx] = target;
			} else if (arr[curIdx] != target) {
				bulga = true;
				break;
			}
			
			used[(int)(target-'A')] = true;
		}

		if (bulga) {
			bw.write("!");
		} else {
			/**
			 * for (int i = 0; i < N; i++) {
				    int idx = (curIdx - i + N) % N;
				    bw.write(arr[idx] == 0 ? "?" : arr[idx]);
				}

			 */
			for (int i = curIdx; i >= 0; i--) {
				bw.write("" + ((int) (arr[i]) == 0 ? "?" : arr[i]));
			}
			for(int i = N-1; i>curIdx; i--) {
				bw.write("" + ((int) (arr[i]) == 0 ? "?" : arr[i]));
			}
		}
		bw.flush();
		bw.close();
		br.close();
	}

}
