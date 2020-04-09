package command.correction;

import cli.Arguments;
import command.Command;
import command.ICommand;
import data.Context;
import data.matrix.Matrix;
import exception.MissingInputException;

import java.util.HashMap;

public abstract class Correction implements ICommand<Matrix, Matrix> {

	public static void run(Arguments arguments, Context context) throws MissingInputException {
		ICommand.run(arguments, context, Command.CORRECTION, context::getMatrix, context::setMatrix, new HashMap<>() {{
			put("jukescantor", new JukesCantor());
		}});
	}

	@Override
	public final Matrix process(Matrix matrix) {
		int size = matrix.size();
		Double[][] m = new Double[size][size];
		return new Matrix(size, (i, j) -> m[i][j] != null ? m[i][j] : (m[i][j] = correct(matrix.get(i, j))));
	}

	protected abstract double correct(double distance);

}
