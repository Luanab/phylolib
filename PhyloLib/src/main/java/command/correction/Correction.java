package command.correction;

import command.ICommand;
import data.matrix.Matrix;

public abstract class Correction implements ICommand<Matrix, Matrix> {

	@Override
	public final Matrix process(Matrix matrix) {
		return matrix.correct(this::correct);
	}

	protected abstract double correct(double distance);

}
