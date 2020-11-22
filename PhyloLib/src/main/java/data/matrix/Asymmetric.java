package data.matrix;

/**
 * Responsible for parsing and formatting {@link Matrix distance matrices} from and to Strings in an asymmetric format.
 */
public final class Asymmetric extends SymmetryProcessor {

	@Override
	protected boolean symmetric() {
		return false;
	}

}
