package data.matrix;

import data.Formatter;
import data.IReader;
import data.IWriter;

import java.util.HashMap;
import java.util.function.Supplier;

public interface IMatrixFormatter extends IReader<Matrix>, IWriter<Matrix> {

    static IMatrixFormatter get(String format) throws Exception {
        return Formatter.get(format, new HashMap<String, Supplier<IMatrixFormatter>>() {{
            put("csv", CSVFormatter::new);
        }});
    }

}
