package flow;

import cli.Options;
import data.Context;
import exception.InvalidFormatException;

public interface IConstructor<T> {

	T construct(Context context, Options options) throws InvalidFormatException;

}
