import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*제한 - 메모리 128 MB, 시간 3 초*/ 
/*스태틱변수 선언시, 메모리 : 22,136 KB   시간 : 1272 MS*/ 
/*메인함수안 선언시, 메모리 : 21,892 KB  시간 : 1424 MS*/
public class DFS_Cycle_ForLoop_9466 {
	static int[] adjacent;
	static int[] visited;
	static int[] s;
	static int Answer;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("sample/sample_DFS_Cycle_9466.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		long startTime = System.currentTimeMillis();
		for (int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine());
			// a - 인접, c - 방문, s - 정점기준
			int N = Integer.parseInt(st.nextToken());

			adjacent = new int[N + 1];
			visited = new int[N + 1];
			s = new int[N + 1];
			Answer = 0;

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				int v = Integer.parseInt(st.nextToken());
				adjacent[i] = v;
			}

			for (int i = 1; i <= N; i++) {
				if (visited[i] == 0) {
					Answer += DFS(i, i);
				}
			}
			StringBuilder sb = new StringBuilder();
			sb.append((N - Answer));
			System.out.println(sb.toString());
		}
		// System.out.println("Time: " + (System.currentTimeMillis() -startTime) / 1000.0 + "\n");
	}

	public static int DFS(int v, int step) {
		int count = 1;
		while (true) {
			if (visited[v] != 0) {
				if (s[v] != step) {
					// 이미 방문했고, 정점 시작점이 다를 경우 사이클 x
					return 0;
				}
				return count - visited[v];
			}
			s[v] = step;
			visited[v] = count;
			v = adjacent[v];
			count += 1;
		}
	}
}