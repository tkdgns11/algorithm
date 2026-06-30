package algorithm;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/92343
class Solution {
    
    // 현재 위치, 양의 수, 늑대의 수, 방문한 노드
    private static class Info {
        int node, sheep, wolf;
        HashSet<Integer> visited;
        
        public Info(int node, int sheep, int wolf, HashSet<Integer> visited) {
            this.node = node;
            this.sheep = sheep;
            this.wolf = wolf;
            this.visited = visited;
        }
    }
    
    private static ArrayList<Integer>[] tree; // 트리 정보를 저장할 인접 리스트
    
    // 트리 구축 메서드
    private static void buildTree(int[] info, int[][] edges) {
        tree = new ArrayList[info.length];
        for(int i = 0; i < tree.length; i++) {
            tree[i] = new ArrayList<>();
        }
        
        for(int[] edge : edges) {
            tree[edge[0]].add(edge[1]);
        }
    }
    
    public int solution(int[] info, int[][] edges) {
        
        buildTree(info, edges); // 트리 생성
        int answer = 0; // 최대 양의 수
        
        // BFS를 위한 큐 생성 및 초기상태 지정
        ArrayDeque<Info> queue = new ArrayDeque<>();
        queue.add(new Info(0, 1, 0, new HashSet<>())); // int node, int sheep, int wolf, HashSet<Integer> visited
        
        while(!queue.isEmpty()) {
            Info now = queue.poll(); // 현재 상태
            answer = Math.max(answer, now.sheep); 
            now.visited.addAll(tree[now.node]); //방문한 노드 집합에 현재 노드의 이웃노드 추가
            
            for(int next : now.visited) {
                HashSet<Integer> set = new HashSet<>(now.visited);
                set.remove(next);
                
                if(info[next] == 1) { // 늑대일 경우
                    if(now.sheep != now.wolf + 1) {
                        queue.add(new Info(next, now.sheep, now.wolf + 1, set));
                    }
                } else { // 양일 경우
                    queue.add(new Info(next, now.sheep + 1, now.wolf, set));
                }
            }
        }
        return answer;
    }
}