package flow;

import data.Context;
import data.File;
import data.IReader;
import data.IWriter;
import data.dataset.IDatasetFormatter;
import data.matrix.IMatrixFormatter;
import data.tree.ITreeFormatter;

import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class Component<T> {

    protected final Context context;
    private final Consumer<T> setter;
    private final IMapper<IWriter<T>> mapper;
    private final String out;

    public Component(Context context, Consumer<T> setter, IMapper<IWriter<T>> mapper,
                     HashMap<String, String> values, boolean dataset, boolean matrix, boolean tree) throws Exception {
        this.context = context;
        this.setter = setter;
        this.mapper = mapper;
        this.out = values.get("--out");
        init(values, "--dataset", dataset, context::getDataset, context::setDataset, IDatasetFormatter::get);
        init(values, "--matrix", matrix, context::getMatrix, context::setMatrix, IMatrixFormatter::get);
        init(values, "--tree", tree, context::getTree, context::setTree, ITreeFormatter::get);
    }

    public static void runSingle(Parameters parameters, Context context, String name, HashMap<String, IConstructor> constructors) throws Exception {
        if (!parameters.contains(name))
            return;
        List<HashMap<String, String>> params = parameters.get(name);
        if (params.size() > 1)
            throw new Exception("Component '" + name + "' can only be defined once...");
        HashMap<String, String> values = params.get(0);
        run(constructors, context, values);
    }

    public static void runAll(Parameters parameters, Context context, String name, HashMap<String, IConstructor> constructors) throws Exception {
        if (!parameters.contains(name))
            return;
        for (HashMap<String, String> values : parameters.get(name))
            run(constructors, context, values);
    }

    private static void run(HashMap<String, IConstructor> constructors, Context context, HashMap<String, String> values) throws Exception {
        String type = values.get("--type");
        IConstructor constructor = constructors.get(type);
        if (constructor == null)
            throw new Exception("Invalid component type " + type + "...");
        constructor.construct(context, values).run();
    }

    private <R> void init(HashMap<String, String> values, String name, boolean input, Supplier<R> getter, Consumer<R> setter, IMapper<IReader<R>> mapper) throws Exception {
        if (input) {
            boolean fromContext = getter.get() != null;
            boolean fromParameter = values.containsKey(name);
            if (fromContext && fromParameter)
                System.out.println("Warning: Ignoring " + name + " parameter...");
            else if (!fromContext && !fromParameter)
                throw new Exception(name + " parameter must be defined...");
            else {
                File file = new File(values.get(name));
                setter.accept(mapper.get(file.format).read(file.location));
            }
        }
    }

    protected abstract T process();

    public final void run() throws Exception {
        T result = process();
        setter.accept(result);
        if (out != null) {
            File file = new File(out);
            mapper.get(file.format).write(file.location, result);
        }
    }

}
