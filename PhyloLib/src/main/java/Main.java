import cli.Arguments;
import cli.Logger;
import command.algorithm.Algorithm;
import command.correction.Correction;
import command.distance.Distance;
import command.optimization.Optimization;
import data.Context;
import exception.MissingInputException;

public class Main {

	public static void main(String[] args) {
		try {
			Arguments arguments = Arguments.parse(args);
			if (arguments != null) {
				Context context = new Context();
				Distance.run(arguments, context);
				Correction.run(arguments, context);
				Algorithm.run(arguments, context);
				Optimization.run(arguments, context);
			} else
				Logger.usage();
		} catch (MissingInputException e) {
			Logger.error(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
