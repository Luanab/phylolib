package data.matrix;

/**
 * Responsible for parsing and formatting {@link Matrix distance matrices} from and to Strings in a symmetric format.
 */
public final class Symmetric extends MatrixProcessor {

	/**
	 * Creates a symmetric distance matrix processor.
	 */
	public Symmetric() {
		super(true);
	}

}
