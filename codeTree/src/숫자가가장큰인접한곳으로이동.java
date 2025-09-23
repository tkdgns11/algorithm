package src;

import java.util.*;
import java.io.*;

public class 숫자가가장큰인접한곳으로이동 {
    
    // 상 하 좌 우
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int[][] pan;
    static List<int[]> gusle;
    
    static class GuslePos {
    	int i;
    	int j;
    	boolean hit;
    	
    	GuslePos(int i, int j, boolean hit) {
    		this.i = i;
    		this.j = j;
    		this.hit = hit;
    	}
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N,M,T;

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        
        pan = new int[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                pan[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        gusle = new ArrayList<>();

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int ii = Integer.parseInt(st.nextToken());
            int jj = Integer.parseInt(st.nextToken());
            gusle.add(new int[] {ii, jj});
        }
        
        while(T-- > 0 && gusle.size() > 0) {

        	List<GuslePos> movePos = new ArrayList<>();

            for(int i=0; i<gusle.size(); i++) {
                int[] moveGusle = gusle.get(i);
                int num = -1;
                int[] maxPoint = new int[2];
                for(int j=0; j<4; j++) {
                	int ni = moveGusle[0] + dy[j];
                	int nj = moveGusle[1] + dx[j];
                	
                    if(ni >= 0 && ni < N && nj >=0 && nj < N && num < pan[ni][nj]) {
                        num = pan[ni][nj];
                        maxPoint[0] = ni;
                        maxPoint[1] = nj;
                    }
                }
                movePos.add(new GuslePos(maxPoint[0], maxPoint[1], false));
            }

            for(int i=0; i<movePos.size(); i++) {
            	GuslePos num1 = movePos.get(i);
            	for(int j=i+1; j<movePos.size(); j++) {
	            	GuslePos num2 = movePos.get(j);
	            	if(num1.i == num2.i && num1.j == num2.j) {
	            		num1.hit = true;
	            		num2.hit = true;
	            	}
            	}
            }
            
            gusle = new ArrayList<>();
            
            for(int i=0; i<movePos.size(); i++) {
            	GuslePos tmp = movePos.get(i);
            	if(!tmp.hit) {
            		gusle.add(new int[] {tmp.i, tmp.j});
            	}
            }
        }
        System.out.println(gusle.size());
    }
}