import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sort_6996 {

	static int T;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("sample/sample_Sort_6996.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		
		String A, B;
		
		for(int testCase=1; testCase<=T; testCase++){
			st = new StringTokenizer(br.readLine());
			A = st.nextToken();
			B = st.nextToken();
			if(countSort(A).equals(countSort(B))==true){
				System.out.println(A + " & " + B + " are anagrams.");
			}else{
				System.out.println(A + " & " + B + " are NOT anagrams.");
			}
		}

	}
	
	static String countSort(String input){
		int[] count = new int[26];
		int[] countSum = new int[26];
		char[] response = new char[input.length()];
		int idx;
		for(int i=0; i<input.length(); i++){
			idx = input.charAt(i) - 'a';
			count[idx] += 1;
		}
		
		countSum[0] = count[0];
		for(int i=1; i<countSum.length; i++){
			countSum[i] = countSum[i-1] + count[i];
		}

		int pos1, pos2;
		for(int i=input.length()-1; i>=0; i--){
			pos1 = input.charAt(i)-'a';
			pos2 = countSum[pos1]-1;
			response[pos2] = input.charAt(i);
			countSum[pos1] -= 1;
		}
		
		return String.valueOf(response);
	}
}