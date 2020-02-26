package file.tree;

import data.tree.Tree;
import file.IReader;
import file.IWriter;

import java.util.HashMap;

public interface ITreeFormatter extends IReader<Tree>, IWriter<Tree> {

	HashMap<String, ITreeFormatter> FORMATTERS = new HashMap<>() {{
		put("newick", new Newick());
		put("nexus", new Nexus());
	}};

}
