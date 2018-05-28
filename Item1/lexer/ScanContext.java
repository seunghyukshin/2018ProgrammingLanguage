package lexer;
//201302422 ½Å½ÂÇõ

class ScanContext {
	private final CharStream input;
	private StringBuilder builder;
	
	ScanContext(String input) {
		this.input = CharStream.from(input);
		this.builder = new StringBuilder();
	}
	
	CharStream getCharStream() {
		return input;
	}
	
	String getLexime() {
		String str = builder.toString();
		builder.setLength(0);
		return str;
	}
	
	void append(char ch) {
		builder.append(ch);
	}
}
