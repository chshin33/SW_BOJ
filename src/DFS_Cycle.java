import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DFS_Cycle {
	static int V;
	static int E;
	static ArrayList<Integer>[] adjList;
	static int[] visitOrder;
	static int[] parent;
	static int[] checkSameTry;
	static int visitNumber;
	static boolean findCycle;
	static ArrayList<Integer> cycleList;
	static int DEBUG = 0;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("sample/sample_DFS_Cycle.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		adjList = new ArrayList[V + 1];

		for (int i = 1; i <= V; i++) {
			adjList[i] = new ArrayList<Integer>();
		}

		int start, end;
		for (int i = 1; i <= E; i++) {
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			adjList[start].add(end);
		}

		if (DEBUG == 1) {
			for (int i = 1; i <= V; i++) {
				bw.write(i + ":" + adjList[i].toString() + "\n");
			}
		}

		visitOrder = new int[V + 1];
		parent = new int[V + 1];
		checkSameTry= new int[V + 1];
		
		visitNumber = 1;
		findCycle = false;
		cycleList = new ArrayList<Integer>();
		for (int i = 1; i <= V; i++) {
			DFS(i);
			if (findCycle == true) {
				break;
			}
		}
		
		if(findCycle==true){
			bw.write("Cycle exist : " + cycleList.toString());
			
		}else{
			bw.write("No Cycle.");
		}
		
		bw.flush();
		bw.close();
	}

	static void DFS(int now) {
		visitOrder[now] = visitNumber++;
		checkSameTry[now] = 1;
		
		if (adjList[now].size() == 0) {
			checkSameTry[now]=0;
			return;
		}
		
		for (int adj : adjList[now]) {
			if (visitOrder[adj] == 0) {
				parent[adj] = now;
				DFS(adj);
		  		if (findCycle == true) {
					break;
				}
			}else if(checkSameTry[adj] ==1 && visitOrder[adj] < visitOrder[now]){
				findCycle = true;
				printPath(adj, now); //시작점, 끝점
				return;
			}
		}
		
		checkSameTry[now]=0;
		
	}
	
	static void printPath(int start, int end){
		if(start !=end){
			printPath(start, parent[end]);
		}
		cycleList.add(end);
		//System.out.println(end);
	}
}
