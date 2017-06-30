import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BFS_Wordprocessor {
	
	public static class Word{
		String word;
		int index, findposition, count;
		public Word(String word, int index, int findposition, int count){
			this.word = word;
			this.index = index;
			this.findposition = findposition;
			this.count = count;
		}
	}

	static int DEBUG=0;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("sample/sample_BFS_Wordprocessor.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		String input;
		int N;
		String[] arrWord;
		long startTime = System.currentTimeMillis();
		for (int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine());
			input = st.nextToken();
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			//입력단어 처리
			ArrayDeque<Word> queue = new ArrayDeque<Word>();
			arrWord = new String[N+1];
			String value;			
			for(int i=1; i<=N; i++){
				st = new StringTokenizer(br.readLine());
				value = st.nextToken();
				if(value.length()>1000){
					continue;
				}else{
					arrWord[i] = value;//단어배열에 저장
					if(input.indexOf(value)==0){//단어가 문자열의 첫부분과 매칭되면 큐에 저장
						queue.add(new Word(value, i, 0, 1));
					}
				}
			}
			
			//BFS처리
			int Answer = 9999;
			boolean find = false;
			int startPos = 0;
			int endPos;
			int nextPos=0;
			int nextCount;
			String remain;
			
			while(queue.isEmpty()==false){
				Word now = queue.poll();
				if(DEBUG==1){
					System.out.println("Poll result - word: " + now.word + ", index: " + now.index + ", position: " + now.findposition + ", count: " + now.count);
				}				
				if(now.count >= Answer){//사용단어 개수가 현재까지 구한 최소값 이상이면 skip
					continue;
				}

				startPos = now.findposition + now.word.length();
				endPos = input.length();
				remain = input.substring(startPos, endPos);
				if(DEBUG==1){
					System.out.println("startPos: " + startPos + ", endPos: " + endPos + ", remain: " + remain);
				}				
				//단어들 검색
				for(int i=1; i<=N; i++){
					if(now.index == i){//단어를 중복으로 사용할수 없도록 처리
						continue;
					}
					if(remain.indexOf(arrWord[i])==0){
						nextPos = startPos + arrWord[i].length();
						nextCount = now.count + 1;
						if(nextPos == input.length()){
							Answer = Math.min(Answer, nextCount);
							find = true;
							break;
						}else{
							if(DEBUG==1){
								System.out.println("Add value - arrWord[i]: " + arrWord[i] + ", i: " + i + ", startPos: " + startPos+ ", nextCount: " + nextCount);
							}											
							queue.add(new Word(arrWord[i], i, startPos, nextCount));
						}
					}
				}
			}
			
			if(find == false){
				Answer = -1;
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append("#" + testCase + " " + Answer);
			System.out.println(sb);
		}
		System.out.println("Time: " + (System.currentTimeMillis()-startTime)/1000.0);
	}
}