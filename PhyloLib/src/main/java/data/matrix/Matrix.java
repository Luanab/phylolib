package data.matrix;

import java.util.Arrays;

/**
 * Represents a distance matrix as a square or triangle depending on its symmetry and the ids of the profiles.
 */
public final class Matrix {

	private final boolean symmetric;
	private final String[] ids;
	private final Double[][] distances;
	private final IDistance distance;

	/**
	 * Create a lazy distance matrix corresponding to the given symmetry, ids and distance provider.
	 *
	 * @param symmetric a flag indicating whether this matrix is a square (asymmetric) or a triangle (symmetric)
	 * @param ids       the ids of the profiles of this matrix
	 * @param distance  the lazy distance provider to populate this matrix
	 */
	public Matrix(boolean symmetric, String[] ids, IDistance distance) {
		this.symmetric = symmetric;
		this.ids = ids;
		if (symmetric) {
			this.distances = new Double[ids.length][];
			for (int i = 0; i < ids.length; i++)
				this.distances[i] = new Double[i];
		} else
			this.distances = new Double[ids.length][ids.length];
		this.distance = distance;
	}

	/**
	 * Creates an eager distance matrix corresponding to the given symmetry, ids and phylogenetic distances.
	 *
	 * @param symmetric a flag indicating whether this matrix is a square (asymmetric) or a triangle (symmetric)
	 * @param ids       the ids of the profiles of this matrix
	 * @param distances the calculated phylogenetic distances to populate this matrix
	 */
	public Matrix(boolean symmetric, String[] ids, Double[][] distances) {
		this.symmetric = symmetric;
		this.ids = ids;
		this.distances = distances;
		this.distance = (i, j) -> 0;
	}

	public String[] ids() {
		return ids;
	}

	public final int size() {
		return distances.length;
	}

	/**
	 * Gets the phylogenetic distance between two given profiles.
	 * <p>
	 * If the given profiles are the same, then returns 0.
	 *
	 * @param i a number identifying one profile in this matrix
	 * @param j a number identifying another profile in this matrix
	 *
	 * @return the phylogenetic distance between the profiles identified by i and j
	 */
	public double distance(int i, int j) {
		if (i == j)
			return 0;
		if (symmetric) {
			int k = i;
			i = Math.max(i, j);
			j = Math.min(k, j);
		}
		return distances[i][j] != null ? distances[i][j] : (distances[i][j] = distance.get(i, j));
	}

	/**
	 * Gets a distance matrix corrected according to the given correction formula.
	 *
	 * @param correction the correction formula to apply to each phylogenetic distance of this matrix
	 *
	 * @return a new distance matrix with the phylogenetic distances of this matrix corrected
	 */
	public Matrix correct(ICorrection correction) {
		return distances[1][0] == null
		       ? new Matrix(symmetric, ids, (i, j) -> correction.get(distance.get(i, j)))
		       : new Matrix(symmetric, ids, Arrays.stream(distances).map(line -> Arrays.stream(line).map(correction::get).toArray(Double[]::new)).toArray(Double[][]::new));
	}

	/**
	 * Represents a phylogenetic distance provider between two profiles.
	 */
	public interface IDistance {

		/**
		 * Calculates the phylogenetic distance between two given profiles.
		 *
		 * @param i a number identifying one profile
		 * @param j a number identifying another profile
		 *
		 * @return the calculated phylogenetic distance between the profiles identified by i and j
		 */
		double get(int i, int j);

	}

	/**
	 * Represents a correction formula for a phylogenetic distance.
	 */
	public interface ICorrection {

		/**
		 * Corrects the given phylogenetic distance.
		 *
		 * @param distance the phylogenetic distance to correct
		 *
		 * @return the value resultant from correcting the phylogenetic distance
		 */
		double get(double distance);

	}

}
