import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_1149 {

	static int DEBUG = 0;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("sample/sample_DP_1149.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int[][] D = new int[3 + 1][N + 1];

		st = new StringTokenizer(br.readLine());
		D[1][1] = Integer.parseInt(st.nextToken());
		D[2][1] = Integer.parseInt(st.nextToken());
		D[3][1] = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			D[1][i + 1] = Math.min(D[2][i], D[3][i]) + Integer.parseInt(st.nextToken());
			D[2][i + 1] = Math.min(D[1][i], D[3][i]) + Integer.parseInt(st.nextToken());
			D[3][i + 1] = Math.min(D[1][i], D[2][i]) + Integer.parseInt(st.nextToken());
		}

		if (DEBUG == 1) {
			for (int i = 1; i <= 3; i++) {
				for (int j = 1; j <= N; j++) {
					System.out.print(D[i][j] + "\t");
				}
				System.out.println();
			}
		}

		int Answer = Integer.MAX_VALUE;
		for (int i = 1; i <= 3; i++) {
			Answer = Math.min(Answer, D[i][N]);
		}
		
		System.out.println(Answer);

	}
}
