package data.matrix;

/**
 * Responsible for parsing and formatting {@link Matrix distance matrices} from and to Strings in a symmetric format.
 */
public final class Symmetric extends SymmetryProcessor {

	@Override
	protected boolean symmetric() {
		return true;
	}

}
