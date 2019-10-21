package flow;

import java.util.List;
import java.util.function.Function;

public class Option<T> {

	int parameters;
	Function<List<String>, T> creator;

	public Option(int parameters, Function<List<String>, T> creator) {
		this.parameters = parameters;
		this.creator = creator;
	}

}
