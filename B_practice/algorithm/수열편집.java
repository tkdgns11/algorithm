package algorithm;

import java.io.*;
import java.util.*;

public class 수열편집 {
	
	static class Node {
		Node next;
		int value;
		
		public Node(int value) {
			this.value = value;
		}
		
		public Node(Node nextNode, int value) {
			this.next = nextNode;
			this.value = value;
		}
	}
	
	static class LinkedList {
		Node head = new Node(-1);
		Node tail = new Node(-1);
		
		void insert(int x, int value) {
			Node pos = head;
			
			for(int i=0; i<x; i++) {
				pos = pos.next;
			}
			
			Node insNode = new Node(pos.next, value);
			
			pos.next = insNode;
			
			// 어차피 맨 처음 만들떈 이걸로 안만듦...
			// 제일 마지막 노드가 null이면 추가하는 노드가 마지막
//			if(pos.next == null) {
//				tail = insNode;
//			}
			
			// 새로 넣은 노드가 마지막이면 tail 갱신
            if (insNode.next == null) {
                tail = insNode;
            }
		}
		
		void delete (int idx) {
			Node pos = head;
			
			for(int i=0; i<idx; i++) {
				pos = pos.next;
			}
			
			if(pos.next.next != null) {
				pos.next = pos.next.next;
			} else {
				pos.next = null;
				tail = pos;
			}
		}
		
		void change(int idx, int value) {
			Node pos = head;
			
			for(int i=0; i<=idx; i++) {
				pos = pos.next;
			}
			pos.value = value;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken()); // 수열의 길이
			int M = Integer.parseInt(st.nextToken()); // 추가 횟수 
			int L = Integer.parseInt(st.nextToken()); // 출력 인덱스 번호
			
			LinkedList linkedList = new LinkedList();
			
			st = new StringTokenizer(br.readLine());
			
			Node cur = linkedList.head;
			
			//System.out.println(cur.value);
			
			for(int i=0; i<N; i++) {
				cur.next = new Node(Integer.parseInt(st.nextToken()));
				cur = cur.next;
			}
			linkedList.tail = cur;
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				
				char cmd = st.nextToken().charAt(0);
				
				if(cmd == 'I') {
					int x = Integer.parseInt(st.nextToken());
					int value = Integer.parseInt(st.nextToken());
					
					linkedList.insert(x, value);
				} else if(cmd == 'D') {
					int idx = Integer.parseInt(st.nextToken());
					linkedList.delete(idx);
				} else { // C
					int idx = Integer.parseInt(st.nextToken());
					int value = Integer.parseInt(st.nextToken());
					linkedList.change(idx, value);
				}
			}
			
			cur = linkedList.head;
			
			for(int i=0; i<L; i++) {
				if(cur.next == null) break; 
				cur = cur.next;
			}
			bw.write("#" + tc + " " + (cur.next == null ? -1 : cur.next.value) + '\n');
		}
		bw.flush();
		bw.close();
		br.close();
	}
}