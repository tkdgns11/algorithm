package algorithm;

import java.io.*;
import java.util.*;

public class 암호문3_optimized {

	static class Node {
		Node next;
		int value;

		public Node(int value) {
			this.value = value;
		}
	}

	static class NodeLinkedList {
		Node head = new Node(-1);
		Node tail = head;  // tail 포인터 추가

		void insert(int x, int y, StringTokenizer st) {
			Node insertNode = head;

			for(int i=0; i<x; i++) {
				insertNode = insertNode.next;
			}

			Node nextNode = insertNode.next;
			Node lastInserted = insertNode;

			for(int i=0; i<y; i++) {
				Node newNode = new Node(Integer.parseInt(st.nextToken()));
				lastInserted.next = newNode;
				lastInserted = newNode;
			}
			lastInserted.next = nextNode;
			
			// tail 업데이트
			if(nextNode == null) {
				tail = lastInserted;
			}
		}

		void delete(int x, int y) {
			Node startNode = head;

			for(int i=0; i<x; i++) {
				startNode = startNode.next;
			}

			Node tmpNode = startNode.next;

			for(int i=0; i<y; i++) {
				tmpNode = tmpNode.next;
			}
			startNode.next = tmpNode;
			
			// tail 업데이트
			if(tmpNode == null) {
				tail = startNode;
			}
		}

		void add(int y, StringTokenizer st) {
			for(int i=0; i<y; i++) {
				Node newNode = new Node(Integer.parseInt(st.nextToken()));
				tail.next = newNode;
				tail = newNode;
			}
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for (int tc = 1; tc <= 10; tc++) {

			int N = Integer.parseInt(br.readLine());

			StringTokenizer st = new StringTokenizer(br.readLine());

			NodeLinkedList linkedList = new NodeLinkedList();

			Node cur = linkedList.head;

			// 초기 리스트 생성 최적화
			for(int i=0; i<N; i++) {
				Node node = new Node(Integer.parseInt(st.nextToken()));
				cur.next = node;
				cur = node;
			}
			linkedList.tail = cur;  // tail 설정

			int M = Integer.parseInt(br.readLine());

			st = new StringTokenizer(br.readLine());

			for(int i=0; i<M; i++) {
				char command = st.nextToken().charAt(0);  // String 대신 char

				if(command == 'I') {
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					linkedList.insert(x, y, st);
				} else if(command == 'A') {
					int y = Integer.parseInt(st.nextToken());
					linkedList.add(y, st);
				} else {  // 'D'
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					linkedList.delete(x, y);
				}
			}

			bw.write("#" + tc + " ");
			
			Node writeNode = linkedList.head.next;
			
			for(int i=0; i<10; i++) {
				bw.write(Integer.toString(writeNode.value));
				bw.write(' ');
				writeNode = writeNode.next;
			}
			bw.write('\n');
		}
		bw.flush();
		bw.close();
		br.close();
	}
}