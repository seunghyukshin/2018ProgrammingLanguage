package parser.ast;
//201302422 �Ž���
public class IntNode extends Node {
	public int value;
	
	public String toString(){
		return "INT: "+Integer.toString(value);
	}
}
