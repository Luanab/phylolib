package pt.ist.phylolib.command.correction;

import pt.ist.phylolib.command.ICommand;
import pt.ist.phylolib.data.matrix.Matrix;

/**
 * Responsible for correcting a {@link Matrix distance matrix} into another.
 */
public abstract class Correction implements ICommand<Matrix, Matrix> {

	@Override
	public final Matrix process(Matrix matrix) {
		return matrix.correct(this::correct);
	}

	/**
	 * Corrects the given phylogenetic distance.
	 *
	 * @param distance the distance to correct
	 *
	 * @return the value resultant from correcting the phylogenetic distance
	 */
	protected abstract double correct(double distance);

}
