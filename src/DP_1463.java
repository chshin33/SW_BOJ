import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP_1463 {
	static int DEBUG=0;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("sample/sample_DP_1463.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int[] D = new int[N + 1];

		D[1] = 0;
		for (int i = 2; i <= N; i++) {
			if(i%2==0 && i%3==0){
				D[i] = Math.min(Math.min(D[i/2]+1,D[i/3]+1), D[i-1]+1);	
			}else if(i%2==0){
				D[i] = Math.min(D[i/2]+1,D[i-1]+1);
			}else if(i%3==0){
				D[i] = Math.min(D[i/3]+1,D[i-1]+1);
			}else{
				D[i] = D[i-1]+1;
			}
		}

		if(DEBUG==1) {
			for(int i=1; i<=N; i++){
				System.out.println(i + " : " + D[i]);	
			}
		}

		System.out.println(D[N]);

	}
}