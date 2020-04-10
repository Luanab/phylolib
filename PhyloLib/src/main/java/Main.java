import cli.Arguments;
import command.algorithm.Algorithm;
import command.correction.Correction;
import command.distance.Distance;
import command.optimization.Optimization;
import data.Context;
import logging.Log;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
				try (Stream<String> usage = Files.lines(Paths.get(Main.class.getClassLoader().getResource("usage.txt").toURI()))) {
					System.out.println(usage.collect(Collectors.joining("\n")));
				}
		} catch (Exception e) {
			Log.error(e.getMessage());
		}
	}

}
