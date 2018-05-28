package parser.ast;
//201302422 �Ž���
public class BooleanNode implements ValueNode {
	// ���� ������ BooleanNode Class
	private Boolean value;

	@Override
	public String toString() {
		return value ? "#T" : "#F";
	}

	public static BooleanNode FALSE_NODE = new BooleanNode(false);
	public static BooleanNode TRUE_NODE = new BooleanNode(true);

	private BooleanNode(Boolean b) {
		value = b;
	}
}
