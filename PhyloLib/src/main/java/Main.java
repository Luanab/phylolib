import cli.Arguments;
import cli.Logger;
import data.Context;
import exception.ArgumentException;
import flow.algorithm.Algorithm;
import flow.correction.Correction;
import flow.distance.Distance;
import flow.optimization.Optimization;

import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;

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
		} catch (ArgumentException e) {
			Logger.error(e.getMessage(), true);
		} catch (InvalidPathException e) {
			Logger.error(e.getMessage(), false);
		} catch (NoSuchFileException e) {
			Logger.error("No such file by the name of '" + e.getMessage() + "'", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
