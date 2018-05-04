package parser.ast;
//201302422 ½Å½ÂÇõ
public class BooleanNode extends Node{
	public boolean value;
	public String toString(){
		return value ? "#T" : "#F";
	}

}
