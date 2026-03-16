package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 도로 정보를 담는 클래스
class Road implements Comparable<Road> {
    int leftLampId;   // 도로의 왼쪽 가로등 ID
    int rightLampId;  // 도로의 오른쪽 가로등 ID
    int length;       // 도로의 길이
    int stPos;        // 도로의 시작 위치 (왼쪽 가로등 위치)

    public Road(int leftLampId, int rightLampId, int length, int stPos) {
        this.leftLampId = leftLampId;
        this.rightLampId = rightLampId;
        this.length = length;
        this.stPos = stPos;
    }

    // 최대 힙으로 사용하기 위한 비교 기준 정의
    @Override
    public int compareTo(Road other) {
        // 길이가 같으면 시작 위치가 작은 도로를 우선
        if (this.length != other.length) {
            return Integer.compare(other.length, this.length);
        }
        // 길이가 긴 도로를 우선
        return Integer.compare(this.stPos, other.stPos);
    }
}

public class 가로등설치 {
    // 전역 변수 선언
    static int N;
    // 각 가로등의 위치 (ID -> 위치), -1은 제거됨을 의미
    static ArrayList<Integer> lampPos;
    // 각 가로등의 다음/이전 가로등 ID (이중 연결 리스트 구현)
    static ArrayList<Integer> nextLampId;
    static ArrayList<Integer> prevLampId;
    // 도로 정보를 저장할 최대 힙
    static PriorityQueue<Road> roads;
    // 가로등 위치를 저장할 최소 힙 (가장 왼쪽 가로등 탐색용)
    static PriorityQueue<int[]> lampPosMinHeap;
    // 가로등 위치를 저장할 최대 힙 (가장 오른쪽 가로등 탐색용)
    static PriorityQueue<int[]> lampPosMaxHeap;

    // 지연 갱신을 적용하여 가장 오른쪽에 있는 유효한 가로등 위치를 찾는 함수
    public static int getMaxPosLamp() {
        while (!lampPosMaxHeap.isEmpty()) {
            int[] top = lampPosMaxHeap.peek();
            int pos = top[0];
            int lampId = top[1];
            // 힙에서 꺼낸 위치가 현재 lamp_pos에 저장된 실제 위치와 일치하는지 확인
            // 일치하지 않으면, 이미 제거된 가로등의 오래된 정보이므로 힙에서 제거
            if (lampPos.get(lampId) == pos) {
                break;
            }
            lampPosMaxHeap.poll();
        }
        return lampPosMaxHeap.peek()[0];
    }

    // 지연 갱신을 적용하여 가장 왼쪽에 있는 유효한 가로등 위치를 찾는 함수
    public static int getMinPosLamp() {
        while (!lampPosMinHeap.isEmpty()) {
            int[] top = lampPosMinHeap.peek();
            int pos = top[0];
            int lampId = top[1];
            // 힙에서 꺼낸 위치가 현재 lamp_pos에 저장된 실제 위치와 일치하는지 확인
            if (lampPos.get(lampId) == pos) {
                break;
            }
            lampPosMinHeap.poll();
        }
        return lampPosMinHeap.peek()[0];
    }

    // 지연 갱신을 적용하여 가장 긴 유효한 도로를 찾는 함수
    public static Road getMaxRoad() {
        while (!roads.isEmpty()) {
            Road road = roads.peek();
            int leftLampId = road.leftLampId;
            int rightLampId = road.rightLampId;
            int length = road.length;
            int stPos = road.stPos;

            // 힙에서 꺼낸 도로 정보가 현재 가로등 위치와 일치하는지 확인
            // 즉, 이 도로를 구성하는 두 가로등이 여전히 인접해 있는지 검사
            if (lampPos.get(leftLampId) == stPos && lampPos.get(rightLampId) == stPos + length) {
                break;
            }
            roads.poll();
        }
        return roads.peek();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int Q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int q = 0; q < Q; q++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();

            switch (cmd) {
                // 100: 마을 상태 확인 (초기화)
                case "100": {
                    N = Integer.parseInt(st.nextToken());
                    int M = Integer.parseInt(st.nextToken());

                    // 자료구조 초기화
                    lampPos = new ArrayList<>();
                    nextLampId = new ArrayList<>();
                    prevLampId = new ArrayList<>();
                    // 1-based 인덱싱을 위해 0번 인덱스에 더미 값 추가
                    lampPos.add(-1);
                    nextLampId.add(-1);
                    prevLampId.add(-1);

                    roads = new PriorityQueue<>();
                    lampPosMinHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
                    lampPosMaxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b[0], a[0]));

                    // 초기 가로등 정보 입력 및 설정
                    for (int i = 1; i <= M; i++) {
                        int pos = Integer.parseInt(st.nextToken());
                        lampPos.add(pos);
                        nextLampId.add(i + 1);
                        prevLampId.add(i - 1);

                        lampPosMinHeap.add(new int[]{pos, i});
                        lampPosMaxHeap.add(new int[]{pos, i});

                        // 인접한 가로등 사이의 도로 정보를 roads 힙에 추가
                        if (i > 1) {
                            int prevPos = lampPos.get(i - 1);
                            int length = pos - prevPos;
                            roads.add(new Road(i - 1, i, length, prevPos));
                        }
                    }
                    // 마지막 가로등의 next와 첫 가로등의 prev를 -1로 설정
                    nextLampId.set(M, -1);
                    prevLampId.set(1, -1);
                    break;
                }
                // 200: 가로등 추가
                case "200": {
                    // 가장 긴 도로를 찾아 그 중간에 가로등을 추가
                    Road road = getMaxRoad();
                    roads.poll();

                    // 새 가로등 위치 계산 (중간, 올림 처리)
                    int newPos = road.stPos + (road.length + 1) / 2;
                    int newLampId = lampPos.size();

                    // 새 가로등 정보를 자료구조에 추가
                    lampPos.add(newPos);
                    prevLampId.add(road.leftLampId);
                    nextLampId.add(road.rightLampId);

                    // 이중 연결 리스트 정보 갱신
                    nextLampId.set(road.leftLampId, newLampId);
                    prevLampId.set(road.rightLampId, newLampId);

                    lampPosMinHeap.add(new int[]{newPos, newLampId});
                    lampPosMaxHeap.add(new int[]{newPos, newLampId});

                    // 기존 도로가 2개의 새로운 도로로 나뉘었으므로, roads 힙에 추가
                    int length1 = newPos - road.stPos;
                    int length2 = road.stPos + road.length - newPos;
                    roads.add(new Road(road.leftLampId, newLampId, length1, road.stPos));
                    roads.add(new Road(newLampId, road.rightLampId, length2, newPos));
                    break;
                }
                // 300: 가로등 제거
                case "300": {
                    int targetId = Integer.parseInt(st.nextToken());
                    // lampPos를 -1로 설정하여 제거되었음을 표시 (지연 갱신용)
                    lampPos.set(targetId, -1);

                    int leftLampId = prevLampId.get(targetId);
                    int rightLampId = nextLampId.get(targetId);

                    // 이중 연결 리스트에서 제거된 가로등의 연결을 끊음
                    if (leftLampId != -1) {
                        nextLampId.set(leftLampId, rightLampId);
                    }
                    if (rightLampId != -1) {
                        prevLampId.set(rightLampId, leftLampId);
                    }

                    // 제거된 가로등의 양옆 가로등이 모두 존재하면, 두 가로등을 잇는 새로운 도로가 생김
                    if (leftLampId != -1 && rightLampId != -1) {
                        int leftPos = lampPos.get(leftLampId);
                        int rightPos = lampPos.get(rightLampId);
                        int length = rightPos - leftPos;
                        roads.add(new Road(leftLampId, rightLampId, length, leftPos));
                    }
                    break;
                }
                // 400: 최소 전력 계산
                case "400": {
                    // 각 힙에서 필요한 값들을 가져옴 (지연 갱신 처리 포함)
                    int maxPos = getMaxPosLamp();
                    int minPos = getMinPosLamp();
                    Road road = getMaxRoad();

                    // 최소 전력 * 2 를 계산하여 출력
                    // max(가장 왼쪽 빈 공간 * 2, 가장 오른쪽 빈 공간 * 2, 가장 긴 도로 길이)
                    long result1 = 2L * (minPos - 1);
                    long result2 = 2L * (N - maxPos);
                    long result3 = (road != null) ? road.length : 0;

                    long finalResult = Math.max(result1, Math.max(result2, result3));
                    sb.append(finalResult).append("\n");
                    break;
                }
            }
        }
        System.out.print(sb.toString());
    }
}
