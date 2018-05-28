package parser.parse;

//201302422 �Ž���
import java.io.PrintStream;
import java.util.StringTokenizer;

import parser.ast.*;

public class NodePrinter {
	private PrintStream ps;

	public static NodePrinter getPrinter(PrintStream ps) {
		return new NodePrinter(ps);
	}

	private NodePrinter(PrintStream ps) {
		this.ps = ps;
	}

	// ListNode, QuoteNode, Node�� ���� printNode �Լ��� ���� overload �������� �ۼ�
	private void printNode(ListNode listNode) {
		if (listNode.equals(ListNode.EMPTYLIST)) {
			ps.print("( )");
			return;
		}
		if (listNode.equals(ListNode.ENDLIST)) {
			return;
		}
		printNode(listNode.car());
		if (listNode.cdr().equals(ListNode.EMPTYLIST)) {
			ps.print(" ");
		}
		printNode(listNode.cdr());
	}

	private void printNode(QuoteNode quoteNode) {
		if (quoteNode.nodeInside() == null)
			return;
		ps.print("'");
		printNode(quoteNode.nodeInside());
	}

	private void printNode(Node node) {
		if (node == null)
			return;
		if (node instanceof ListNode) {
			ps.print("(");
			printNode((ListNode) node);
			ps.print(" )");
		} else if (node instanceof QuoteNode) {
			printNode((QuoteNode) node);
		} else if(node instanceof BooleanNode){ //�̹��� �����߰�
			String temp = node.toString();
			ps.print(temp);
		}
		else {
			String temp = node.toString();
			StringTokenizer st = new StringTokenizer(temp, " ");
			st.nextToken();
			ps.print(" " + st.nextToken());
		}
	}

	public void prettyPrint(Node node){
		printNode(node);
	}
}