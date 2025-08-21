package test;

import java.util.*;
import java.io.*;

/**
 * 키가 작은 아이부터. 반 아이들은 항상 20명 같은 키를 가진 학생은 없다 선택정렬
 */
public class Baekjoon10431줄세우기 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {

			StringTokenizer st = new StringTokenizer(br.readLine());

			Integer.parseInt(st.nextToken());

			int[] pan = new int[20];

			for (int i = 0; i < pan.length; i++) {
				pan[i] = Integer.parseInt(st.nextToken());
			}

			int[] check = new int[20];

			check[0] = pan[0]; // 아무나 한명 세우기

			int backCount = 0; // 뒤로 물러난 수

			int checkCount = 1; // 체크 배열에 들어있는 수

			for (int i = 1; i < pan.length; i++) {
//				for (int k = 0; k < pan.length; k++) {
//					System.out.print(check[k] +  " "  );
//				}
//				System.out.println();
				
				int num = pan[i];
				boolean find = false;
				for (int j = 0; j < checkCount; j++) {
					if (num < check[j]) {
						System.arraycopy(check, j, check, j + 1, checkCount - j);
						check[j] = num;
						backCount += (checkCount - j);
						find = true;
						break;
					}
				}
				if (!find) {
					check[checkCount] = num;
				}
				checkCount++;
			}

			bw.write("" + tc + " " + backCount);
			bw.write("\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}

}
