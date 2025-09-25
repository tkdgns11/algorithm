package src;
import java.util.Scanner;
import java.util.*;

/**
 * enum, Pair 사용
 * number 인덱스 실수.. 생각하면서 꼼꼼히 해야되는데 로직 생각하기 바쁨.
 */
public class 강력한폭발 {
    static int n;
    static List<int[]> booms;
    static int[] number;
    static List<Integer> result;
    static int resultMax;
    
    enum One {
        U(-1, 0),
        UU(-2, 0),
        D(1, 0),
        DD(2, 0);

        int i;
        int j;

        One(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    enum Two {
        U(-1, 0),
        R(0, 1),
        D(1, 0),
        L(0, -1);

        int i;
        int j;

        Two(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    enum Three {
        UR(-1, 1),
        UL(-1, -1),
        DR(1, 1),
        DL(1, -1);

        int i;
        int j;

        Three(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    static class Pair {
        public final int a, b;
        public Pair(int a, int b) { this.a = a; this.b = b; }

        @Override public boolean equals(Object o){
            if(this == o) return true;
            if(!(o instanceof Pair)) return false;
            Pair p = (Pair) o;
            return a == p.a && b == p.b;
        }

        @Override public int hashCode() {
            int h = 17;
            h = 31*h + a;
            h = 31*h + b;
            return h;
        }
    }

    static void calc() {
       Set<Pair> set = new HashSet<>();

        for(int i=0; i<booms.size(); i++) {
            int[] boom = booms.get(i);
            set.add(new Pair(boom[0], boom[1]));
            if(number[i] == 1) {
                for(One d : One.values()) {
                    int ni = boom[0] + d.i;
                    int nj = boom[1] + d.j;
                    if(inRange(ni,nj)) {
                        set.add(new Pair(ni, nj));
                    }
                }
            } else if(number[i] == 2) {
                for(Two d : Two.values()) {
                    int ni = boom[0] + d.i;
                    int nj = boom[1] + d.j;
                    if(inRange(ni,nj)) {
                        set.add(new Pair(ni, nj));
                    }
                }
            } else {
                for(Three d : Three.values()) {
                    int ni = boom[0] + d.i;
                    int nj = boom[1] + d.j;
                    if(inRange(ni,nj)) {
                        set.add(new Pair(ni, nj));
                    }
                }
            }
        }
    resultMax = Math.max(set.size(), resultMax);
    }

    static boolean inRange(int i, int j) {
        return i>=0 && i<n && j>=0 && j<n; 
    }

    static void dfs(int depth) {
        if(depth == booms.size()) {
            calc();
            return;
        }

        for(int i=1; i<4; i++) {
            number[depth] = i;
            dfs(depth+1);
        }
    }

    public static void main(String[] args) {

        booms = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
                if(grid[i][j] == 1) {
                    booms.add(new int[]{i, j});
                }
            }
        }

        number = new int[booms.size()];
        result = new ArrayList<>();
        resultMax = Integer.MIN_VALUE;
        dfs(0);
        System.out.println(resultMax);
    }
}