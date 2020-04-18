package data.tree;

import data.IReader;
import data.IWriter;

public interface ITreeProcessor extends IReader<Tree>, IWriter<Tree> {

	String RENAMING = "Renaming node '%s' to '%d'";
	String IGNORING = "Ignoring invalid tree";

}
