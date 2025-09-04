package algo2;
import java.util.*;

/**
 * 광물 운송 - 캠프 그룹핑/검색
 * - 거리 L 이하 캠프는 같은 그룹(DSU)으로 묶기
 * - 그룹 대표(리더): amount 오름차순 → row → col
 * - 대표 객체에 그룹 총량(groupAmount)을 유지
 * - addBaseCamp: 새 캠프 추가 + 주변 그룹과 병합 후, 최종 그룹 총량 반환
 * - findBaseCampForDropping(K): 총량 ≥ K 인 그룹 중 대표 우선순위 최솟값의 대표 id 반환 (없으면 -1)
 */
class UserSolution {

    /** 캠프 노드 (DSU 원소) */
    static class Camp implements Comparable<Camp> {
        int id;       // 캠프 식별자
        int row, col; // 좌표
        int amount;   // 해당 캠프의 보유량(대표 선정 기준)

        Camp parent;  // DSU 부모 (대표는 자기 자신을 가리킴)
        int groupAmount = 0; // (대표에게만 유효) 그룹 총 보유량

        public Camp(int id, int row, int col, int amount) {
            this.id = id;
            this.row = row;
            this.col = col;
            this.amount = amount;
            // parent는 addBaseCamp 병합 단계에서 설정됨 (대표는 자신을 가리키도록)
        }

        /** 대표 우선순위: amount → row → col (오름차순) */
        @Override
        public int compareTo(Camp o) {
            if (this.amount == o.amount) {
                return (this.row == o.row) ? (this.col - o.col) : (this.row - o.row);
            }
            return this.amount - o.amount;
        }
    }

    int L, N;              // 결합 기준 거리, 공간 크기(상한)
    int maxLength;         // 버킷 최대 인덱스 = N / L

    // 현재 "대표 노드"들의 모음 (List로 유지: 원본 유지)
    List<Camp> groupLeaders;

    // L 크기 격자 버킷: 각 버킷에 해당 좌표의 캠프들을 보관
    @SuppressWarnings("unchecked")
    List<Camp>[][] camps = new ArrayList[31][31]; // 문제 전제: maxLength ≤ 30

    /** 초기화 */
    void init(int L, int N) {
        this.L = L;
        this.N = N;
        this.maxLength = N / L; // 버킷 인덱스 상한

        groupLeaders = new ArrayList<>();
        for (int r = 0; r <= maxLength; ++r) {
            for (int c = 0; c <= maxLength; ++c) {
                camps[r][c] = new ArrayList<>();
            }
        }
    }

    /**
     * 캠프 추가 + 주변 그룹 병합
     * @return 병합 완료 후 최종 대표의 그룹 총보유량
     */
    int addBaseCamp(int mID, int mRow, int mCol, int mQuantity) {
        Camp camp = new Camp(mID, mRow, mCol, mQuantity);

        // 버킷 인덱스
        int rowIdx = mRow / L;
        int colIdx = mCol / L;

        // 인접 그룹 대표들(중복 방지)을 모아두는 집합
        HashSet<Camp> prevLeaders = new HashSet<>();

        // 새로 형성될 그룹의 총 보유량 누적
        int groupAmount = mQuantity;

        // 우선 대표 후보는 "새 캠프" 자신
        Camp groupLeader = camp;

        // 주변 3x3 버킷을 훑으며 거리 L 이하의 기존 캠프들을 조사
        for (int br = rowIdx - 1; br <= rowIdx + 1; ++br) {
            for (int bc = colIdx - 1; bc <= colIdx + 1; ++bc) {
                if (br < 0 || br > maxLength || bc < 0 || bc > maxLength) continue;

                for (Camp near : camps[br][bc]) {
                    int dist = Math.abs(camp.row - near.row) + Math.abs(camp.col - near.col);
                    if (dist > L) continue; // 버킷은 후보일 뿐, 최종 판정은 거리 L로

                    Camp leader = getParent(near); // near가 속한 그룹의 대표
                    if (!prevLeaders.add(leader)) continue; // 같은 대표는 한 번만

                    // 기존 대표는 더 이상 대표가 아닐 가능성이 있으므로 목록에서 제거
                    groupLeaders.remove(leader);

                    // 새로 형성될 그룹 총량 누적 (대표의 groupAmount를 신뢰)
                    groupAmount += leader.groupAmount;

                    // 대표 선정: amount → row → col (오름차순)
                    if (groupLeader.compareTo(leader) > 0) {
                        groupLeader = leader;
                    }
                }
            }
        }

        // 자기 자신도 "병합 후보 집합"에 넣어야 대표/부모 설정이 일관됨
        prevLeaders.add(camp);

        // 모든 후보들의 parent를 최종 대표로 연결 (DSU union)
        for (Camp leader : prevLeaders) {
            leader.parent = groupLeader; // 대표 자신이면 자기 자신을 가리키게 됨
        }

        // 버킷에 새 캠프 등록
        camps[rowIdx][colIdx].add(camp);

        // 대표에 최종 그룹 총량 기록
        groupLeader.groupAmount = groupAmount;

        // 대표 목록 갱신(최종 대표만 다시 추가)
        groupLeaders.add(groupLeader);

        // 결과: 그룹 총 보유량
        return groupLeader.groupAmount;
    }

    /**
     * 총 보유량이 K 이상인 그룹들 중
     * 대표 우선순위(=amount,row,col)가 가장 작은 대표의 id 반환. 없으면 -1
     */
    int findBaseCampForDropping(int K) {
        // "가장 큰 값"으로 초기화된 가짜 후보 (amount=MAX_VALUE → 아무나 이길 수 있게)
        Camp best = new Camp(-1, -1, -1, Integer.MAX_VALUE);

        for (Camp leader : groupLeaders) {
            // leader는 항상 "대표"이며, groupAmount가 유효
            if (leader.groupAmount >= K && best.compareTo(leader) > 0) {
                best = leader;
            }
        }
        return best.id; // 없으면 -1 반환(초기값 그대로)
    }

    /** DSU - 경로 압축 find (null 안전 가드 추가) */
    private Camp getParent(Camp x) {
        if (x == null) return null;       // 방어적: 이 케이스는 정상 흐름에선 발생하지 않음
        if (x.parent == null) return x;   // 방어적: parent 미설정 시 자신을 대표로 간주
        if (x.parent == x) return x;      // 대표 노드

        // 경로 압축
        x.parent = getParent(x.parent);
        return x.parent;
    }
}
