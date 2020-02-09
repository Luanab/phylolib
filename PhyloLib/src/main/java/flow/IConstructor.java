package flow;

import data.Context;

import java.util.HashMap;

@FunctionalInterface
public interface IConstructor {

    Component construct(Context context, HashMap<String, String> values) throws Exception;

}
