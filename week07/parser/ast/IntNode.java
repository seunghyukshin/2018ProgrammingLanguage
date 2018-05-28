package parser.ast;
//201302422 �Ž���
public class IntNode implements ValueNode { // ���� ������ IntNode
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
