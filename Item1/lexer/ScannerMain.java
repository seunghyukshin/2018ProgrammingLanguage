package lexer;
//201302422 �Ž���
import java.io.File;

public class ScannerMain {
    public static final void main(String... args) throws Exception {
        ClassLoader cloader = ScannerMain.class.getClassLoader();
        File file = new File(cloader.getResource("lexer/as02.txt").getFile());
        //testTokenStream(file);  
    }
    
    // use tokens as a Stream 
   /* private static void testTokenStream(File file) throws FileNotFoundException {	
        Stream<Token> tokens = Scanner.stream(file);
        tokens.map(ScannerMain::toString).forEach(System.out::println);
    }*/    
    
    private static String toString(Token token) {
        return String.format("%-3s: %s", token.type().name(), token.lexme());
    }
    
}
