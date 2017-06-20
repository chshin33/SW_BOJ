import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BinarySearch_10815 {
	static int N, M;
	static int[] input;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("sample/sample_BinarySearch_10815.txt"));
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
		for(int i=1; i<=M; i++){
			val = checkExist(Integer.parseInt(st.nextToken()));
			if(val>=0)
				System.out.print(1+" ");
			else
				System.out.print(0+" ");
		}
	}
	
	static int checkExist(int num){
		int start=0; 
		int end=N-1;
		int mid;
		while(start<=end){
			mid = (start+end)>>1;
			if(num == input[mid]){
				return mid;
			}else if(num < input[mid]){
				end = mid -1;
			}else{
				start = mid +1;
			}
		}
		return -(start+1);
	}

}
