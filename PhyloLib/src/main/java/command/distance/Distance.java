package command.distance;

import cli.Arguments;
import command.Command;
import command.ICommand;
import data.Context;
import data.dataset.Dataset;
import data.dataset.Profile;
import data.matrix.Matrix;
import exception.MissingInputException;

import java.util.HashMap;

public abstract class Distance implements ICommand<Dataset, Matrix> {

	public static void run(Arguments arguments, Context context) throws MissingInputException {
		ICommand.run(arguments, context, Command.DISTANCE, context::getDataset, context::setMatrix, new HashMap<>() {{
			put("hamming", new Hamming());
			put("grapetree", new GrapeTree());
			put("kimura", new Kimura());
		}});
	}

	@Override
	public final Matrix process(Dataset dataset) {
		int size = dataset.size();
		Double[][] m = new Double[size][size];
		return new Matrix(size, (i, j) -> m[i][j] != null ? m[i][j] : (m[i][j] = distance(dataset.profile(i), dataset.profile(j))));
	}

	protected abstract double distance(Profile a, Profile b);

}
