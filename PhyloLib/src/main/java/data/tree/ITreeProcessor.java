package data.tree;

import data.IReader;
import data.IWriter;

import java.util.HashMap;

public interface ITreeProcessor extends IReader<Tree>, IWriter<Tree> {

	String RENAMING = "Renaming node '%s' to '%d'";
	String IGNORING = "Ignoring invalid tree";

	HashMap<String, ITreeProcessor> PROCESSORS = new HashMap<>() {{
		put("newick", new Newick());
		put("nexus", new Nexus());
	}};

}
