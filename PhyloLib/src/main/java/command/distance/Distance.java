package command.distance;

import command.ICommand;
import data.dataset.Dataset;
import data.dataset.Profile;
import data.matrix.Matrix;

public abstract class Distance implements ICommand<Dataset, Matrix> {

	@Override
	public final Matrix process(Dataset dataset) {
		return new Matrix(dataset.ids(), (i, j) -> distance(dataset.profile(i), dataset.profile(j)));
	}

	protected abstract double distance(Profile i, Profile j);

}
