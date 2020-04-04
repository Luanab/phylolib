package command;

public enum Command {

	DISTANCE(Multiplicity.SINGLE),
	CORRECTION(Multiplicity.SINGLE),
	ALGORITHM(Multiplicity.SINGLE),
	OPTIMIZATION(Multiplicity.MULTIPLE);

	private final Multiplicity multiplicity;

	Command(Multiplicity multiplicity) {
		this.multiplicity = multiplicity;
	}

	public String getName() {
		return name().toLowerCase();
	}

	public Multiplicity getMultiplicity() {
		return multiplicity;
	}

}
