package command.correction;

public final class JukesCantor extends Correction {

	@Override
	protected strictfp double correct(double distance) {
		return -3.0 / 4.0 * Math.log(1 - 4.0 / 3.0 * distance);
	}

}
