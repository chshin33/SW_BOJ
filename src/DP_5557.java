import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_5557 {
	static int N;
	static int[] input;
	static long[][] DP;
	static int maxNum = 20;
	static int DEBUG = 0;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("sample/sample_DP_5557.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		input = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}

		DP = new long[N+1][maxNum + 1];

		DP[1][input[1]] = 1;
		for (int i = 2; i <= N-1; i++) {
			for (int j = 0; j <= maxNum; j++) {
				if (DP[i - 1][j] != 0) {
					if (DEBUG == 1)
						System.out.printf("%d + %d = %d\n", j, input[i], j + input[i]);
					if (j + input[i] <= maxNum) {
						DP[i][j + input[i]] += DP[i - 1][j];
					}
					if (DEBUG == 1)
						System.out.printf("%d - %d = %d\n", j, input[i], j - input[i]);
					if (j - input[i] >= 0) {
						DP[i][j - input[i]] += DP[i - 1][j];
					}
				}
			}
		}

		if (DEBUG == 1) {
			System.out.print("idx:\t");
			for (int j = 0; j <= maxNum; j++) {
				System.out.print(j + "\t");
			}
			System.out.println();
			for (int i = 1; i <= N-1; i++) {
				System.out.print(i + ":\t");
				for (int j = 0; j <= maxNum; j++) {
					System.out.print(DP[i][j] + "\t");
				}
				System.out.println();
			}
		}

		//마지막 숫자위치 직전(N-2)까지의 연산결과가 마지막 숫자(input[N-1]이어야함
		System.out.println(DP[N-1][input[N]]);
	}
}
