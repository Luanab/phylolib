package data.matrix;

import data.Formatter;
import data.IReader;
import data.IWriter;

import java.util.HashMap;

public interface IMatrixFormatter extends IReader<Matrix>, IWriter<Matrix> {

    static IMatrixFormatter get(String format) throws Exception {
        return Formatter.get(format, new HashMap<>() {{
            put("csv", CSVFormatter::new);
        }});
    }

}
