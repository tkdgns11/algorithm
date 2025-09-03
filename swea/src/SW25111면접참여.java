package src;

import java.io.*;
import java.util.*;

public class SW25111면접참여 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long N = Long.parseLong(st.nextToken());
            long M = Long.parseLong(st.nextToken());
            long K = Long.parseLong(st.nextToken());

            long W = N - M;
            
            long noDoubleMax = (W + 1) * (K - 1);

            if (M <= noDoubleMax) {
                bw.write(M + "\n");
            } else {
                long R = M - noDoubleMax; 
                long d = (R + K - 1) / K;  
                long pow2 = pow2(d);
                long ans = 2L * K * (pow2 - 1L) + (M - d * K);
                bw.write(ans + "\n");
            }
        }

        bw.flush();
    }

    private static long pow2(long e) {
        return 1L << e;
    }
}
