package parser.ast;
//201302422 �Ž���
public class BooleanNode extends Node{
	public boolean value;
	public String toString(){
		return value ? "#T" : "#F";
	}

}
