package data.dataset;

import java.util.Iterator;

/**
 * Responsible for parsing {@link Dataset phylogenetic datasets} from Strings in SNP format.
 */
public final class SNP extends DatasetParser {

	@Override
	public Profile parse(Iterator<String> iterator) {
		String[] profile = iterator.next().split("\\t", 2);
		return new Profile(profile[0], profile.length == 2 && profile[1].matches("^[01 -]+$") ? profile[1] : "");
	}

}
