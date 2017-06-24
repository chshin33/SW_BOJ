import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BinarySearch_2805WA {
	static int N;
	static long M;
	static long[] input;
	static long[] psum;
	static long Answer;
	static int DEBUG=0;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("sample/sample_BinarySearch_2805.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		input = new long[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++){
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(input);
		
		if(DEBUG==1) System.out.println(Arrays.toString(input));
		
		psum = new long[N];
		psum[0] = input[0];
		for(int i=1; i<N; i++){
			psum[i] = psum[i-1] + input[i];
		}
		
		if(DEBUG==1) System.out.println(Arrays.toString(psum));
		
		Answer = getMinHeight();
		
		System.out.println(Answer);

	}
	
	static long getMinHeight(){
		int start=0; 
		int end=N-1;
		int mid;
		long val;
		int pos = -1;
		int left=0, right=0;
		long retVal=0;
		while(start<=end){
			mid = (start+end)>>1;
			if(DEBUG==1) System.out.println("mid: " + mid);
			val = devideSum(mid, N-1);
			if(DEBUG==1) System.out.printf("devideSum:(%d,%d) = %d\n", mid, N-1, val);
			
			if(val == M){
				pos = mid;
				break;
			}else if(val <M){//왼쪽으로 이동
				end = mid -1;
				right = mid;
			}else if(val > M){//오른쪽으로 이동
				start = mid + 1;
				left = mid;
			}
		}
		
		if(pos > -1){
			retVal = input[pos];
		}else{
			//left와  right사이에 있는 높이가 답이다.
			if(DEBUG==1) System.out.printf("left:%d, right:%d\n", left, right);
			long leftH = input[left];
			long rightH = input[right];
			int sum;
			//for(int h = leftH+1; h<=rightH-1; h++){
			for(long h = leftH; h<=rightH; h++){
				sum = 0;
				for(int i=left; i<=N-1; i++){
					sum +=  Math.max(input[i] - h, 0);
				}
				if(sum ==M){
					retVal = h;
					break;
				}else if(sum < M){
					retVal = h-1;
					break;
				}
			}
		}
		return retVal;
	}
	
	static long devideSum(int start, int end){
		long val;
		val = rsum(start, end);
		val -= input[start] * (end-start+1);
		return val;
	}
	
	static long rsum(int start, int end){
		if(start>=1){
			return psum[end]-psum[start-1];	
		}else{
			return psum[end];
		}
	}

}
