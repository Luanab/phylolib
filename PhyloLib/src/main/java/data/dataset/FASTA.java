package data.dataset;

import javafx.util.Pair;

import java.util.Iterator;

public final class FASTA implements IDatasetProcessor {

	private String id;

	@Override
	public void init(Iterator<String> iterator) {
		id = iterator.hasNext() ? iterator.next() : null;
	}

	@Override
	public Pair<String, Profile> parse(Iterator<String> iterator) {
		String id = this.id;
		StringBuilder sequence = new StringBuilder();
		String next = "";
		while (iterator.hasNext() && !(next = iterator.next()).startsWith(">"))
			sequence.append(next);
		this.id = next;
		return new Pair<>(id, id.startsWith(">") && sequence.toString().matches("^[ACTG ]+$") ? new Profile(sequence.toString()) : null);
	}

}
