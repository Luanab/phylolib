package command.distance;

import command.ICommand;
import data.dataset.Dataset;
import data.dataset.Profile;
import data.matrix.Matrix;

/**
 * Responsible for calculating a {@link Matrix distance matrix} from a {@link Dataset phylogenetic dataset}.
 */
public abstract class Distance implements ICommand<Dataset, Matrix> {

	@Override
	public final Matrix process(Dataset dataset) {
		return new Matrix(symmetric(), dataset.ids(), (i, j) -> distance(dataset.profile(i), dataset.profile(j)));
	}

	/**
	 * Checks the symmetry of the distance matrix calculated by this distance command.
	 *
	 * @return a flag indicating whether the distance matrix calculated by this distance command is symmetric or asymmetric
	 */
	protected abstract boolean symmetric();

	/**
	 * Calculates the phylogenetic distance between the two given profiles.
	 *
	 * @param i one profile
	 * @param j another profile
	 *
	 * @return the phylogenetic distance between the profiles
	 */
	protected abstract double distance(Profile i, Profile j);

}
