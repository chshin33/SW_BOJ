import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DFS_Cycle_Recursice_9466 {
	static int V;
	static ArrayList<Integer>[] adjList;
	static int[] visitOrder;
	static int[] parent;
	static int[] sameTry;
	static int visitNumber;
	static boolean findCycle;
	static int[] cycleList;
	static int DEBUG = 0;
	static int Answer;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("sample/sample_DFS_Cycle_9466.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		long startTime = System.currentTimeMillis();
		for (int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());

			adjList = new ArrayList[V + 1];

			for (int i = 1; i <= V; i++) {
				adjList[i] = new ArrayList<Integer>();
			}

			st = new StringTokenizer(br.readLine());
			int val;

			for (int i = 1; i <= V; i++) {
				val = Integer.parseInt(st.nextToken());
				adjList[i].add(val);
			}

			if (DEBUG == 1) {
				for (int i = 1; i <= V; i++) {
					bw.write(i + ":" + adjList[i].toString() + "\n");
				}
			}

			visitOrder = new int[V + 1];
			parent = new int[V + 1];
			sameTry = new int[V + 1];
			cycleList = new int[V + 1];

			findCycle = false;

			Answer = 0;

			visitNumber = 1;

			for (int i = 1; i <= V; i++) {
				if (visitOrder[i] == 0 && cycleList[i] == 0) {
					DFS(i);
				}
			}

			bw.write(Integer.toString(V-Answer) + "\n");
		}

		bw.write("Time: " + (System.currentTimeMillis() - startTime) / 1000.0 + "\n");
		bw.flush();
		bw.close();
	}

	static void DFS(int now) {
		visitOrder[now] = visitNumber++;
		sameTry[now] = 1;

		if (adjList[now] == null) {
			sameTry[now] = 0;
			return;
		}

		for (int adj : adjList[now]) {
			if (visitOrder[adj] == 0 && cycleList[adj] == 0) {
				parent[adj] = now;
				DFS(adj);
				if (findCycle == true) {
					 break;
				}
			} else if (visitOrder[adj] == visitOrder[now] && sameTry[adj] == 1 && cycleList[adj]==0) {
				findCycle = true;
				cycleList[adj] = 1;
				Answer++;
				break;
			} else if (visitOrder[adj] < visitOrder[now] && sameTry[adj] == 1 && cycleList[adj]==0) {
				findCycle = true;
				addCycle(adj, now); // 시작점, 끝점
				break;
			}
		}

		sameTry[now] = 0;

	}

	static void addCycle(int start, int end) {
		// start와 end가 다르고 end가 0이면 0의 부모는 없으로 스택오버플로
		if (start != end && end != 0) {
			addCycle(start, parent[end]);
		}
		cycleList[end] = 1;
		Answer++;
	}
}
