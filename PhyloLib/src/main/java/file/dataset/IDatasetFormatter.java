package file.dataset;

import data.dataset.Dataset;
import file.IReader;

import java.util.HashMap;

public interface IDatasetFormatter extends IReader<Dataset> {

	HashMap<String, IDatasetFormatter> FORMATTERS = new HashMap<>() {{
		put("fasta", new FASTA());
		put("mlst", new MLST());
		put("mlva", new MLVA());
		put("snp", new SNP());
	}};

}
