package parser.ast;
//201302422 ½Å½ÂÇõ
public class IntNode implements ValueNode { // »õ·Î ¼öÁ¤µÈ IntNode
	private Integer value;

	@Override
	public String toString() {
		return "INT: " + value;
	}
	
	public IntNode(String text) {
		this.value = new Integer(text);
	}
	
	public Integer getValue(){
		return value;
	}
}
