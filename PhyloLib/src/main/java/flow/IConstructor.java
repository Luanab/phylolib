package flow;

import cli.Options;
import data.Context;

@FunctionalInterface
public interface IConstructor<T> {

    T construct(Context context, Options options) throws Exception;

}
