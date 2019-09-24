package flow;

import write.ConsoleWriter;
import write.FileWriter;
import write.IWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

class Writer extends Component<IWriter, Consumer<String>> {

	Writer() {
		this.name = "-writer";
		this.number = 1;
		this.options = new HashMap<>() {{
			put("console", new ConsoleWriter());
			put("file", new FileWriter());
		}};
		this.mapper = (values, writer) -> (data -> writer.write(data, values.get(0)));
	}

	@Override
	List<String> defaultValues() {
		return new ArrayList<>(){{
			add("console");
			add(null);
		}};
	}

}
