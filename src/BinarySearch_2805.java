import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BinarySearch_2805 {
	static int N, M;
	static int[] input;
	static int Answer;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("sample/sample_BinarySearch_2805.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		input = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++){
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(input);
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		
		int val;
		st = new StringTokenizer(br.readLine());

		Answer = checkHeight();
		
		System.out.println(Answer);

	}
	
	static int checkHeight(){
		int start=0; 
		int end=N-1;
		int mid;
		int sum=0;
		while(start<=end){
			mid = (start+end)>>1;
		
			for(int i=mid; i<=N; i++){
				sum += input[i] - input[mid]; 
			}
			
			if(sum == M){
				return input[mid];
			}else if(sum < M){
				end = mid -1;
			}else{
				start = mid +1;
			}
		}
		return -(start+1);
	}

}
