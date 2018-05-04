package parser.ast;
//201302422 ½Å½ÂÇõ
public class IntNode extends Node {
	public int value;
	
	public String toString(){
		return "INT: "+Integer.toString(value);
	}
}
