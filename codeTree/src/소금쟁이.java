package src;

import java.io.*;
import java.util.*;

/**
 * 1. 위에서부터 순서대로 뜀. 만약 소금쟁이가 뛰던 중 먼저 뛴 소금쟁이가 머무른 곳에 다다르는 경우, 뛰던 소금쟁이는 죽는다. 만약 먼저
 * 뛴 소금쟁이가 다다른 장소가 다른 소금쟁이가 배치된 장소라면, 배치된 소금쟁이가 죽는다.
 */
public class 소금쟁이 {

	// 상 하 좌 우
	static final int[] di = { -1, -1, 1, 0, 0 };
	static final int[] dj = { -1, 0, 0, -1, 1 };
	static int N;

	static class Bug {
		int i;
		int j;
		int d;
		int order;

		public Bug(int i, int j, int d, int order) {
			super();
			this.i = i;
			this.j = j;
			this.d = d;
			this.order = order;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + Objects.hash(d, i, j, order);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Bug other = (Bug) obj;
			return d == other.d && i == other.i && j == other.j;
		}

		public boolean myOrder(int order) {
			return order == this.order;
		}
	}

	static boolean inRange(int i, int j) {
		return i >= 0 && i < N && j >= 0 && j < N;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine().trim());
		
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			List<Bug> list = new ArrayList<>();

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int row = Integer.parseInt(st.nextToken());
				int col = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				list.add(new Bug(row, col, dir, i));
			}
			//List<Bug> removeList;
			/**
			 * 버그 하나 선택, 3번 뛰면서 로직처리.
			 */
			//int moveBugIdx = 0;
			Bug bug = null;
			for (int i = 0; i < M; i++) {
				Bug tmp = list.get(i);
				if (tmp != null && tmp.myOrder(i)) {
					bug = tmp;
					break;
				}
				if (bug == null) continue;
				for (int n = 0; n < 3; n++) {
					//removeList = new ArrayList<>();
					int ni = 0, nj = 0;
					if (n == 0) {
						ni = bug.i + di[bug.d] * 3;
						nj = bug.j + dj[bug.d] * 3;
					} else if (n == 1) {
						ni = bug.i + di[bug.d] * 2;
						nj = bug.j + dj[bug.d] * 2;
					} else {
						ni = bug.i + di[bug.d];
						nj = bug.j + dj[bug.d];
					}

					if (!inRange(ni, nj)) {
						// 밖으로 뜀
						list.remove(bug);
						continue;
					} else {
						// 배치 버그
						for (int j = 0; j < M; j++) {
							if (i < j) {
								Bug batchBug = null;
								for (int i1 = 0; i1 < list.size(); i1++) {
									Bug tmp1 = list.get(i1);
									if (tmp1.myOrder(j)) {
										batchBug = tmp1;
										break;
									}
								}
								if (batchBug != null && ni == batchBug.i && nj == batchBug.j) {
									//removeList.add(batchBug);
									list.remove(batchBug);
								}
							} else if (j < i) {
								// 3칸 이동후 머무르는 버그
								Bug stayBug = null;
								for (int i1 = 0; i1 < list.size(); i1++) {
									Bug tmp1 = list.get(i1);
									if (tmp1.myOrder(j)) {
										stayBug = tmp1;
										break;
									}
								}
								if (stayBug != null && ni == stayBug.i && nj == stayBug.j) {
									//removeList.add(bug);
									list.remove(bug);
									continue;
								}
							}
						}
						bug.i = ni;
						bug.j = nj;
					}
//					for (int r = 0; r < removeList.size(); r++) {
//						list.remove(removeList.get(r));
//					}
				}
			}
			//moveBugIdx++;
			bw.write("#" + tc + " " + list.size());
			bw.write('\n');
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
