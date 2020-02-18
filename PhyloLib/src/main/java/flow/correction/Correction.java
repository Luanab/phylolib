package flow.correction;

import cli.Arguments;
import cli.Parameters;
import data.Context;
import data.matrix.IMatrixFormatter;
import data.matrix.Matrix;
import exception.ArgumentException;
import flow.Component;

import java.io.IOException;
import java.util.HashMap;

public abstract class Correction extends Component<Matrix> {

	public static final String NAME = "correction";

	protected Correction(Context context, Parameters parameters) {
		super(context, context::setMatrix, IMatrixFormatter::get, parameters);
		this.input |= MATRIX;
	}

	public static void run(Arguments arguments, Context context) throws ArgumentException, IOException {
		Component.run(arguments, context, NAME, true, new HashMap<>() {{
			put("jukescantor", JukesCantor::new);
		}});
	}

}
