package node_max_sum;
//201302422_신승혁
import ast.IntNode;
import ast.ListNode;
import ast.Node;
import compile.TreeFactory;

public class NodeMaxSum {

	//static Node node;
	public static int max(Node node) {
		// 최대값을 리턴하도록 작성
		// value와 next값 중 큰 값을 리턴

		// int a = ((IntNode)node).value;
		// node.getNext();

		int max = Integer.MIN_VALUE;
		do {
			if (node instanceof IntNode) {
				if (((IntNode) node).value > max) {
					max = ((IntNode) node).value;
				}
			} else if (node instanceof ListNode) {
				int tempMax = max(((ListNode) node).value);
				if (tempMax > max) {
					max = tempMax;
				}
			} else {
				// 재귀로 들어올 때 
				break;
			}

			node = node.getNext();
		} while (node != null);

		return max;
	}

	public static int sum(Node node) {
		// 노드 value와 총합을 반환
		// value와 next의 총합을 리턴하면됨
		int sum = 0;
		do {
			if (node instanceof IntNode) {
				sum += ((IntNode) node).value;
			} else if (node instanceof ListNode) {
				int tempSum = sum(((ListNode) node).value);
				sum += tempSum;
			} else {
				break;
			}
			node = node.getNext();
		} while (node != null);

		return sum;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Node node = TreeFactory.createtTree("( ( 3 ( ( 10 ) ) 6 ) 4 1 ( ) -2 ( ) )");
		// Node node = TreeFactory.createtTree("( ( 3 ( ( 10 ) ) 6 ) 4 1 ( ) -2
		// ( ) )");
		System.out.println("최댓값 :"+max(node));
		System.out.println("총합 :"+sum(node));
	}

}
