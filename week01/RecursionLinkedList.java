package linkedlist;

//201302422_�Ž���
public class RecursionLinkedList {
	private Node head;
	private static char UNDEF = Character.MIN_VALUE;

	private void linkFirst(char element) {
		head = new Node(element, head);
	}

	// (1)�־��� Node x�� ���������� ����� Node�� �������� ���Ӱ� ������ ��带 ����
	// add�� ���� // x�� 3��° ���
	private void linkLast(char element, Node x) {
		if (x.next == null)
			x.next = new Node(element, null);
		else
			linkLast(element, x.next);
	}

	// ���� Node�� ���� Node�� ���Ӱ� ������ ��带 ����
	private void linkNext(char element, Node pred) {
		Node next = pred.next;
		pred.next = new Node(element, next);
	}

	// ����Ʈ�� ù��° ���� ����
	private char unlinkFirst() {
		Node x = head;
		char element = x.item;
		head = head.next;
		x.item = UNDEF;
		x.next = null;
		return element;
	}

	// ���� Node�� ����Node ���� ����(����)
	private char unlinkNext(Node pred) {
		Node x = pred.next;
		Node next = x.next;
		char element = x.item;
		x.item = UNDEF;
		x.next = null;
		pred.next = next;
		return element;
	}

	// (2)x��忡�� index��ŭ ������ Node ��ȯ O
	private Node node(int index, Node x) {
		if (index == 0)
			return x;
		index--;
		Node nextNode = x.next;
		return node(index, nextNode);
	}

	// (3)���κ��� �������� ����Ʈ�� ��尹�� ��ȯ O
	private int length(Node x) {
		if (x == null)
			return 0;
		Node nextNode = x.next;
		return length(nextNode) + 1;
	}

	// (4)���κ��� �����ϴ� ����Ʈ�� ���� ��ȯ O
	private String toString(Node x) {
		if (x.next == null)
			return x.item + "";
		return x.item + toString(x.next);
	}

	// (plus)�������� ���� ������ ����Ʈ�� ������ �Ųٷθ���
	private void reverse(Node x, Node pred) {

	}

	public boolean add(char element) {
		if (head == null) {
			linkFirst(element);
		} else {
			linkLast(element, head);
		}
		return true;
	}

	public void add(int index, char element) {
		if (!(index >= 0 && index <= size()))
			throw new IndexOutOfBoundsException("" + index);
		if (index == 0)
			linkFirst(element);
		else
			linkNext(element, node(index - 1, head));
	}

	public char get(int index) {
		if (!(index >= 0 && index < size()))
			throw new IndexOutOfBoundsException("" + index);
		return node(index, head).item;
	}

	public char remove(int index) {
		if (!(index >= 0 && index < size()))
			throw new IndexOutOfBoundsException("" + index);
		if (index == 0)
			return unlinkFirst();
		return unlinkNext(node(index - 1, head));
	}

	public void reverse() {
		reverse(head, null);
	}

	public int size() {
		return length(head);
	}

	public String toString() {
		if (head == null)
			return "[]";
		return "[" + toString(head) + "]";
	}

	private static class Node {
		char item;
		Node next;

		Node(char element, Node next) {
			this.item = element;
			this.next = next;
		}
	}
}
