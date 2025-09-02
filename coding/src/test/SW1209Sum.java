package test;

import java.util.Scanner;

public class SW1209Sum {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int t, max;
		int arr[][] = new int[100][100];

		for (int tc = 1; tc <= 10; tc++) {
			max = 0;

			t = sc.nextInt();

			for (int i = 0; i < 100; i++)
				for (int j = 0; j < 100; j++)
					arr[i][j] = sc.nextInt();

			for (int i = 0; i < 100; i++) { // row 
				t = 0;
				for (int j = 0; j < 100; j++)
					t += arr[i][j];
				if (t > max)
					max = t;
			}

			for (int i = 0; i < 100; i++) { // col
				t = 0;
				for (int j = 0; j < 100; j++)
					t += arr[j][i];
				if (t > max)
					max = t;
			}

			t = 0;
			for (int i = 0; i < 100; i++)
				t += arr[i][i];
			if (t > max)
				max = t;

			t = 0;
			for (int i = 0; i < 100; i++)
				t += arr[i][99 - i];
			if (t > max)
				max = t;

			System.out.println("#" + tc + " " + max);

		}
		sc.close();
	}
}
