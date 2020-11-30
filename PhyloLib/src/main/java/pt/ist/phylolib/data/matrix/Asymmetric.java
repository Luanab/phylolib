package pt.ist.phylolib.data.matrix;

/**
 * Responsible for parsing {@link Matrix distance matrices} from and to Strings in an asymmetric format.
 */
public final class Asymmetric extends SymmetryParser {

	@Override
	protected boolean symmetric() {
		return false;
	}

}
