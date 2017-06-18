import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_4781 {
	static int N;
	static int M;
	static int[] P;
	static int[] C;
	static int[] B;
	static int DEBUG=0;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("sample_DP_4781.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true){
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = (int)Math.round((Double.parseDouble(st.nextToken()) * 100));
			
			if(N==0 && M==0){
				break;
			}
		
			C = new int[N+1];
			P = new int[N+1];
			for(int i=1; i<=N; i++){
				st = new StringTokenizer(br.readLine());
				C[i] = Integer.parseInt(st.nextToken());
				P[i] = (int)Math.round((Double.parseDouble(st.nextToken()) * 100));
			}
			
			B = new int[M+1];
			
			int maxVal = 0;
			B[0] = 0;
			for(int j=1; j<=M; j++){
				for(int i=1; i<=N; i++){
					if(j>=P[i]){
						maxVal = Math.max(maxVal, B[j-P[i]] + C[i]);	
					}
				}
				B[j] = Math.max(B[j-1], maxVal);
			}
			System.out.println(B[M]);	
		}
	}
}
