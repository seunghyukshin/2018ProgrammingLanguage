package interpreter;

//201302422 신승혁
import java.util.Scanner;

import parser.ast.*;
import parser.parse.*;

public class CuteInterpreter {
	private void errorLog(String err) {
		System.out.println(err);
	}

	public Node runExpr(Node rootExpr) {
		if (rootExpr == null)
			return null;

		if (rootExpr instanceof IdNode)
			return rootExpr;
		else if (rootExpr instanceof IntNode)
			return rootExpr;
		else if (rootExpr instanceof BooleanNode)
			return rootExpr;
		else if (rootExpr instanceof ListNode)
			return runList((ListNode) rootExpr);
		else
			errorLog("run Expr error");
		return null;
	}

	private Node runList(ListNode list) {
		if (list.equals(ListNode.EMPTYLIST))
			return list;
		if (list.car() instanceof FunctionNode) {
			return runFunction((FunctionNode) list.car(), list.cdr());
		}
		if (list.car() instanceof BinaryOpNode) {
			return runBinary(list);
		}
		return list;
	}

	private Node runFunction(FunctionNode operator, ListNode operand) {
		switch (operator.value) {
		// CAR, CDR, CONS등에 대한 동작 구현
		case CAR:
			ListNode lnCar = (ListNode) runQuote((ListNode) operand);
			return lnCar.car();
		// return runQuote((ListNode)operand).car();

		case CDR:
			ListNode lnCdr = (ListNode) runQuote((ListNode) operand);
			// return lnCdr.cdr();
			return new QuoteNode(lnCdr.cdr());

		case CONS:
			ListNode lnConsTail = (ListNode) runQuote((ListNode) operand.cdr());

			if (!(operand.car() instanceof QuoteNode)) { // car의 원소가 atom인 경우
				// return operand.car();
				// return operand.car()lnCar.;
				return new QuoteNode(ListNode.cons(operand.car(), lnConsTail));
			} else { // car의 원소가 list인 경우
				ListNode lnConsHead = (ListNode) runQuote((ListNode) operand);
				// return lnConsHead;
				// return operand.car();
				return new QuoteNode(ListNode.cons(lnConsHead, lnConsTail));
			}

		case COND: // 우선순위 1:CAR의 #T 2:CDR의 #T 3:?
			ListNode lnCondHead = (ListNode) operand.car(); // (#F 0 )
			ListNode lnCondTail = (ListNode) operand.cdr().car(); // ( #T 1 )
			Node condHeadCar = lnCondHead.car();
			Node condTailCar = lnCondTail.car();
			// #T 대신 ListNode
			if (lnCondHead.car() instanceof ListNode) {
				condHeadCar = runBinary((ListNode) condHeadCar);
			}
			if (lnCondTail.car() instanceof ListNode) {
				condTailCar = runBinary((ListNode) condTailCar);
			}

			// firstNode.car(); //#F
			if ((condHeadCar.toString()).equals("#T")) {
				return lnCondHead.cdr().car();
			} else if ((condTailCar.toString()).equals("#T")) {
				return lnCondTail.cdr().car();
			}

			return null; // 둘다 #F일때

		case NOT:
			if ((operand.car() instanceof BooleanNode)) {
				if (operand.car() == BooleanNode.TRUE_NODE) {
					return BooleanNode.FALSE_NODE;
				} else {
					return BooleanNode.TRUE_NODE;
				}
			} else { // list형태 not ( < 3 5 )
				// list로나와서 runbinary로 나온값을 가져옴
				if (runBinary((ListNode) operand.car()) == BooleanNode.TRUE_NODE) {
					return BooleanNode.FALSE_NODE;
				} else {
					return BooleanNode.TRUE_NODE;
				}
			}

		case NULL_Q: // ( null? ' ( 1 2 ) )
			ListNode NullQNode = (ListNode) runQuote(operand); // ( 1 2 )
			if (NullQNode.car() == null) {
				return BooleanNode.TRUE_NODE;
			}
			return BooleanNode.FALSE_NODE;

		case ATOM_Q: // ( atom? ' ( 1 2 ) )
			// operand; // ( ' ( 1 2 ) )
			// operand.car(); // ' ( 1 2 )
			Node tempNode = runQuote(operand); // ( 1 2 )
			if (tempNode instanceof ListNode) {
				return BooleanNode.FALSE_NODE;
			} else {
				return BooleanNode.TRUE_NODE;
			}

		case EQ_Q: // eq? ' a ' a quote다음 왜 atom??
			Node eqHeadNode = runQuote(operand);
			Node eqTailNode = runQuote(operand.cdr());
			// return eqTailNode;
			if (eqHeadNode instanceof ListNode && eqTailNode instanceof ListNode) {

				ListNode lnEQHead = (ListNode) eqHeadNode;
				ListNode lnEQTail = (ListNode) eqTailNode;
				while (true) {
					if (!(lnEQHead.car().toString()).equals(lnEQTail.car().toString())) {
						return BooleanNode.FALSE_NODE;
					}
					lnEQHead = lnEQHead.cdr();
					lnEQTail = lnEQTail.cdr();
					if (lnEQHead == lnEQTail) {
						return BooleanNode.TRUE_NODE;
					}
				}
			} else {
				if (eqHeadNode.toString().equals(eqTailNode.toString())) { // 둘다
																			// atom이면서
																			// 값같
					return BooleanNode.TRUE_NODE;
				} else { // 한쪽이 listNode
					return BooleanNode.FALSE_NODE;
				}
			}
		default:
			break;
		}
		return null;
	}

	private Node runBinary(ListNode list) {
		BinaryOpNode operator = (BinaryOpNode) list.car();
		// 구현과정에서 필요한 변수 및 함수 작업 가능
		IntNode firstNode = null;
		IntNode secondNode = null;

		if (list.cdr().car() instanceof IntNode && list.cdr().cdr().car() instanceof IntNode) {
			firstNode = (IntNode) list.cdr().car();
			secondNode = (IntNode) list.cdr().cdr().car();
		} else if (list.cdr().car() instanceof ListNode && !(list.cdr().cdr().car() instanceof ListNode)) {
			firstNode = (IntNode) runBinary((ListNode) list.cdr().car());
			secondNode = (IntNode) list.cdr().cdr().car();
		} else if (list.cdr().cdr().car() instanceof ListNode && !(list.cdr().car() instanceof ListNode)) {
			firstNode = (IntNode) list.cdr().car();
			secondNode = (IntNode) runBinary((ListNode) list.cdr().cdr().car());
		} else {
			firstNode = (IntNode) runBinary((ListNode) list.cdr().car());
			secondNode = (IntNode) runBinary((ListNode) list.cdr().cdr().car());
		}

		switch (operator.value) {

		// +,-,/ 등에 대한 바이너리 연산 동작 구현
		case PLUS:
			int plusFirst = firstNode.getValue();
			int plusSecond = secondNode.getValue();
			int plusTotal = plusFirst + plusSecond;

			return new IntNode(String.valueOf(plusTotal));

		case MINUS:
			int minusFirst = firstNode.getValue();
			int minusSecond = secondNode.getValue();
			int minusTotal = minusFirst - minusSecond;

			return new IntNode(String.valueOf(minusTotal));

		case DIV: // /
			int divFirst = firstNode.getValue();
			int divSecond = secondNode.getValue();
			int divTotal = divFirst / divSecond;

			return new IntNode(String.valueOf(divTotal));

		case TIMES: // *
			int timesFirst = firstNode.getValue();
			int timesSecond = secondNode.getValue();
			int timesTotal = timesFirst * timesSecond;

			return new IntNode(String.valueOf(timesTotal));

		case LT: // <
			int LTFirst = firstNode.getValue();
			int LTSecond = secondNode.getValue();

			Boolean LTTotal = (LTFirst < LTSecond); // false
			if (LTTotal) {
				return BooleanNode.TRUE_NODE;
			} else {
				return BooleanNode.FALSE_NODE;
			}

		case GT: // ( > 1 5)
			int GTFirst = firstNode.getValue();
			int GTSecond = secondNode.getValue();

			Boolean GTTotal = (GTFirst > GTSecond); // false
			if (GTTotal) {
				return BooleanNode.TRUE_NODE;
			} else {
				return BooleanNode.FALSE_NODE;
			}

		case EQ: // =
			int EQFirst = firstNode.getValue();
			int EQSecond = secondNode.getValue();

			Boolean EQTotal = (EQFirst == EQSecond);
			if (EQTotal) {
				return BooleanNode.TRUE_NODE;
			} else {
				return BooleanNode.FALSE_NODE;
			}
		default:
			break;
		}
		return null;
	}

	private Node runQuote(ListNode node) {
		return ((QuoteNode) node.car()).nodeInside();
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while (true) {
			System.out.print("> ");
			String input = scan.nextLine();
			CuteParser cuteParser = new CuteParser(input);
			Node parseTree = cuteParser.parseExpr();
			CuteInterpreter i = new CuteInterpreter();
			Node resultNode = i.runExpr(parseTree);
			System.out.print("...");
			NodePrinter.getPrinter(System.out).prettyPrint(resultNode);
			System.out.println();
		}
	}
}
