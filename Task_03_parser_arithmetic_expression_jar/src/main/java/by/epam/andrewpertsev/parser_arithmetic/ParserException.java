package by.epam.andrewpertsev.parser_arithmetic;

public class ParserException extends Exception {

    private static final long serialVersionUID = 1L;
    private String errStr;

    public ParserException(String errStr) {
        super();
        this.errStr = errStr;
    }

    public String toString() {
        return this.errStr;
    }
}
