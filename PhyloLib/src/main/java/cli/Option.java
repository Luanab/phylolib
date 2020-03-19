package cli;

public enum Option {

	OUT('o'),
	DATASET('d'),
	MATRIX('m'),
	TREE('t'),
	LVS('l');

	private final char alias;

	Option(char alias) {
		this.alias = alias;
	}

	public String getKey() {
		return name().toLowerCase();
	}

	public char getAlias() {
		return alias;
	}

}
