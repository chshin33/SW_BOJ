import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DP_MoveCase {

	static int[][] DP;
	static int[][] check;
	static int N, K;
	static long Answer;
	static int[] AX;
	static int[] AY;
	static int[] BX;
	static int[] BY;
	static int MOD = 1000000007;
	static int DEBUG=1;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("sample/sample_DP_MoveCase.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		for (int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			AX = new int[K + 1];
			AY = new int[K + 1];
			BX = new int[K + 1];
			BY = new int[K + 1];

			DP = new int[N + 1][N + 1];
			check = new int[N + 1][N + 1];
			
			for(int[] row : check){
				Arrays.fill(row, -1);
			}

			int ax,ay,bx,by;
			for (int i = 1; i <= K; i++) {
				st = new StringTokenizer(br.readLine());
				ax = Integer.parseInt(st.nextToken());
				ay = Integer.parseInt(st.nextToken());
				bx = Integer.parseInt(st.nextToken());
				by = Integer.parseInt(st.nextToken());
				if(ax==1&&ay==1){
					check[ax][ay] =1;
					check[bx][by] =0;
				}else if(bx==N&&by==N){
					check[ax][ay] =0;
					check[bx][by] =1;
				}else{
					check[ax][ay] =1;
					check[bx][by] =1;
				}
			}
			
			if(DEBUG==1) {
				System.out.println("==check==");
				print(check);
			}

			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (i == 1 && j == 1) {
						DP[i][j] = 1;
					}else{
						if(check[i][j]==0){
							DP[i][j] = 0;
						}else{
							DP[i][j] = (DP[i - 1][j] % MOD + DP[i][j - 1] % MOD) % MOD;	
						}
					}
				}
			}
			
			if(DEBUG==1) {
				System.out.println("==DP==");
				print(DP);
			}
			

			System.out.println("#" + testCase + " " + DP[N][N]);

		}

	}
	
	public static void print(int[][] arr){
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				System.out.print(arr[i][j] + "\t");
			}
			System.out.println();
		}
		
	}
}