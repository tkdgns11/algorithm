package src;

import java.io.*;
import java.util.*;

/**
 * 토큰이 한줄에 다 안들어오는 경우 처리
 */
public class SWGNS0908_count {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {

			StringTokenizer st = new StringTokenizer(br.readLine());

			st.nextToken();
			int N = Integer.parseInt(st.nextToken());

			int[] pan = new int[10]; // 0~9

			st = null;

			int read = 0;

			while (read < N) {
				if (st == null || !st.hasMoreTokens()) { // 토큰 떨어지면 다음 줄 보충
					st = new StringTokenizer(br.readLine());
					continue;
				}
				String str = st.nextToken();
				switch (str) {
				case "ZRO":
					pan[0]++;
					break;
				case "ONE":
					pan[1]++;
					break;
				case "TWO":
					pan[2]++;
					break;
				case "THR":
					pan[3]++;
					break;
				case "FOR":
					pan[4]++;
					break;
				case "FIV":
					pan[5]++;
					break;
				case "SIX":
					pan[6]++;
					break;
				case "SVN":
					pan[7]++;
					break;
				case "EGT":      
					pan[8]++;
					break;
				case "NIN":
					pan[9]++;
					break;
				}
				read++;
			}
			bw.write("#");
			bw.write(Integer.toString(tc));
			bw.write("\n");

			for (int i = 0; i < 10; i++) {
				if (pan[i] > 0) {
					for (int j = 0; j < pan[i]; j++) {
						switch (i) {
						case 0:
							bw.write("ZRO");
							break;
						case 1:
							bw.write("ONE");
							break;
						case 2:
							bw.write("TWO");
							break;
						case 3:
							bw.write("THR");
							break;
						case 4:
							bw.write("FOR");
							break;
						case 5:
							bw.write("FIV");
							break;
						case 6:
							bw.write("SIX");
							break;
						case 7:
							bw.write("SVN");
							break;
						case 8:
							bw.write("EGT");
							break;
						case 9:
							bw.write("NIN");
							break;
						}
						bw.write(' ');
					}
				}
			}
			bw.write("\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
