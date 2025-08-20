package test;

/**
 * 일타싸피 플래너 (고스트볼 + smart power)
 * - 각도: atan2(dx, dy) → degree(0~360)
 * - 조준점: 고스트볼 G = T - 2R * unit(H - T)
 * - 파워: p = 20 + 0.55*|W-G| + 0.35*|T-H|  (20~100 클램프)
 */
public class Planner_일타싸피 {

    // --- 테이블/포켓 상수 (스타터와 동일) ---
    public static final int TABLE_WIDTH  = 254;
    public static final int TABLE_HEIGHT = 127;
    public static final int[][] HOLES = {
        {0,0}, {127,0}, {254,0},
        {0,127}, {127,127}, {254,127}
    };

    // --- 물리/수치 상수 ---
    public static final double R   = 2.8575; // 공 반지름(cm)
    public static final double EPS = 1e-6;   // 거의 0 판정
    public static final float  MIN_PWR = 20f;
    public static final float  MAX_PWR = 100f;

    private static boolean alive(float x, float y) { return x >= 0 && y >= 0; }

    /** 코너 포켓을 R만큼 안쪽으로 보정 (쿠션 끼임 방지) */
    private static double[] holeIn(int hx, int hy) {
        double x = hx, y = hy;
        if (hx == 0)                x = R;
        else if (hx == TABLE_WIDTH) x = hx - R;
        if (hy == 0)                y = R;
        else if (hy == TABLE_HEIGHT)y = hy - R;
        return new double[]{x, y};
    }

    /** 고스트볼: G = T - 2R * unit(H - T).  len<EPS면 G=T(스킵 신호) */
    private static double[] ghost(double Tx, double Ty, double Hx, double Hy) {
        double vx = Hx - Tx, vy = Hy - Ty;
        double len = Math.hypot(vx, vy);
        if (len < EPS) return new double[]{Tx, Ty}; // 스킵 신호(퇴로)
        double ux = vx / len, uy = vy / len;
        return new double[]{ Tx - 2 * R * ux, Ty - 2 * R * uy };
    }

    /** 각도(도): 0=아래(+y), 90=오른쪽(+x)  ※ atan2 인자순서 (dx, dy)! */
    private static float angleDeg(double fromX, double fromY, double toX, double toY) {
        double dx = toX - fromX, dy = toY - fromY;
        double rad = Math.atan2(dx, dy); // (dx, dy)!
        float deg = (float) Math.toDegrees(rad);
        return (deg < 0) ? deg + 360f : deg;
    }

    /** smart power: p = 20 + 0.55*|W-G| + 0.35*|T-H|, 20~100 클램프 */
    private static float powerSmart(double Wx,double Wy,double Gx,double Gy,
                                    double Tx,double Ty,double Hx,double Hy) {
        double dWG = Math.hypot(Gx - Wx, Gy - Wy);   // 흰공→고스트
        double dTH = Math.hypot(Hx - Tx, Hy - Ty);   // 타깃→포켓
        double p   = 20.0 + 0.55*dWG + 0.35*dTH;
        float pf   = (float)p;
        if (pf < MIN_PWR) pf = MIN_PWR;
        if (pf > MAX_PWR) pf = MAX_PWR;
        return pf;
    }

    /** 결과 묶음 */
    public static class Shot { public float angle, power; }

    /** 메인: balls/order로 angle/power 결정 */
    public static Shot plan(float[][] balls, int order) {
        Shot out = new Shot();

        float Wx = balls[0][0], Wy = balls[0][1];

        // 1) 타깃 선택 (선공: 1,3,5 / 후공: 2,4,5)
        int[] pri = (order == 1) ? new int[]{1,3,5} : new int[]{2,4,5};
        int tIdx = -1;
        for (int b : pri) {
            if (alive(balls[b][0], balls[b][1])) { tIdx = b; break; }
        }

        if (tIdx == -1) {
            // 전부 포켓 → 중앙으로 가볍게
            out.angle = angleDeg(Wx, Wy, TABLE_WIDTH/2.0, TABLE_HEIGHT/2.0);
            out.power = 30f;
            return out;
        }

        double Tx = balls[tIdx][0], Ty = balls[tIdx][1];

        // 2) 포켓 후보 중 |W-G| 가 가장 짧은 것 선택
        double best = Double.POSITIVE_INFINITY, Gx = Tx, Gy = Ty, Hx = 0, Hy = 0;
        boolean found = false;

        for (int[] H : HOLES) {
            double[] Hin = holeIn(H[0], H[1]);
            double[] G   = ghost(Tx, Ty, Hin[0], Hin[1]);
            boolean bad  = (Math.abs(G[0]-Tx) < EPS && Math.abs(G[1]-Ty) < EPS);
            if (bad) continue;

            double dWG = Math.hypot(G[0]-Wx, G[1]-Wy);
            if (!Double.isFinite(dWG)) continue;

            if (dWG < best) {
                best = dWG;
                Gx = G[0]; Gy = G[1];
                Hx = Hin[0]; Hy = Hin[1];
                found = true;
            }
        }

        // 3) 각도/파워
        if (!found) {
            // 폴백: 타깃 중심 두껍게, smart power도 대략 적용 (T에서 가장 가까운 포켓 기준)
            double nhx = HOLES[0][0], nhy = HOLES[0][1], bestTH = Double.POSITIVE_INFINITY;
            for (int[] H : HOLES) {
                double[] Hin = holeIn(H[0], H[1]);
                double dTH = Math.hypot(Hin[0]-Tx, Hin[1]-Ty);
                if (dTH < bestTH) { bestTH = dTH; nhx = Hin[0]; nhy = Hin[1]; }
            }
            out.angle = angleDeg(Wx, Wy, Tx, Ty);                  // 타깃 중심 조준
            out.power = powerSmart(Wx, Wy, Tx, Ty, Tx, Ty, nhx, nhy); // G=T, H=가까운 포켓
        } else {
            out.angle = angleDeg(Wx, Wy, Gx, Gy);                  // 고스트 조준
            out.power = powerSmart(Wx, Wy, Gx, Gy, Tx, Ty, Hx, Hy); // smart power
        }

        return out;
    }
}
