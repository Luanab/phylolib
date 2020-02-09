package data.matrix;

import data.IFormatter;
import data.IReader;
import data.IWriter;

import java.util.HashMap;

public interface IMatrixFormatter extends IReader<Matrix>, IWriter<Matrix> {

    static IMatrixFormatter get(String format) throws Exception {
        return IFormatter.get(format, new HashMap<>() {{
            put("csv", CSVFormatter::new);
        }});
    }

}
