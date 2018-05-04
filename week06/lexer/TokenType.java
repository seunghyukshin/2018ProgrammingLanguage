package lexer;
//201302422 �Ž���
public enum TokenType {
	INT, ID, QUESTION, TRUE, FALSE, NOT, PLUS, MINUS, TIMES, DIV, LT, GT, EQ, APOSTROPHE, L_PAREN, R_PAREN, DEFINE, LAMBDA, COND, QUOTE, CAR, CDR, CONS, ATOM_Q, NULL_Q, EQ_Q;

	static TokenType fromSpecialCharactor(char ch) {
		switch (ch) {
		// ����ǥ������ �����Ͽ� ch�� ��Ī�Ǵ� keyword�� ��ȯ�ϴ� case�� �ۼ�
		case '(':
			return TokenType.L_PAREN;
		case ')':
			return TokenType.R_PAREN;
		case '+':
			return TokenType.PLUS;
		case '-':
			return TokenType.MINUS;
		case '*':
			return TokenType.TIMES;
		case '/':
			return TokenType.DIV;
		case '<':
			return TokenType.LT;
		case '=':
			return TokenType.EQ;
		case '>':
			return TokenType.GT;
		case '\'':
			return TokenType.APOSTROPHE;
			
		default:
			throw new IllegalArgumentException("unregistered char:" + ch);
		}
	}
}
