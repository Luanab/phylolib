package flow.read;

import data.DataSet;
import exception.ParameterException;
import flow.Option;
import flow.Parameters;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;

public abstract class Reader {

	private final String from;

	protected Reader(String from) {
		this.from = from;
	}

	protected abstract DataSet parse(Stream<String> data);

	public final DataSet read() throws IOException {
		return parse(Files.lines(Paths.get(from)));
	}

	public static Reader get(Parameters parameters) throws ParameterException {
		return parameters.map("-reader", "-r", new ArrayList<>(){{ add("mlst"); }}, new HashMap<>() {{
			put("mlst", new Option<>(0, values -> new MLSTReader(parameters.from)));
			put("snp", new Option<>(0, values -> new SNPReader(parameters.from)));
			put("mlva", new Option<>(0, values -> new MLVAReader(parameters.from)));
			put("fasta", new Option<>(0, values -> new FASTAReader(parameters.from)));
		}});
	}

}
