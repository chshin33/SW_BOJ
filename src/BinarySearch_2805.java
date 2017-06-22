import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BinarySearch_2805 {
	static int N;
	static int M;
	static long[] input;
	static int Answer;
	static int DEBUG = 0;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("sample/sample_BinarySearch_2805.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int max = 0;
		input = new long[N];
		int data=0;
		st = new StringTokenizer(br.readLine());		
		for(int i=0; i<N;i++){
			data = Integer.parseInt(st.nextToken());
			input[i] = data; 
			max = Math.max(max, data);
		}
		
		if(DEBUG==1) System.out.println("max:" + max);
		
		int start=0;
		int end =max;
		int mid;
		long sum;
		while(start<=end){
			mid = (start+end)>>1;
			sum=0;
			for(int i=0; i<N; i++){
				if(input[i]>mid){
					sum += input[i] - mid;
				}
			}
			if(DEBUG==1) System.out.printf("start:%d, end:%d, mid:%d, sum:%d, M:%d\n", start, end, mid, sum, M);
			
			if(sum > M){
				start = mid +1;
				Answer = mid;
			}else if(sum < M){
				end = mid -1;
			}else if(sum==M){
				Answer = mid;
				break;
			}
		}
		
		System.out.println(Answer);
		
	}
}
