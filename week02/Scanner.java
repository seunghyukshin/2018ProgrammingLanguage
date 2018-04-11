package recognizing_tokens;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Scanner {
	private int transM[][];
	private String source;
	private StringTokenizer st;

	public Scanner(String source) {
		this.transM = new int[4][128];
		this.source = source == null ? "" : source;
		// if(source==null) this.source = ""
		// else this.source = source

		initTM();
	}

	// 구현1
	private void initTM() {
		// transM[4][128] = { {...},{...}, ... , {...} }
		// value of entries : -1, 0, 1, 2, 3 : next state
		// TransM[0]['0'] = 2, ... , TransM[0]['9'] = 2,
		// TransM[0]['-'] = 1,
		// TransM[0]['a'] = 3, ... , TransM[0]['z'] = 3,
		// TransM[1]['0'] = 2, ... , TransM[1]['9'] = 2,
		// TransM[2]['0'] = 2, ... , TransM[2]['9'] = 2,
		// TransM[3]['A'] = 3, ... , TransM[3]['Z'] = 3,
		// TransM[3]['a'] = 3, ... , TransM[3]['z'] = 3,
		// TransM[3]['0'] = 3, ... , TransM[3]['9'] = 3,
		// ...
		// The values of the other entries are all -1
		// for (int i = 0; i < 4; i++)
		// for (int j = 0; j < 128; j++)
		// transM[i][j] = -1;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 128; j++) {
				if (i == 0 && j == '-')
					transM[i][j] = 1;
				else if ('0' <= j && j <= '9') {
					if (i != 3)
						transM[i][j] = 2;
					else
						transM[i][j] = 3;
				}
				else if ((('a' <= j && j <= 'z') | ('A' <= j && j <= 'Z')) && (i == 0 | i == 3))
					transM[i][j] = 3;
				else
					transM[i][j] = -1;
			}
		}
	}
	
	private Token nextToken() {
		int stateOld = 0, stateNew;

		// 토큰이 더 있는지 검사
		if (!st.hasMoreTokens())
			return null;

		// 그 다음 토큰을 받음
		String temp = st.nextToken();

		Token result = null;
		for (int i = 0; i < temp.length(); i++) {
			// 문자열의 문자를 하나씩 가져와 상태 판별
			stateNew = transM[stateOld][temp.charAt(i)];

			if (stateNew == -1) {
				// 입력된 문자의 상태가 reject이므로 에러메시지 출력 후 return 함
				System.out.println(String.format("acceptState error %s\n", temp));
				return null;
			}
			stateOld = stateNew;
		}
		for (TokenType t : TokenType.values()) {
		//for( i; i<TokenType.values().length;i++)
		//	TokenType t = TokenType.values().next;
			if (t.finalState == stateOld) {
				result = new Token(t, temp);
				break;
			}
		}
		return result;
	}
	
	// 구현2
	public List<Token> tokenize() {
		// Token 리스트반환, nextToken()이용
		st = new StringTokenizer(source);
		List<Token> tokenList = new LinkedList<Token>();
		
//		while(st.hasMoreTokens()){	
//			if(transM[0][st.nextToken().charAt(0)]==2)
//				tokenList.add(new Token(TokenType.ID,st.nextToken()));
//			if(transM[0][st.nextToken().charAt(0)]==3)
//				tokenList.add(new Token(TokenType.INT,st.nextToken()));
//		}
//		
		while(st.hasMoreTokens()){
			tokenList.add(nextToken());
		}
		return tokenList;
	}

	// 구현3
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String source = "banana 267 h cat -3789 7 y2010";
		Scanner s = new Scanner(source);
		List<Token> tokens = s.tokenize();
		int i=0;
		while(i<tokens.size()){
//			if(tokens.get(i).type.finalState == 2){
//				System.out.println("int: ");
//				System.out.println(tokens);
//				i++;
//			}
//			else if(tokens.get(i).type.finalState == 3){
//				System.out.print("id: ");
//				System.out.println(tokens.get(i));
//				i++;
//			}
			System.out.println(tokens.get(i));
			i++;
		}
	}

	public enum TokenType {
		ID(3), INT(2);

		private final int finalState;

		TokenType(int finalState) {
			this.finalState = finalState;
		}
	}

	public static class Token {
		public final TokenType type;
		public final String lexme;

		Token(TokenType type, String lexme) {
			this.type = type;
			this.lexme = lexme;
		}

		public String toString() {
			return String.format("[%s: %s]", type.toString(), lexme);
		}
	}

}
