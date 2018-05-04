package parser.parse;
//201302422 신승혁
import java.io.PrintStream;

import parser.ast.ListNode;
import parser.ast.Node;
import parser.ast.QuoteNode;

public class NodePrinter {
	PrintStream ps;

	public static NodePrinter getPrinter(PrintStream ps) {
		return new NodePrinter(ps);
	}

	private NodePrinter(PrintStream ps) {
		this.ps = ps;
	}

	// ListNode, QuoteNode, Node에 대한 printNode 함수를 각각 overload 형식으로 작성
	private void printNode(ListNode listNode) {
		if (listNode == ListNode.EMPTYLIST) {
			ps.print("( ) ");
			return;
		}
		if (listNode == ListNode.ENDLIST) {
			return;
		}
		ps.print("( ");
		if(listNode.car() instanceof QuoteNode){
			printNode(listNode.car());
		}else if(listNode.car() instanceof ListNode){
			printNode(listNode.car());
		}else{
			ps.print("[ ");
			ps.print(listNode.car());
			ps.print("] ");					
		}

		printNode(listNode.cdr());
		ps.print(") ");
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
		if (node instanceof ListNode){
			ListNode ln = (ListNode) node;
			printNode(ln);
		}
		else if (node instanceof QuoteNode){
			QuoteNode qn = (QuoteNode) node;
			printNode(qn);
		}
		else {
			ps.print("[" + node.toString() + "] ");
		}
		//printNode();
	}

	public void prettyPrint(Node node) {
		printNode(node);
	}
}