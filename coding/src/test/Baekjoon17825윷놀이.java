package test;

import java.io.*;
import java.util.*;

class Baekjoon17825윷놀이 {

	static int totalScore; // 총점수

	static Mal[] malArr; // 말배열

	static class Mal {
		int malNumber; // 말번호
		int current; // 현재위치
		boolean arrival; // 도착여부

		Mal(int malNumber, int current, boolean arrival) {
			this.malNumber = malNumber;
			this.current = current;
			this.arrival = arrival;
		}
	}

	static class PanNode {
		int id; // 각각의 노드에 할당하는 id
		int myScore; // 점수
		int nextNode; // 다음 노드
		boolean blueArrowYn;
		int nextBlueNode; // 파랑인 경우 다음노드
		boolean isArrival; // 도착노드인 경우

		PanNode(int id, int myScore, int nextNode, boolean blueArrowYn, int nextBlueNode, boolean isArrival) {
			this.id = id;
			this.myScore = myScore;
			this.nextNode = nextNode;
			this.blueArrowYn = blueArrowYn;
			this.nextBlueNode = nextBlueNode;
			this.isArrival = isArrival;
		}
	}

	/**
	 * start 위치에서 dice만큼 이동한 최종 노드 인덱스를 반환
	 */
	static int move(int start, int dice, PanNode[] panArr) {
		int cur = start;
		boolean firstStep = true;

		for (int i = 0; i < dice; i++) {
			// 첫 칸 이동 전이고, 파란 화살이 있으면 nextBlueNode로
			if (firstStep && panArr[cur].blueArrowYn) {
				cur = panArr[cur].nextBlueNode;
			} else {
				// 첫 칸 이후 혹은 파란 화살이 없으면 일반 nextNode로
				cur = panArr[cur].nextNode;
			}

			firstStep = false;
			// 도착 지점이면 더 이상 못 움직이도록
			if (panArr[cur].isArrival)
				break;
		}
		return cur;
	}

	// isOccupied(nextPos, malArr)
	static boolean isOccupied(int nextPos, Mal[] malArr) {
		for (int i = 0; i < malArr.length; i++) {
			if (malArr[i].current == nextPos)
				return true;
		}
		return false;
	}

	/*
	 * 말이 이동을 마치는 칸에 다른 말이 있으면 그 말은 고를 수 없다. 단, 이동을 마치는 칸이 도착 칸이면 고를 수 있다. 
	 * 말이 이동을 마칠때마다 칸에 적혀있는 수가 점수에 추가된다.
	 * 이동 전 상태 저장 (current,arrival) → 이동 → 재귀 → 복원
	 */
	static void dfs(int turn, int sum, Mal[] malArr, PanNode[] panArr, int[] scoreArr) {
//	    if (turn == scoreArr.length) { // 10
//	        totalScore = Math.max(totalScore, sum);
//	        return;
//	    }
		
		 // 1) 들어올 때마다 최대 갱신
	    totalScore = Math.max(totalScore, sum);

	    // 2) 종료 조건: 
	    //   a) 모든 턴을 다 썼거나
	    if (turn == scoreArr.length) return; // 10

	    //   b) 말이 모두 도착한 경우
	    boolean allArrived = true;
	    for (Mal m : malArr) {
	        if (!m.arrival) { allArrived = false; break; }
	    }
	    if (allArrived) return;

	    int dice = scoreArr[turn];
	    
	    for (int h = 0; h < 4; h++) {
	        Mal horse = malArr[h];
	        if (horse.arrival) continue;

	        // 상태 백업
	        int prevPos   = horse.current;
	        boolean prevArr = horse.arrival;

	        // 1) 목표 위치 계산
	        int nextPos = move(prevPos, dice, panArr);
	        boolean arrival = panArr[nextPos].isArrival;

	        // 2) 도착이 아니면서 이미 말이 있으면 스킵
	        if (!arrival && isOccupied(nextPos, malArr)) {
	            continue;
	        }

	        // 3) 이동 확정 & 점수 계산
	        horse.current = nextPos;
	        horse.arrival = arrival;
	        int gained = arrival ? 0 : panArr[nextPos].myScore;

	        // 4) 다음 턴 탐색
	        dfs(turn + 1, sum + gained, malArr, panArr, scoreArr);

	        // 5) 상태 복원
	        horse.current = prevPos;
	        horse.arrival = prevArr;
	    }
	}


	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// ================== 판 초기화 시작 ==================
		// 1) 노드 ID 상수 정의
		final int START = 0;
		final int N2 = 1, N4 = 2, N6 = 3, N8 = 4;
		final int N10 = 5, N12 = 6, N14 = 7, N16 = 8, N18 = 9;
		final int N20 = 10, N22 = 11, N24 = 12, N26 = 13, N28 = 14;
		final int N30 = 15, N32 = 16, N34 = 17, N36 = 18, N38 = 19;
		final int N40 = 20;

		final int B10_13 = 21, B13_16 = 22, B16_19 = 23;
		final int B20_22 = 24, B22_24 = 25;
		final int B30_28 = 26, B28_27 = 27, B27_26 = 28;

		final int C25 = 29, C30 = 30, C35 = 31;
		final int END = 32;

		// 2) 배열 생성
		PanNode[] panArr = new PanNode[END + 1];

		// 3) 메인 트랙(0→2→4→…→40) 세팅
		panArr[START] = new PanNode(START, 0, N2, false, 0, false);
		panArr[N2] = new PanNode(N2, 2, N4, false, 0, false);
		panArr[N4] = new PanNode(N4, 4, N6, false, 0, false);
		panArr[N6] = new PanNode(N6, 6, N8, false, 0, false);
		panArr[N8] = new PanNode(N8, 8, N10, false, 0, false);

		// 10분기
		panArr[N10] = new PanNode(N10, 10, N12, true, B10_13, false);
		panArr[N12] = new PanNode(N12, 12, N14, false, 0, false);
		panArr[N14] = new PanNode(N14, 14, N16, false, 0, false);
		panArr[N16] = new PanNode(N16, 16, N18, false, 0, false);
		panArr[N18] = new PanNode(N18, 18, N20, false, 0, false);

		// 20분기
		panArr[N20] = new PanNode(N20, 20, N22, true, B20_22, false);
		panArr[N22] = new PanNode(N22, 22, N24, false, 0, false);
		panArr[N24] = new PanNode(N24, 24, N26, false, 0, false);

		// 30분기
		panArr[N26] = new PanNode(N26, 26, N28, false, 0, false);
		panArr[N28] = new PanNode(N28, 28, N30, false, 0, false);
		panArr[N30] = new PanNode(N30, 30, N32, true, B30_28, false);

		// 나머지 메인 트랙
		panArr[N32] = new PanNode(N32, 32, N34, false, 0, false);
		panArr[N34] = new PanNode(N34, 34, N36, false, 0, false);
		panArr[N36] = new PanNode(N36, 36, N38, false, 0, false);
		panArr[N38] = new PanNode(N38, 38, N40, false, 0, false);
		panArr[N40] = new PanNode(N40, 40, END, false, 0, false);

		// 10→13→16→19 분기 트랙
		panArr[B10_13] = new PanNode(B10_13, 13, B13_16, false, 0, false);
		panArr[B13_16] = new PanNode(B13_16, 16, B16_19, false, 0, false);
		panArr[B16_19] = new PanNode(B16_19, 19, C25, false, 0, false);

		// 20→22→24 분기 트랙
		panArr[B20_22] = new PanNode(B20_22, 22, B22_24, false, 0, false);
		panArr[B22_24] = new PanNode(B22_24, 24, C25, false, 0, false);

		// 30→28→27→26 분기 트랙
		panArr[B30_28] = new PanNode(B30_28, 28, B28_27, false, 0, false);
		panArr[B28_27] = new PanNode(B28_27, 27, B27_26, false, 0, false);
		panArr[B27_26] = new PanNode(B27_26, 26, C25, false, 0, false);

		// 중앙 합류 트랙 (25→30′→35)
		panArr[C25] = new PanNode(C25, 25, C30, false, 0, false);
		panArr[C30] = new PanNode(C30, 30, C35, false, 0, false);
		panArr[C35] = new PanNode(C35, 35, N40, false, 0, false);

		// 도착 노드
		panArr[END] = new PanNode(END, 0, END, false, 0, true);
		// ================== 판 초기화 끝 ==================

		totalScore = Integer.MIN_VALUE;

		int[] scoreArr = new int[10];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 10; i++) {
			scoreArr[i] = Integer.parseInt(st.nextToken());
		}

		malArr = new Mal[4];
		for (int i = 0; i < malArr.length; i++) {
			malArr[i] = new Mal(i, 0, false);
		}

		// 재귀 탐색 시작: 0번 턴부터, 점수 0에서 출발
		dfs(0, 0, malArr, panArr, scoreArr);

		bw.write(String.valueOf(totalScore));
		bw.flush();
		bw.close();
		br.close();
	}
}