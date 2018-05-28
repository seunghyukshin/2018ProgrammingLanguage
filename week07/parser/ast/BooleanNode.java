package parser.ast;
//201302422 ½Å½ÂÇõ
public class BooleanNode implements ValueNode {
	// »õ·Î ¼öÁ¤µÈ BooleanNode Class
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
