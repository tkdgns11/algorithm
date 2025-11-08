package algorithm;

import java.io.*;
import java.util.*;

public class 암호문3 {
	
	static class Node {
		Node next;
		int value;
		
		public Node(Node next, int value) {
			super();
			this.next = next;
			this.value = value;
		}
	}  
	
	static class NodeLinkedList {
		Node head = new Node(null, -1);
		
		void insert(int x, int y, int[] values) {
			Node insertNode = head;
			
			for(int i=0; i<x; i++) {
				insertNode = insertNode.next;
			}
			
			Node NextNode = insertNode.next; 
			
			for(int i=0; i<y; i++) {
				Node newNode = new Node(null, values[i]);
				insertNode.next = newNode;
				insertNode = newNode;
			}
			insertNode.next = NextNode;
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
		}
		
		void add(int y, int[] values) {
			Node node = head;
			while(node.next != null) {
				node = node.next;
			}
			
			for(int i=0; i<y; i++) {
				Node newNode = new Node(null, values[i]);
				node.next = newNode;
				node = newNode;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for (int tc = 1; tc <= 10; tc++) {
			
			int N = Integer.parseInt(br.readLine().trim()); // 암호문 뭉치 속 암호문의 갯수 N
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			NodeLinkedList linkedList = new NodeLinkedList();
			
			Node cur = linkedList.head;
			
			for(int i=0; i<N; i++) {
				Node node = new Node(null, Integer.parseInt(st.nextToken()));
				cur.next = node;
				cur = cur.next; 
			}
			
			int M = Integer.parseInt(br.readLine().trim()); // 명령어 갯수
			
			st = new StringTokenizer(br.readLine());
			
			for(int i=0; i<M; i++) {
				String command = st.nextToken();
				
				if(command.equals("I")) {
					int x = Integer.parseInt(st.nextToken()); 
					int y = Integer.parseInt(st.nextToken());
					
					int[] values = new int[y];
					
					for(int i1=0; i1<y; i1++) {
						values[i1] = Integer.parseInt(st.nextToken());
					}
					linkedList.insert(x, y, values);
				} else if(command.equals("A")) {
					
					int y = Integer.parseInt(st.nextToken());
					
					int[] values = new int[y];
					
					for(int i1=0; i1<y; i1++) {
						values[i1] = Integer.parseInt(st.nextToken());
					}
					linkedList.add(y, values);
				} else {
					
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
