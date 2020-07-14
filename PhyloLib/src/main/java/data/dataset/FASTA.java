package data.dataset;

import java.util.Iterator;

/**
 * Responsible for parsing {@link Dataset phylogenetic datasets} from Strings in FASTA format.
 */
public final class FASTA extends DatasetProcessor {

	private String id;

	@Override
	public void init(Iterator<String> iterator) {
		id = iterator.hasNext() ? iterator.next() : null;
	}

	@Override
	public Profile parse(Iterator<String> iterator) {
		String id = this.id;
		StringBuilder sequence = new StringBuilder();
		String next = "";
		while (iterator.hasNext() && !(next = iterator.next()).startsWith(">"))
			sequence.append(next);
		this.id = next;
		return id.startsWith(">")
			   ? new Profile(id.substring(1), sequence.toString().matches("^[ACTG -]+$") ? sequence.toString() : "")
			   : new Profile(id, "");
	}

}
