import exception.ParameterException;
import flow.Flow;

public class Main {

	public static void main(String[] args) {
		try {
			Flow.process(args);
		} catch (ParameterException e) {
			System.out.println(e.getMessage());
			//System.out.println("How to use");
		}
	}

}
