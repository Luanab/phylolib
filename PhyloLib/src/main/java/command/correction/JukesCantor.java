package command.correction;

public final class JukesCantor extends Correction {

	@Override
	protected double correct(double distance) {
		return -3 * Math.log(1 - 4 * distance / 3) / 4;
	}

}
