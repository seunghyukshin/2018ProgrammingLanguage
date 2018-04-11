package parser.parse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;

import lexer.Token;
import lexer.TokenType;
import lexer.Scanner;

import parser.ast.Node;
import parser.ast.IdNode;
import parser.ast.IntNode;
import parser.ast.BinaryOpNode;
import parser.ast.BooleanNode;
import parser.ast.ListNode;

public class CuteParser {
	private Iterator<Token> tokens;
	public CuteParser(File file) {
		// TODO Auto-generated constructor stub
		try{
			tokens = Scanner.scan(file);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}
	private Token getNextToken(){
		if(!tokens.hasNext()){
			return null;
		}
		return tokens.next();
	}
	public Node parseExpr(){
		Token t = getNextToken();
		if(t == null){
			System.out.println("No more token");
			return null;
		}
		TokenType tType = t.type();
		String tLexeme = t.lexme();
		switch(tType){
		case ID:
			IdNode idNode = new IdNode();
			idNode.value = tLexeme;
			return idNode;
		case INT:
			IntNode intNode = new IntNode();
			if(tLexeme == null){
				System.out.println("???");
			}
			intNode.value = new Integer(tLexeme);
			return intNode;
		// BinaryOpNode +,-,/,*가 해당
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
		// FunctionNode 키워드가 FunctionNode에 해당
		case ATOM_Q:
		case CAR:
		case CDR:
		case COND:
		case CONS:
		case DEFINE:
		case EQ_Q:
		case LAMBDA:
		case NOT:
		case NULL_Q:
			//내용채우기(BinaryOp 참고)
			
		// BooleanNode
		case FALSE:
			BooleanNode falseNode = new BooleanNode();
			falseNode.value = false;
			return falseNode;
		case TRUE:
			BooleanNode trueNode = new BooleanNode();
			trueNode.value = true;
			return trueNode;
			//case L_PAREN일 경우와 case R_PAREN일 경우
			// L_PAREN일 경우 parseExprList()를 호출하여 처리
		case L_PAREN:
			ListNode listNode = new ListNode();
			listNode.value = parseExpr();
			return listNode;
		case R_PAREN:
			return null;
		default:
			//head의 next를 만들고 head를 반환하도록 작성
			System.out.println("Parsing Error!");
			return null;
		}
	}
	//List의 value를 생성하는 메소드
	private Node parseExprList(){
		Node head = parseExpr();
		// head의 next노드를 set하시오
		if(head == null){ //if next token is RPAREN
			return null;
		}
		head.setNext(parseExprList());
		return head;
	}
}
