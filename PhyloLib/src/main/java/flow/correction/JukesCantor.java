package flow.correction;

import cli.Parameters;
import data.Context;
import data.matrix.Matrix;

public final class JukesCantor extends Correction {

	public JukesCantor(Context context, Parameters parameters) {
		super(context, parameters);
	}

	@Override
	protected Matrix process() {
		Matrix matrix = context.getMatrix();
		return new Matrix(matrix.size(), (i, j) -> -3 * Math.log(1 - 4 * matrix.get(i, j) / 3) / 4);
	}

}
