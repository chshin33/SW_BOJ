import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sort_1919 {

	static int T;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("sample/sample_Sort_1919.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String A, B;
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = st.nextToken(); 
		st = new StringTokenizer(br.readLine());
		B = st.nextToken();
		
		int[] checkA = new int[26];
		int[] checkB = new int[26];
		
		for(int i=0; i<A.length(); i++){
			checkA[A.charAt(i)-'a'] += 1;
		}
		
		for(int i=0; i<B.length(); i++){
			checkB[B.charAt(i)-'a'] += 1;
		}
		
		int Answer = 0;
		for(int i=0; i<26; i++){
			Answer += Math.abs(checkA[i] - checkB[i]);
		}
		
		System.out.println(Answer);
	}
}