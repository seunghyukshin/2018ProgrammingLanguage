package parser.parse;

//201302422 �Ž���
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;

import lexer.Token;
import lexer.TokenType;
import lexer.Scanner;

import parser.ast.Node;
import parser.ast.QuoteNode;
import parser.ast.IdNode;
import parser.ast.IntNode;
import parser.ast.BinaryOpNode;
import parser.ast.BooleanNode;
import parser.ast.FunctionNode;
import parser.ast.ListNode;

public class CuteParser {
	private Iterator<Token> tokens;
	private static Node END_OF_LIST = new Node() {
	}; // ���� �߰��� �κ�

	public CuteParser(File file) {
		try {
			tokens = Scanner.scan(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Token getNextToken() {
		if (!tokens.hasNext())
			return null;
		return tokens.next();
	}

	public Node parseExpr() {
		Token t = getNextToken();
		if (t == null) {
			System.out.println("No more token");
			return null;
		}
		TokenType tType = t.type();
		String tLexeme = t.lexme();
		switch (tType) {
		case ID:
			IdNode idNode = new IdNode(tLexeme);
			return idNode;
		case INT:
			if (tLexeme == null)
				System.out.println("???");
			IntNode intNode = new IntNode(tLexeme);
			return intNode;
		// BinaryOpNode +, -, /, *�� �ش�
		case DIV:
		case EQ:
		case MINUS:
		case GT:
		case PLUS:
		case TIMES:
		case LT:
			BinaryOpNode binaryNode = new BinaryOpNode();
			binaryNode.setValue(tType);
			return binaryNode;
		// FunctionNode Ű���尡 FunctionNode�� �ش�
		case ATOM_Q:
		case CAR:
		case CDR:
		case COND:
		case CONS:
		case DEFINE:
		case EQ_Q:
		case LAMBDA:
		case NOT:
		case NULL_Q: // ���� ä��� (BinaryOp����)
			// BooleanNode
			FunctionNode functionNode = new FunctionNode();
			functionNode.setValue(tType);
			return functionNode;
		// ���� ������ BooleanNode Case
		case FALSE:
			return BooleanNode.FALSE_NODE;
		case TRUE:
			return BooleanNode.TRUE_NODE;
		// ���� ������ L_PAREN, R_PAREN Case
		case L_PAREN:
			return parseExprList();
		case R_PAREN:
			return END_OF_LIST;
		//���� �߰��� APOSTROPHE, QUOTE	
		case APOSTROPHE:
			return new QuoteNode(parseExpr());
		case QUOTE:
			return new QuoteNode(parseExpr());
		default:
			System.out.println("Parsing Error!");
			return null;
		}
	}

	// List�� value�� �����ϴ� �޼ҵ�
	private ListNode parseExprList() {
		Node head = parseExpr();
		if (head == null)
			return null;
		if (head == END_OF_LIST) // if next token is RPAREN
			return ListNode.ENDLIST;
		ListNode tail = parseExprList();
		if (tail == null)
			return null;
		return ListNode.cons(head, tail);
	}
}