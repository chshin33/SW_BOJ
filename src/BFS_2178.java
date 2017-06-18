import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BFS_2178 {
	
	public static class Node{
		int row, col;
		public Node(int row, int col){
			this.row = row;
			this.col = col;
		}
	}
	
	static int N, M;
	static int[][] input;
	static int[][] discovered;
	static int DEBUG=0;
	static ArrayDeque<Node> queue;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("sample_BFS_2178.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		input = new int[N+1][M+1];
		discovered = new int[N+1][M+1];
		
		for(int i=1; i<=N; i++){
			String temp = br.readLine();
			for(int j=1; j<=M; j++){
				input[i][j] = temp.charAt(j-1) - '0';
			}
		}
		
		if(DEBUG==1){
			for(int i=1; i<=N; i++){
				for(int j=1; j<=M; j++){
					System.out.print(input[i][j] + "\t");
				}
				System.out.println();
			}
		}
		
		int[] dRow = {-1, 0, 1, 0};
		int[] dCol  = { 0, 1, 0, -1};
		
		queue = new ArrayDeque<Node>();
		queue.add(new Node(1,1));	
		discovered[1][1] = input[1][1]; 
		
		while(!queue.isEmpty()){
			Node now = queue.poll();
			int nextRow, nextCol;
			for(int i=0; i<4;i++){
				nextRow = now.row + dRow[i];
				nextCol = now.col + dCol[i];
				if(nextRow<=0 || nextRow>N || nextCol<=0 || nextCol > M){
					continue;
				}
				if(input[nextRow][nextCol] ==1 && discovered[nextRow][nextCol]==0){
					queue.add(new Node(nextRow, nextCol));
					discovered[nextRow][nextCol] = discovered[now.row][now.col] +1; 
				}
			}
		}
		
		System.out.println(discovered[N][M]);
	}
}
