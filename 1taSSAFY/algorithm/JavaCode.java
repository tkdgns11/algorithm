package algorithm;

import java.net.*;
import java.awt.Point;
import java.io.*;

/**
 * 알고리즘 전략 : 타깃 구를 순서대로 hole에 넣는다. 어떤 hole에 넣는것이 효율적인지 판단 및 강도를 조절.
 */
public class JavaCode {

	// 닉네임을 사용자에 맞게 변경해 주세요.
	static final String NICKNAME = "[구미_5반_윤상훈,김민서]";

	// 일타싸피 프로그램을 로컬에서 실행할 경우 변경하지 않습니다.
	static final String HOST = "127.0.0.1";

	// 일타싸피 프로그램과 통신할 때 사용하는 코드값으로 변경하지 않습니다.
	static final int PORT = 1447;
	static final int CODE_SEND = 9901;
	static final int CODE_REQUEST = 9902;
	static final int SIGNAL_ORDER = 9908;
	static final int SIGNAL_CLOSE = 9909;
 
	// 게임 환경에 대한 상수입니다.
	static final int TABLE_WIDTH = 254;
	static final int TABLE_HEIGHT = 127;
	static final int NUMBER_OF_BALLS = 6;
	static final int[][] HOLES = { { 0, 0 }, { 127, 0 }, { 254, 0 }, { 0, 127 }, { 127, 127 }, { 254, 127 } };

	static final double PWR_MAX = 100.0;
	static final double PWR_MIN = 20.0;
	static final double EPS = 1e-6;
	static final double r = 1.8;

	static double[] holeIn(int hx, int hy) {
		double x = hx;
		double y = hy;
		if (x == 0)
			x = r;
		else if (x == TABLE_WIDTH)
			x = x - r;
		if (y == 0)
			y = r;
		else if (y == TABLE_HEIGHT)
			y = y - r;
		return new double[] { x, y };
	}

	static double[] ghost(double Tx, double Ty, double Hx, double Hy) {
		double Vx = Hx - Tx;
		double Vy = Hy - Ty;
		double len = Math.hypot(Vx, Vy);
		if (len < EPS)
			return new double[] { Tx, Ty };

		double Ux = Vx / len;
		double Uy = Vy / len;

		return new double[] { Tx - 2 * r * Ux, Ty - 2 * r * Uy };
	}

	static float angle(double fromX, double fromY, double toX, double toY) {
		double dx = toX - fromX;
		double dy = toY - fromY;
		double rad = Math.atan2(dx, dy);
		float degree = (float) Math.toDegrees(rad);

		return degree < 0 ? degree + 360f : degree;
	}

	static boolean alive(float x, float y) {
		return x >= 0 && y >= 0;
	}

	static double powerCalc(double Wx, double Wy, double Gx, double Gy, double Tx, double Ty, double Hx, double Hy) {

		double dWG = Math.hypot(Gx - Wx, Gy - Wy);
		double dTH = Math.hypot(Hx - Tx, Hy - Ty);

		double calc = (dWG * 0.55 + dTH * 0.25 + 0.2);
		if (calc < PWR_MIN)
			calc = PWR_MIN;
		if (calc > PWR_MAX)
			calc = PWR_MAX;

		return calc;
	}
	
	static Point hole_select(int [][]holes, float tx, float ty, int n) {
		Point holePoint= new Point();
		for(int i=0; i<6; i++) {
			int h_x=HOLES[0][0];
			int h_y=HOLES[0][1];
			if(n==1) {//1사분면이어야할때
				if(h_x>tx&&h_y>ty) {
					holePoint=new Point(h_x,h_y);
					break;
				}
			}else if(n==2) {
				if(h_x<tx&&h_y>ty) {
					holePoint=new Point(h_x,h_y);
					break;
				}
			}else if(n==3) {
				if(h_x<tx&&h_y<ty) {
					holePoint=new Point(h_x,h_y);
					break;
				}
			}else if(n==4) {
				if(h_x>tx&&h_y<ty) {
					holePoint=new Point(h_x,h_y);
					break;
				}
			}
		}
		return holePoint;
	}

	public static void main(String[] args) {

		Socket socket = null;
		String recv_data = null;
		byte[] bytes = new byte[1024];
		float[][] balls = new float[NUMBER_OF_BALLS][2];
		int order = 0;

		try {
			socket = new Socket();
			System.out.println("Trying Connect: " + HOST + ":" + PORT);
			socket.connect(new InetSocketAddress(HOST, PORT));
			System.out.println("Connected: " + HOST + ":" + PORT);

			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();

			String send_data = CODE_SEND + "/" + NICKNAME + "/";
			bytes = send_data.getBytes("UTF-8");
			os.write(bytes);
			os.flush();
			System.out.println("Ready to play!\n--------------------");

			while (socket != null) {

				// Receive Data
				bytes = new byte[1024];
				int count_byte = is.read(bytes);
				recv_data = new String(bytes, 0, count_byte, "UTF-8");
				System.out.println("Data Received: " + recv_data);

				// Read Game Data
				String[] split_data = recv_data.split("/");
				int idx = 0;
				try {
					for (int i = 0; i < NUMBER_OF_BALLS; i++) {
						for (int j = 0; j < 2; j++) {
							balls[i][j] = Float.parseFloat(split_data[idx++]);
						}
					}
				} catch (Exception e) {
					bytes = (CODE_REQUEST + "/" + CODE_REQUEST).getBytes("UTF-8");
					os.write(bytes);
					os.flush();
					System.out.println("Received Data has been currupted, Resend Requested.");
					continue;
				}

				// Check Signal for Player Order or Close Connection
				if (balls[0][0] == SIGNAL_ORDER) {
					order = (int) balls[0][1];
					System.out.println("\n* You will be the " + (order == 1 ? "first" : "second") + " player. *\n");
					continue;
				} else if (balls[0][0] == SIGNAL_CLOSE) {
					break;
				}

				// Show Balls' Position
				for (int i = 0; i < NUMBER_OF_BALLS; i++) {
					System.out.println("Ball " + i + ": " + balls[i][0] + ", " + balls[i][1]);
				}

				float angle = 0.0f;
				float power = 20.0f;

				//////////////////////////////
				// 이 위는 일타싸피와 통신하여 데이터를 주고 받기 위해 작성된 부분이므로 수정하면 안됩니다.
				//
				// 모든 수신값은 변수, 배열에서 확인할 수 있습니다.
				// - order: 1인 경우 선공, 2인 경우 후공을 의미
				// - balls[][]: 일타싸피 정보를 수신해서 각 공의 좌표를 배열로 저장
				// 예) balls[0][0]: 흰 공의 X좌표
				// balls[0][1]: 흰 공의 Y좌표
				// balls[1][0]: 1번 공의 X좌표
				// balls[4][0]: 4번 공의 X좌표
				// balls[5][0]: 마지막 번호(8번) 공의 X좌표

				// 여기서부터 코드를 작성하세요.
				// 아래에 있는 것은 샘플로 작성된 코드이므로 자유롭게 변경할 수 있습니다.

				// whiteBall_x, whiteBall_y: 흰 공의 X, Y좌표를 나타내기 위해 사용한 변수
				float Wx = balls[0][0];
				float Wy = balls[0][1];

				// targetBall_x, targetBall_y: 목적구의 X, Y좌표를 나타내기 위해 사용한 변수
				float Tx = balls[1][0];
				float Ty = balls[1][1];

				// width, height: 목적구와 흰 공의 X좌표 간의 거리, Y좌표 간의 거리
				// float width = Math.abs(tX - whiteBall_x);
				// float height = Math.abs(targetBall_y - whiteBall_y);

				// radian: width와 height를 두 변으로 하는 직각삼각형의 각도를 구한 결과
				// - 1radian = 180 / PI (도)
				// - 1도 = PI / 180 (radian)
				// angle : 아크탄젠트로 얻은 각도 radian을 degree로 환산한 결과
				// double radian = height > 0? Math.atan(width / height): 0;
				// angle = (float) ((180.0 / Math.PI) * radian);

				int[] orderList = (order == 1) ? new int[] { 1, 3, 5 } : new int[] { 2, 4, 5 };

				for (int i = 0; i < orderList.length; i++) {
					float[] ball = balls[orderList[i]];
					
					if (alive(ball[0], ball[1])) {
						Tx = ball[0];
						Ty = ball[1];
						break;
					}
				}

				double best = Double.POSITIVE_INFINITY, Hx = HOLES[0][0], Hy = HOLES[0][1], Gx = Tx, Gy = Ty;
				
				for (int[] hole : HOLES) {
					double[] holeCheck = holeIn(hole[0], hole[1]);
					double tmpHx = holeCheck[0];
					double tmpHy = holeCheck[1];

					double[] ghostCheck = ghost(Tx, Ty, tmpHx, tmpHy);
					double tmpGx = ghostCheck[0];
					double tmpGy = ghostCheck[1];

					double calc = Math.hypot(tmpGx - tmpHx, tmpGy - tmpHy);

					if (calc < best) {
						best = calc;
						Hx = tmpHx;
						Hy = tmpHy;
						Gx = tmpGx;
						Gy = tmpGy;
					}
				}

				angle = angle(Wx, Wy, Gx, Gy);
				power = (float) powerCalc(Wx, Wy, Gx, Gy, Tx, Tx, Hx, Hy);

				// 주어진 데이터(공의 좌표)를 활용하여 두 개의 값을 최종 결정하고 나면,
				// 나머지 코드에서 일타싸피로 값을 보내 자동으로 플레이를 진행하게 합니다.
				// - angle: 흰 공을 때려서 보낼 방향(각도)
				// - power: 흰 공을 때릴 힘의 세기
				//
				// 이 때 주의할 점은 power는 100을 초과할 수 없으며,
				// power = 0인 경우 힘이 제로(0)이므로 아무런 반응이 나타나지 않습니다.
				//
				// 아래는 일타싸피와 통신하는 나머지 부분이므로 수정하면 안됩니다.
				//////////////////////////////

				String merged_data = angle + "/" + power + "/";
				bytes = merged_data.getBytes("UTF-8");
				os.write(bytes);
				os.flush();
				System.out.println("Data Sent: " + merged_data);
			}

			os.close();
			is.close();
			socket.close();
			System.out.println("Connection Closed.\n--------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
