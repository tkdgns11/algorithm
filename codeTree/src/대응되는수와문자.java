package src;

import java.io.*;
import java.util.*;



public class 대응되는수와문자 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<String, Integer> map = new HashMap<>();
        String[] strPan = new String[N+1];

        for(int i=0; i<N; i++) {
            String str1 = br.readLine().trim();
            map.put(str1, i+1);
            strPan[i+1] = str1;
        }

        for(int i=0; i<M; i++) {
            String find = br.readLine();

            if(map.containsKey(find)){
                bw.write(String.valueOf(map.get(find)));
                bw.write('\n');
            } else {
                bw.write(strPan[Integer.parseInt(find)]);
                bw.write('\n');
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
