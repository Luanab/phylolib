package data.dataset;

import data.IFormatter;
import data.IReader;
import exception.InvalidFormatException;

import java.util.HashMap;

public interface IDatasetFormatter extends IReader<Dataset> {

    static IDatasetFormatter get(String format) throws InvalidFormatException {
        return IFormatter.get(format, new HashMap<>() {{
            put("fasta", FASTAFormatter::new);
            put("mlst", MLSTFormatter::new);
            put("mlva", MLVAFormatter::new);
            put("snp", SNPFormatter::new);
        }});
    }

}
