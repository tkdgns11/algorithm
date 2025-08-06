package test;

import java.io.*;
import java.util.*;

public class 정사각형방 {
	
	static int[][] pan;
	
	static int max;
	static int bangNumber;
	
	// 상하좌우
	static int[] dirX = {0, 0, -1, 1};
	
	static int[] dirY = {-1, 1, 0, 0};
	
	static class Node {
		int i; 
		int j;
		int number;
		
		Node(){}
		
		Node(int i, int j, int number){
			this.i = i;
			this.j = j;
			this.number = number;
		}
	}
	
	public static void main(String[] args) throws Exception {

		// 파일 경로 설정 (프로젝트 폴더 기준)
		FileInputStream fileInputStream = new FileInputStream("input.txt");
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
		BufferedReader br = new BufferedReader(inputStreamReader);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine().trim());

		for (int test_case = 1; test_case <= T; test_case++) {
			
			// 판 초기화
			int N = Integer.parseInt(br.readLine().trim());
			
			pan = new int[N][N];
			
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					pan[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			max = Integer.MIN_VALUE;
			bangNumber = Integer.MAX_VALUE;
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					Stack<Node> stack = new Stack<>();
					
					stack.add(new Node(i, j, pan[i][j]));
					
					int count = 0;
					
					boolean[] visited = new boolean[(N * N) + 1]; // 0~4
					
					while(!stack.isEmpty()) {
						
						Node node = stack.pop();
						
						if(visited[node.number]) continue;
						
						count++;
						
						visited[node.number] = true; 
						
						// 상 하 좌 우
						for(int k=0; k<4; k++) {
							int nx = node.j + dirX[k];
							int ny = node.i + dirY[k];
							
							if(nx >= 0 && nx < N && ny >= 0 && ny < N 
									&& !visited[pan[ny][nx]] && pan[ny][nx] == node.number + 1) {
								stack.push(new Node(ny, nx, pan[ny][nx]));
							}
						}
					}
					
					if(max < count) {
						max = count;
						bangNumber = pan[i][j];
					} else if (max == count && bangNumber > pan[i][j]) {
						bangNumber = pan[i][j];
					}
					
				}
			}
			
			bw.write("#" + test_case + " " + bangNumber + " "+ max);
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
		br.close();
		inputStreamReader.close();
		fileInputStream.close();
	}

}