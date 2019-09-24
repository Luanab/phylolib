package flow;

import data.DataSet;
import parse.CSVParser;
import parse.IParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

class Parser extends Component<IParser, Function<String, DataSet>> {

	Parser() {
		this.name = "-parser";
		this.number = 0;
		this.options = new HashMap<>() {{
			put("csv", new CSVParser());
		}};
		this.mapper = (values, parser) -> parser::parse;
	}

	@Override
	List<String> defaultValues() {
		return new ArrayList<>(){{
			add("csv");
		}};
	}

}
