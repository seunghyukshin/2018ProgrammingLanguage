package linkedlist;

//201302422_신승혁
public class RecursionLinkedList {
	private Node head;
	private static char UNDEF = Character.MIN_VALUE;

	private void linkFirst(char element) {
		head = new Node(element, head);
	}

	// (1)주어진 Node x의 마지막으로 연결된 Node의 다음으로 새롭게 생성된 노드를 연결
	// add에 영향 // x는 3번째 노드
	private void linkLast(char element, Node x) {
		if (x.next == null)
			x.next = new Node(element, null);
		else
			linkLast(element, x.next);
	}

	// 이전 Node의 다음 Node로 새롭게 생성된 노드를 연결
	private void linkNext(char element, Node pred) {
		Node next = pred.next;
		pred.next = new Node(element, next);
	}

	// 리스트의 첫번째 원소 삭제
	private char unlinkFirst() {
		Node x = head;
		char element = x.item;
		head = head.next;
		x.item = UNDEF;
		x.next = null;
		return element;
	}

	// 이전 Node의 다음Node 연결 해제(삭제)
	private char unlinkNext(Node pred) {
		Node x = pred.next;
		Node next = x.next;
		char element = x.item;
		x.item = UNDEF;
		x.next = null;
		pred.next = next;
		return element;
	}

	// (2)x노드에서 index만큼 떨어진 Node 반환 O
	private Node node(int index, Node x) {
		if (index == 0)
			return x;
		index--;
		Node nextNode = x.next;
		return node(index, nextNode);
	}

	// (3)노드로부터 끝까지의 리스트의 노드갯수 반환 O
	private int length(Node x) {
		if (x == null)
			return 0;
		Node nextNode = x.next;
		return length(nextNode) + 1;
	}

	// (4)노드로부터 시작하는 리스트의 내용 반환 O
	private String toString(Node x) {
		if (x.next == null)
			return x.item + "";
		return x.item + toString(x.next);
	}

	// (plus)현재노드의 이전 노드부터 리스트의 끝까지 거꾸로만듦
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
