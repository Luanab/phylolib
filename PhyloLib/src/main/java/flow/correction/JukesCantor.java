package flow.correction;

import cli.Options;
import data.Context;
import data.matrix.Matrix;

public final class JukesCantor extends Correction {

	public JukesCantor(Context context, Options options) {
		super(context, options);
	}

	@Override
	protected Matrix process() {
		Matrix matrix = context.getMatrix();
		return new Matrix(matrix.size(), (i, j) -> -3 * Math.log(1 - 4 * matrix.get(i, j) / 3) / 4);
	}

}
