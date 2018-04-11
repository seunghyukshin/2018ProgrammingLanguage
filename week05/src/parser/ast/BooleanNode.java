package parser.ast;

public class BooleanNode extends Node{
	public boolean value;
	public String toString(){
		return value ? "#T" : "#F";
	}

}
