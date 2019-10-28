package exception;

public class NumberOfArgumentsException extends ParameterException {

    public NumberOfArgumentsException(String name, String type, int number, int size) {
        super("The number of arguments for parameter " + name + " of type " + type +
                " should be at least " + number + " instead got " + size + "...");
    }

}
