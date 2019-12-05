package data.tree;

import data.Formatter;
import data.IReader;
import data.IWriter;

import java.util.HashMap;
import java.util.function.Supplier;

public interface ITreeFormatter extends IReader<Tree>, IWriter<Tree> {

    static ITreeFormatter get(String format) throws Exception {
        return Formatter.get(format, new HashMap<String, Supplier<ITreeFormatter>>() {{
            put("newick", NewickFormatter::new);
            put("nexus", NexusFormatter::new);
        }});
    }

}
