package pt.ist.phylolib.data.matrix;

/**
 * Responsible for parsing {@link Matrix distance matrices} from and to Strings in a symmetric format.
 */
public final class Symmetric extends SymmetryParser {

	@Override
	protected boolean symmetric() {
		return true;
	}

}
