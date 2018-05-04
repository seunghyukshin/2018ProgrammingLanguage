package parser.parse;

//201302422 ½Å½ÂÇõ
import java.io.*;

public class ParserMain {

	public static final void main(String... args) throws Exception {
		ClassLoader cloader = ParserMain.class.getClassLoader();
		File file = new File(cloader.getResource("parser/as07.txt").getFile());
		CuteParser cuteParser = new CuteParser(file);
		NodePrinter.getPrinter(System.out).prettyPrint(cuteParser.parseExpr());
	}
	/*
	 * ' ( + 2 3 ) ( quote ( + 2 3 ) ) ( cons 1 ' ( 2 3 4 ) ) ( + ( length ' ( 2
	 * 3 16 ) ) -378 )
	 */
}
