package test;

import java.util.*;
import java.io.*;

public class 미로 {
	static int T = 10;
	
	// 위 우 아래 좌
	static int[] dirX = {-1, 0, 1, 0};
	static int[] dirY = {0, 1, 0, -1}; 

	// 판 초기화
	static int[][] pan = new int[16][16];
	
	static boolean[][] visited = new boolean[16][16];
	
	static boolean goYn(int i, int j, char direction) { // 갈 수 있으면 true, 못가면 false
		int nx;
		int ny;
		
		if(direction == 'U') {
			nx = i + dirY[0];
			ny = j + dirX[0];
		} else if(direction == 'R') {
			nx = i + dirY[1];
			ny = j + dirX[1];
		} else if(direction == 'D') {
			nx = i + dirY[2];
			ny = j + dirX[2];
		} else {// L
			nx = i + dirY[3];
			ny = j + dirX[3];
		}
		
		return nx >= 0 && nx < 16 && ny >= 0 && ny < 16 && pan[nx][ny] != 1;
    }
	
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
	        
	            // 파일 경로 설정 (프로젝트 폴더 기준)
	            FileInputStream fileInputStream = new FileInputStream("input.txt");
	            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
	            BufferedReader br = new BufferedReader(inputStreamReader);
	            BufferedWriter bw =  new BufferedWriter(new OutputStreamWriter(System.out));
	            
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
	            	
	            	Stack<Node> stack = new Stack<>();
	            	
	            	int currentX = 1;
	            	int currentY = 1;
	            	
	            	if(pan[currentY+1][currentX] == 0) {
	            		Node node = new Node(currentX, currentY, 'D');
	            		stack.push(node);
	            	} 
	            	
	            	if(pan[currentY][currentX+1] == 0) {
	            		Node node = new Node(currentX, currentY, 'R');
	            		stack.push(node);
	            	}
	            	
	            	while(!stack.isEmpty()) {
	            		Node currentNode = stack.pop();
	            		
	            		currentX = currentNode.x;
	            		currentY = currentNode.y; 
	            		char dir = currentNode.dir;
	            		
	            		if (pan[currentY][currentX] == 3) {
	            			arrive = true;
		                    break;
		                }
	            		
	            		for(int i=0; i<4; i++) {
	            			int nx = currentX + dirX[i];
	            			int ny = currentY + dirY[i];
	            			char nDir = "URDL".charAt(i);
	            			
	            			if(nx >= 0 && nx < 16 && ny >= 0 && ny < 16 && !visited[ny][nx] && pan[ny][nx] != 1 ) {
	            				visited[ny][nx] = true;
	            				stack.push(new Node(nx, ny, nDir));
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
	            
	            // 리소스 정리
	            bw.flush();
	            bw.close();
	            br.close();
	            inputStreamReader.close();
	            fileInputStream.close();
	    }
}