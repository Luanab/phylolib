package pt.ist.phylolib.command.correction;

import pt.ist.phylolib.data.matrix.Matrix;

/**
 * Responsible for correcting a {@link Matrix distance matrix} into another using the Jukes-Cantor correction formula.
 */
public final class JukesCantor extends Correction {

	@Override
	protected double correct(double distance) {
		return -3.0 / 4.0 * Math.log(1 - 4.0 / 3.0 * distance);
	}

}
