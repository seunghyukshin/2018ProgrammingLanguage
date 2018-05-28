package parser.ast;
//201302422 ½Å½ÂÇõ
public class IdNode implements ValueNode {
	// »õ·Î ¼öÁ¤µÈ IdNode Class
	String idString;

	public IdNode(String text) {
		idString = text;
	}

	@Override
	public String toString() {
		return "ID: " + idString;
	}
}
