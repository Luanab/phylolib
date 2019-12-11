package data.dataset;

import data.Formatter;
import data.IReader;

import java.util.HashMap;

public interface IDatasetFormatter extends IReader<Dataset> {

    static IDatasetFormatter get(String format) throws Exception {
        return Formatter.get(format, new HashMap<>() {{
            put("fasta", FASTAFormatter::new);
            put("mlst", MLSTFormatter::new);
            put("mlva", MLVAFormatter::new);
            put("snp", SNPFormatter::new);
        }});
    }

}
