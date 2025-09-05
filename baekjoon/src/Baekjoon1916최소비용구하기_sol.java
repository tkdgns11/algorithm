package src;

import java.io.*;
import java.util.*;

/**
 * BOJ 1916 - 최소비용 구하기 (인접리스트 + 중복 간선 최소화)
 *
 * 핵심 아이디어
 * 1) 입력 단계에서 같은 (u -> v) 간선이 여러 번 들어오면, 비용이 최소인 간선만 보관한다.
 *    - Map.merge(v, w, Math::min) 으로 한 줄 처리 (O(1) 기대)
 * 2) 이렇게 정리된 자료를 인접리스트(List<Edge>[])로 변환한다.
 * 3) 다익스트라 (우선순위큐 사용, O((N + M) log N)) 로 최소비용을 구한다.
 *
 *
 *	맞아. 내가 준 버전이 느린 건 HashMap + 객체(ArrayList/Edge) 오버헤드 때문이야.
1916은 N≤1000이라 인접행렬도 충분히 빠른 편이고, 내가 쓴 “중복 합치기용 Map”이 박싱/해시/객체 생성을 많이 유발해서 오히려 시간·메모리를 잡아먹었을 가능성이 큼.
 *
 */
public class Baekjoon1916최소비용구하기_sol {

    // 안전한 INF: 덧셈 시 오버플로를 피하기 위해 Integer.MAX_VALUE 대신 1e9 사용
    static final int INF = 1_000_000_000;

    /** 그래프의 간선(도착 정점, 가중치) */
    static class Edge {
        int to, w;
        Edge(int to, int w) { this.to = to; this.w = w; }
    }

    /** 다익스트라 우선순위큐에 넣을 노드(정점 번호, 시작점으로부터의 현재 최소비용) */
    static class Node implements Comparable<Node> {
        int pos, dist;
        Node(int pos, int dist) { this.pos = pos; this.dist = dist; }
        @Override public int compareTo(Node o) { return Integer.compare(this.dist, o.dist); }
    }

    public static void main(String[] args) throws Exception {
        // 빠른 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine().trim()); // 도시 수 (정점 수)
        int M = Integer.parseInt(br.readLine().trim()); // 버스 수 (간선 수)

        /**
         * 입력을 곧바로 인접리스트에 넣지 않고, 먼저 (u -> v)별 최소비용만 모아둔다.
         * best[u]는 "도착지 v -> 최소비용 w" 를 담는 맵.
         * 예) best[3].get(5) = 3 -> 5 로 가는 최소 비용
         */
        @SuppressWarnings("unchecked")
        Map<Integer, Integer>[] best = new HashMap[N + 1];
        for (int i = 1; i <= N; i++) best[i] = new HashMap<>();

        // M개의 간선 입력 처리: (u, v, w)
        // 같은 (u, v)가 여러 번 들어오면 merge로 최소 비용만 남긴다.
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            // Map.merge: 키 v가 없으면 put(v, w), 있으면 Math.min(기존, 새값)으로 갱신
            best[u].merge(v, w, Math::min);
        }

        // 시작/도착 정점
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end   = Integer.parseInt(st.nextToken());

        /**
         * 인접리스트 구성
         * best[u]에 남아있는 (v, 최소 w)만 실제 그래프에 넣는다.
         * 이렇게 하면 중복 간선이 제거되어 다익스트라 수행 시 불필요한 relax가 줄어든다.
         */
        @SuppressWarnings("unchecked")
        List<Edge>[] g = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) g[i] = new ArrayList<>();
        for (int u = 1; u <= N; u++) {
            for (Map.Entry<Integer, Integer> e : best[u].entrySet()) {
                int v = e.getKey();
                int w = e.getValue();
                g[u].add(new Edge(v, w));
            }
        }

        // 다익스트라 준비
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        // 다익스트라
        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            // 우선순위큐에서 꺼낸 값이 최신(dist[cur.pos])이 아니면 스킵 (outdated 제거)
            if (cur.dist != dist[cur.pos]) continue;

            // 목적지(end)가 처음 확정되는 순간의 dist가 최단거리 → 즉시 종료 가능 (조기 종료)
            if (cur.pos == end) {
                System.out.println(cur.dist);
                return;
            }

            // 인접 정점 완화(relaxation)
            for (Edge e2 : g[cur.pos]) {
                int nd = cur.dist + e2.w;
                if (nd < dist[e2.to]) {
                    dist[e2.to] = nd;
                    pq.offer(new Node(e2.to, nd));
                }
            }
        }

        // (문제 특성상 start → end 경로가 항상 있다고 가정되지만)
        // 안전하게 처리하려면 아래 출력이 필요할 수 있음.
        // System.out.println(dist[end] == INF ? -1 : dist[end]);
    }
}
