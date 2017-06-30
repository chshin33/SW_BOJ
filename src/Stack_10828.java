import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Stack_10828 {

	static int N;
	static final int SIZE = 1000;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("sample/sample_Stack_10828.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		int top = -1;
		int[] stack = new int[SIZE];
		String command;
		int num;
		int DEBUG=0;
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			
			command = st.nextToken();
			
			if(DEBUG==1) System.out.print("command: " + command + " ");
			
			if(command.equals("push")){
				num = Integer.parseInt(st.nextToken());
				if(DEBUG==1) System.out.println("num: " + num);
				top++;
				stack[top] = num;
			}else if(command.equals("pop")){
				if(top==-1){
					System.out.println(-1);
				}else{
					System.out.println(stack[top]);
					top--;
				}
			}else if(command.equals("size")){
				System.out.println(top+1);
			}else if(command.equals("empty")){
				if(top==-1){
					System.out.println(1);
				}else{
					System.out.println(0);
				}
				
			}else if(command.equals("top")){
				if(top==-1){
					System.out.println(-1);
				}else{
					System.out.println(stack[top]);
				}
			}
			
		}

	}
}