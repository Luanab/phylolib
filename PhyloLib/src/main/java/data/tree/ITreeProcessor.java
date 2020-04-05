package data.tree;

import data.IReader;
import data.IWriter;

import java.util.HashMap;

public interface ITreeProcessor extends IReader<Tree>, IWriter<Tree> {

	String INVALID_TREE = "Ignoring invalid tree";

	HashMap<String, ITreeProcessor> PROCESSORS = new HashMap<>() {{
		put("newick", new Newick());
		put("nexus", new Nexus());
	}};

}
