import cli.Arguments;
import data.Context;
import exception.ArgumentException;
import flow.algorithm.Algorithm;
import flow.correction.Correction;
import flow.distance.Distance;
import flow.optimization.Optimization;

import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {
		try {
			Arguments arguments = new Arguments();
			if (arguments.parse(args)) {
				Context context = new Context();
				Distance.run(arguments, context);
				Correction.run(arguments, context);
				Algorithm.run(arguments, context);
				Optimization.run(arguments, context);
			} else
				try (Stream<String> usage = Files.lines(Paths.get("src/main/resources/usage.txt"))) {
					usage.forEach(System.out::println);
				}
		} catch (ArgumentException e) {
			System.err.println(e.getMessage());
		} catch (NoSuchFileException e) {
			System.err.println("Error: No such file by the name of '" + e.getMessage() + "'...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
