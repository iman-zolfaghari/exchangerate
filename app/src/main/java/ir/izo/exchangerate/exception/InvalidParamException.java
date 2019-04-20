package ir.izo.exchangerate.exception;

public class InvalidParamException extends RuntimeException {
	public InvalidParamException(String pattern, Object... params) {
		super(String.format(pattern, params));
	}
}
