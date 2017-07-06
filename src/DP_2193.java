import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_2193 {

	static int type=2;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("sample/sample_DP_2193.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		if(type==1){
			long[] D = new long[N+1];
			
			for(int i=1; i<=N; i++){
				if(i==1 || i==2){
					D[i]=1;
				}else{
					D[i] = D[i-1] + D[i-2]; 
				}
			}
			System.out.println(D[N]);			
		}else{
			long[][] D = new long[N+1][2];
			for(int i=1; i<=N; i++){
				if(i==1){
					D[i][1] = 1; 
					D[i][0] = 0;
				}else{
					D[i][0] = D[i-1][1] + D[i-1][0]; 
					D[i][1] = D[i-1][0];
				}
			}
			
			System.out.println(D[N][0] + D[N][1]);
		}


	}
}