package cli;

public enum Option {

	OUT('o', Format.FILE),
	DATASET('d', Format.FILE),
	MATRIX('m', Format.FILE),
	TREE('t', Format.FILE),
	LVS('l', Format.NATURAL),
	ROOT('r', Format.NATURAL);

	private final char alias;
	private final Format format;

	Option(char alias, Format format) {
		this.alias = alias;
		this.format = format;
	}

	public String getKey() {
		return name().toLowerCase();
	}

	public char getAlias() {
		return alias;
	}

	public Format getFormat() {
		return format;
	}

}
