package command.distance;

import cli.Arguments;
import cli.Command;
import command.ICommand;
import data.Context;
import data.dataset.Dataset;
import data.matrix.Matrix;
import exception.MissingInputException;

import java.util.HashMap;

public abstract class Distance implements ICommand<Dataset, Matrix> {

	public static void run(Arguments arguments, Context context) throws MissingInputException {
		ICommand.run(arguments, context, Command.DISTANCE, context::readDataset, context::writeMatrix, new HashMap<>() {{
			put("hamming", new Hamming());
			put("grapetree", new GrapeTree());
			put("kimura", new Kimura());
		}});
	}

}
