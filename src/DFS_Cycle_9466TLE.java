import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DFS_Cycle_9466TLE {
	static int V;
	static ArrayList<Integer>[] adjList;
	static int[] visitOrder;
	static int[] parent;
	static int[] checkSameTry;
	static int visitNumber;
	static boolean findCycle;
	static ArrayList<Integer> cycleList;
	static int DEBUG = 0;
	static int Answer;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("sample/sample_DFS_Cycle_9466.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		for (int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());

			adjList = new ArrayList[V + 1];

			for (int i = 1; i <= V; i++) {
				adjList[i] = new ArrayList<Integer>();
			}

			st = new StringTokenizer(br.readLine());
			int val;
			cycleList = new ArrayList<Integer>();
			for (int i = 1; i <= V; i++) {
				val = Integer.parseInt(st.nextToken());
				/*if (i == val) {
					cycleList.add(i);
				}*/
				adjList[i].add(val);
			}

			if (DEBUG == 1) {
				for (int i = 1; i <= V; i++) {
					bw.write(i + ":" + adjList[i].toString() + "\n");
				}
			}

			visitOrder = new int[V + 1];
			parent = new int[V + 1];
			checkSameTry = new int[V + 1];

			
			findCycle = false;

			Answer = 0;

			Arrays.fill(visitOrder, -1);
			visitNumber = 1;

			for (int i = 1; i <= V; i++) {
				if(visitOrder[i]==-1){
					DFS(i);
				}
			}

			if(DEBUG==1) bw.write(cycleList.toString()+"\n");
			Answer = V - cycleList.size();
			bw.write(Integer.toString(Answer) + "\n");

		}

		bw.flush();
		bw.close();
	}

	static void DFS(int now) {
		visitOrder[now] = visitNumber++;
		checkSameTry[now] = 1;

		if (adjList[now] != null) {
			for (int adj : adjList[now]) {
				if (visitOrder[adj] == -1) {
					parent[adj] = now;
					DFS(adj);
					if (findCycle == true) {
						break;
						//checkSameTry[now] = 0;
						//return;
					}
				} else if (checkSameTry[adj] == 1 && visitOrder[adj] == visitOrder[now]) {
					findCycle = true;
					cycleList.add(now);
					break;
				} else if (checkSameTry[adj] == 1 && visitOrder[adj] < visitOrder[now]) {
					findCycle = true;
					process(adj, now); // 시작점, 끝점
					break;
				}
			}
		}

		checkSameTry[now] = 0;

	}

	static void process(int start, int end) {
		// start와 end가 다르고 end가 0이면 0의 부모는 없으로 스택오버플로
		if (start != end && end != 0) {
			process(start, parent[end]);
		}
		if(cycleList.contains(end)==false){
			cycleList.add(end);
		}
		// System.out.println(end);
	}
}
