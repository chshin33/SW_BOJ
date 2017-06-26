import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class DFS_Cycle_9466 {

	static int N;
	static int Answer;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("sample/sample_DFS_Cycle_9466.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		long startTime = System.currentTimeMillis();
		// a - 인접, c - 방문, s - 정점기준
		for(int testCase=1; testCase<=T; testCase++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int[] a = new int[N + 1];
			int[] c = new int[N+ 1];
			int[] s = new int[N + 1];
			Answer = 0;

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				int v = Integer.parseInt(st.nextToken());
				a[i] = v;
			}
			
			for(int i=1;i<=N;i++) {
				if (c[i] == 0) {
					Answer += dfs(a,c,s,i,i);
				}
			}
			
		}
		
		System.out.println(N - Answer);
		//System.out.println("Time: " + (System.currentTimeMillis()-startTime)/1000.0 + "\n");
	}

	public static int dfs(int[] a, int[] c, int[] s, int v, int step) {
		int cnt = 1;

		while(true) {
			
			if (c[v] != 0) {
				if (s[v] != step) {
					// 이미 방문했고, 정점 시작점이 다를 경우 사이클 x
					return 0;
				}
				return cnt - c[v];
			}
			
			s[v] = step;
			c[v] = cnt;
			v = a[v];
			
			cnt += 1;
		}
		
	}
	
}