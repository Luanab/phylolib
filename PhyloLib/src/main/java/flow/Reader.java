package flow;

import read.ConsoleReader;
import read.FileReader;
import read.IReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;

class Reader extends Component<IReader, Supplier<String>> {

	Reader() {
		this.name = "-reader";
		this.number = 1;
		this.options = new HashMap<>() {{
			put("console", new ConsoleReader());
			put("file", new FileReader());
		}};
		this.mapper = (values, reader) -> (() -> reader.read(values.get(0)));
	}

	@Override
	List<String> defaultValues() {
		return new ArrayList<>(){{
			add("console");
			add(null);
		}};
	}

}
