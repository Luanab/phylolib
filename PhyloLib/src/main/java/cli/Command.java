package cli;

public enum Command {

	DISTANCE(Granularity.SINGLE),
	CORRECTION(Granularity.SINGLE),
	ALGORITHM(Granularity.SINGLE),
	OPTIMIZATION(Granularity.MULTIPLE);

	private final Granularity granularity;

	Command(Granularity granularity) {
		this.granularity = granularity;
	}

	public String getName() {
		return name().toLowerCase();
	}

	public Granularity getGranularity() {
		return granularity;
	}

}
