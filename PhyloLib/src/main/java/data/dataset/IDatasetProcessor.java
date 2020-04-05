package data.dataset;

import data.IReader;

import java.util.HashMap;

public interface IDatasetProcessor extends IReader<Dataset> {

	String INVALID_PROFILE = "Ignoring invalid profile %d";

	HashMap<String, IDatasetProcessor> PROCESSORS = new HashMap<>() {{
		put("fasta", new FASTA());
		put("mlst", new ML());
		put("mlva", new ML());
		put("snp", new SNP());
	}};

}
