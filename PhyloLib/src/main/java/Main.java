import cli.Arguments;
import cli.Command;
import command.ICommand;
import data.Context;
import exception.ArgumentException;
import logging.Log;

import java.io.InputStream;

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
				try (InputStream usage = Main.class.getClassLoader().getResourceAsStream("usage.txt")) {
					System.out.write(usage.readAllBytes());
					System.out.flush();
				}
		} catch (ArgumentException exception) {
			Log.error(exception.getMessage());
		} catch (Exception exception) {
			Log.exception(exception);
		}
	}

}
