package data.tree;

import data.IReader;
import data.IWriter;

public interface ITreeProcessor extends IReader<Tree>, IWriter<Tree> {

	String IGNORING = "Ignoring invalid tree";

}
