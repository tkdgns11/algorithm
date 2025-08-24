package test;

import java.io.*;
import java.util.*;

public class Baekjoon1181단어정렬 {

    static class Node implements Comparable<Node> {
        String str;

        Node(String str) {
            this.str = str;
        }

        @Override
        public int compareTo(Node o) {
            // 길이 우선 비교
            if (this.str.length() != o.str.length()) {
                return this.str.length() - o.str.length();
            }
            // 길이가 같으면 사전순 비교
            return this.str.compareTo(o.str);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine().trim());

        Set<String> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            set.add(br.readLine().trim()); // 중복 제거
        }

        List<Node> list = new ArrayList<>();
        for (String s : set) {
            list.add(new Node(s));
        }

        Collections.sort(list); // Node의 compareTo 기준 정렬

        for (Node node : list) {
            bw.write(node.str + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
