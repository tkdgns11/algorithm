import java.util.Arrays;
import java.util.Scanner;
 
public class Solution {
 
    static int find(int x) {
        if(num[x]==x) return x;
        else {
            return num[x]=find(num[x]);
        }
    }   
    static void union(int start, int end) {
        int s=find(start);
        int e=find(end);
        if(s==e) return;
        else {
            num[e]=s;
        }
    } 
    
    static int num[];
     
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int test=1; test<=T; test++) {
             
            int N = sc.nextInt();
            int M = sc.nextInt();
            num= new int[N+1];
            for(int n=0; n<=N; n++) {
                num[n]=n;
            }
            for(int m =0; m<M; m++) {
                int start =sc.nextInt();
                int end = sc.nextInt();
                union(start,end);
            }
            int count=0;
            for(int i=1; i<=N; i++) {
                if(find(i)==i) count++;
            }
            System.out.println("#"+test+" "+count);
        }
    }
 
}