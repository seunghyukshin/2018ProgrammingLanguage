package lexer;
//201302422 �Ž���

public class ScannerException extends RuntimeException {
	private static final long serialVersionUID = -5564986423129197718L;

	public ScannerException() {
		super();
	}

	public ScannerException(String details) {
		super(details);
	}
}
