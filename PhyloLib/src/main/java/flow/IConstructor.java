package flow;

import cli.Parameters;
import data.Context;
import exception.InvalidFormatException;

@FunctionalInterface
public interface IConstructor<T> {

    T construct(Context context, Parameters parameters) throws InvalidFormatException;

}
