package test;

import java.util.*;
import java.io.*;

public class 길찾기 {
	
	static boolean[] visited;
	
	static int dfs(int i) {
		if(i == 99) return 1;
		visited[i] = true;
		int returnValue = 0;
		
		if(arr1[i] != 0) {
			returnValue = dfs(arr1[i]);
			if(returnValue == 1) return 1;  
		}
		
		if(arr2[i] != 0) {
			returnValue = dfs(arr2[i]);
			if(returnValue == 1) return 1;  
		}
		return 0;
	}
	
	// 초기화
    static int[] arr1 = new int[100];
    
    static int[] arr2 = new int[100];
	

	public static void main(String[] args) throws Exception {
	        
	            // 파일 경로 설정 (프로젝트 폴더 기준)
	            FileInputStream fileInputStream = new FileInputStream("input.txt");
	            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
	            BufferedReader br = new BufferedReader(inputStreamReader);
	            BufferedWriter bw =  new BufferedWriter(new OutputStreamWriter(System.out));
	            
	            int T = 10;
	            
	            for(int test_case = 1; test_case <= T; test_case++) {
		            StringTokenizer st = new StringTokenizer(br.readLine());
		            String testCase = st.nextToken();
		            
		            int N = Integer.parseInt(st.nextToken());
		            
		            visited = new boolean[100]; // 0 ~ 99
		            
		            // 초기화
		            arr1 = new int[100];
		            
		            arr2 = new int[100];
		            
		            st = new StringTokenizer(br.readLine());
		            
		            while(st.hasMoreTokens()) {
		            	int start = Integer.parseInt(st.nextToken());
		            	
		            	if(arr1[start] == 0) {
		            		arr1[start] = Integer.parseInt(st.nextToken());
		            	} else {
		            		arr2[start] = Integer.parseInt(st.nextToken());
		            	}
		            }
		            
		           int result = dfs(0);
		           
		           bw.write("#" + testCase + " " + result);
		           bw.newLine();
	    		}
	            
	            bw.flush();
	            bw.close();
	            br.close();
	            inputStreamReader.close();
	            fileInputStream.close();
	    }
}