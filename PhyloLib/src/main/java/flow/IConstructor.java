package flow;

import cli.Parameters;
import data.Context;

@FunctionalInterface
public interface IConstructor<T> {

	T construct(Context context, Parameters parameters);

}
