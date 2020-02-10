package flow;

import cli.Commands;
import cli.File;
import cli.Options;
import cli.Parameters;
import data.Context;
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

    public Component(Context context, Consumer<T> setter, IMapper<IWriter<T>> mapper, Options options, boolean dataset, boolean matrix, boolean tree) throws Exception {
        this.context = context;
        this.setter = setter;
        this.mapper = mapper;
        this.out = options.get("out");
        init(options, "dataset", dataset, context::getDataset, context::setDataset, IDatasetFormatter::get);
        init(options, "matrix", matrix, context::getMatrix, context::setMatrix, IMatrixFormatter::get);
        init(options, "tree", tree, context::getTree, context::setTree, ITreeFormatter::get);
    }

    public static <T extends Component<?>> void runSingle(Commands commands, Context context, String name, HashMap<String, IConstructor<T>> constructors) throws Exception {
        if (!commands.containsKey(name))
            return;
        List<Parameters> parameters = commands.get(name);
        if (parameters.size() > 1)
            throw new Exception("Component '" + name + "' can only be defined once...");
        run(constructors, context, parameters.get(0));
    }

    public static <T extends Component<?>> void runAll(Commands commands, Context context, String name, HashMap<String, IConstructor<T>> constructors) throws Exception {
        if (!commands.containsKey(name))
            return;
        for (Parameters parameters : commands.get(name))
            run(constructors, context, parameters);
    }

    private static <T extends Component<?>> void run(HashMap<String, IConstructor<T>> constructors, Context context, Parameters parameters) throws Exception {
        IConstructor<T> constructor = constructors.get(parameters.type);
        if (constructor == null)
            throw new Exception("Invalid component type " + parameters.type + "...");
        constructor.construct(context, parameters.options).run();
    }

    private <R> void init(Options options, String name, boolean input, Supplier<R> getter, Consumer<R> setter, IMapper<IReader<R>> mapper) throws Exception {
        if (input) {
            boolean fromContext = getter.get() != null;
            boolean fromParameter = options.containsKey(name);
            if (!fromContext && !fromParameter)
                throw new Exception("'" + name + "' parameter must be defined...");
            if (fromParameter) {
                if (fromContext)
                    System.out.println("Warning: Overwriting '" + name + "' context value with parameter value...");
                File file = new File(options.get(name));
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
