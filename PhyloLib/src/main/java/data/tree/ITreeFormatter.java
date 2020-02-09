package data.tree;

import data.IFormatter;
import data.IReader;
import data.IWriter;

import java.util.HashMap;

public interface ITreeFormatter extends IReader<Tree>, IWriter<Tree> {

    static ITreeFormatter get(String format) throws Exception {
        return IFormatter.get(format, new HashMap<>() {{
            put("newick", NewickFormatter::new);
            put("nexus", NexusFormatter::new);
        }});
    }

}
