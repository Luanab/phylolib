package flow.read;

import data.DataSet;
import exception.NumberOfArgumentsException;
import exception.ParameterException;
import flow.Component;
import flow.Parameters;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public abstract class Reader extends Component {

	private final String from;

	protected Reader(String name, String value, List<String> parameters, int mandatory) throws NumberOfArgumentsException {
		super(name, value, parameters, mandatory + 1);
		this.from = parameters.remove(0);
	}

	protected abstract DataSet parse(Stream<String> data);

	public final DataSet read() throws IOException {
		return parse(Files.lines(Paths.get(from)));
	}

	public static Reader get(Parameters parameters) throws ParameterException {
		return parameters.map("-reader", "-r", null, new HashMap<>() {{
			put("mlst", MLSTReader::new);
			put("snp", SNPReader::new);
			put("mlva", MLVAReader::new);
			put("fasta", FASTAReader::new);
		}});
	}

}
