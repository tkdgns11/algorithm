package src;

import java.util.*;
import java.io.*;

public class 미로 {
	static class Node {
		int x;
		int y;
		char dir;
		
		Node(){}
		
		Node(int x, int y, char dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}

	public static void main(String[] args) throws Exception {
	            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	            BufferedWriter bw =  new BufferedWriter(new OutputStreamWriter(System.out));
	            
	            int T = 10;
	            
	            // 위 우 아래 좌
	        	int[] dirX = {-1, 0, 1, 0};
	        	int[] dirY = {0, 1, 0, -1}; 

	        	// 판 초기화
	        	int[][] pan = new int[16][16];
	        	
	        	boolean[][] visited = new boolean[16][16];
	            
	            for(int test_case = 1; test_case <= T; test_case++) {
		            int N = Integer.parseInt(br.readLine().trim());
	            	
	            	for(int i=0; i<16; i++) {
	            		String tmp = br.readLine().trim();
	            		for(int j=0; j<16; j++) {
	            			pan[i][j] = Integer.valueOf(tmp.charAt(j) - '0');
	            		}
	            	}
	            	
	            	visited = new boolean[16][16];
	            	
	            	boolean arrive = false;
	            	
	            	Queue<Node> q = new LinkedList<>();
	            	
	            	int currentX = 1;
	            	int currentY = 1;
	            	
	            	if(pan[currentY+1][currentX] == 0) {
	            		Node node = new Node(currentX, currentY, 'D');
	            		q.offer(node);
	            	} 
	            	
	            	if(pan[currentY][currentX+1] == 0) {
	            		Node node = new Node(currentX, currentY, 'R');
	            		q.offer(node);
	            	}
	            	
	            	visited[1][1] = true;
	            	
	            	while(!q.isEmpty()) {
	            		Node currentNode = q.poll();
	            		
	            		currentX = currentNode.x;
	            		currentY = currentNode.y; 
	            		
	            		if (pan[currentY][currentX] == 3) {
	            			arrive = true;
		                    break;
		                }
	            		
	            		for(int i=0; i<4; i++) {
	            			int nx = currentX + dirX[i];
	            			int ny = currentY + dirY[i];
	            			char nDir = "URDL".charAt(i);
	            			
	            			if(nx >= 0 && nx < 16 && ny >= 0 && ny < 16 && !visited[ny][nx] && pan[ny][nx] != 1 ) {
	            				q.offer(new Node(nx, ny, nDir));
	            				visited[ny][nx] = true;
	            			}
	            		}
	            	}
	            	
	            	if(arrive) {
	            		bw.write("#" + N + " " + 1);
	            	} else {
	            		bw.write("#" + N + " " + 0);
	            	}
	            	bw.newLine();
	    		}
	            
	            bw.flush();
	            bw.close();
	            br.close();
	    }
}