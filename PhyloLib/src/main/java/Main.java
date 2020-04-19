import cli.Arguments;
import command.Command;
import command.ICommand;
import data.Context;
import exception.ArgumentException;
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
				ICommand.run(arguments, context, Command.DISTANCE, context::getDataset, context::setMatrix);
				ICommand.run(arguments, context, Command.CORRECTION, context::getMatrix, context::setMatrix);
				ICommand.run(arguments, context, Command.ALGORITHM, context::getMatrix, context::setTree);
				ICommand.run(arguments, context, Command.OPTIMIZATION, context::getTree, context::setTree);
			} else
				try (Stream<String> usage = Files.lines(Paths.get(Main.class.getClassLoader().getResource("usage.txt").toURI()))) {
					System.out.println(usage.collect(Collectors.joining("\n")));
				}
		} catch (ArgumentException exception) {
			Log.error(exception.getMessage());
		} catch (Exception exception) {
			Log.exception(exception);
		}
	}

}
