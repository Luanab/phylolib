package command.correction;

import command.ICommand;
import data.matrix.Matrix;

public abstract class Correction implements ICommand<Matrix, Matrix> {

	@Override
	public final Matrix process(Matrix matrix) {
		return new Matrix(matrix.ids(), (i, j) -> correct(matrix.get(i, j)));
	}

	protected abstract double correct(double distance);

}
