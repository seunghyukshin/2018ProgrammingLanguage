package node_max_sum;
//201302422_�Ž���
import ast.IntNode;
import ast.ListNode;
import ast.Node;
import compile.TreeFactory;

public class NodeMaxSum {

	//static Node node;
	public static int max(Node node) {
		// �ִ밪�� �����ϵ��� �ۼ�
		// value�� next�� �� ū ���� ����

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
				// ��ͷ� ���� �� 
				break;
			}

			node = node.getNext();
		} while (node != null);

		return max;
	}

	public static int sum(Node node) {
		// ��� value�� ������ ��ȯ
		// value�� next�� ������ �����ϸ��
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
		System.out.println("�ִ� :"+max(node));
		System.out.println("���� :"+sum(node));
	}

}
