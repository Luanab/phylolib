package flow;

import cli.Commands;
import cli.File;
import cli.Parameters;
import data.Context;
import data.IReader;
import data.IWriter;
import data.dataset.IDatasetFormatter;
import data.matrix.IMatrixFormatter;
import data.tree.ITreeFormatter;
import exception.InvalidFormatException;
import exception.InvalidTypeException;
import exception.MissingOptionException;
import exception.RepeatedCommandException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class Component<T> {

    protected final Context context;
    private final Consumer<T> setter;
    private final IMapper<IWriter<T>> mapper;
    private final Parameters parameters;
    private boolean dataset;
    private boolean matrix;
    private boolean tree;

    protected Component(Context context, Consumer<T> setter, IMapper<IWriter<T>> mapper, Parameters parameters, boolean dataset, boolean matrix, boolean tree) {
        this.context = context;
        this.setter = setter;
        this.mapper = mapper;
        this.parameters = parameters;
        this.dataset = dataset;
        this.matrix = matrix;
        this.tree = tree;
    }

    public static <T extends Component<?>> void runSingle(Commands commands, Context context, String command, HashMap<String, IConstructor<T>> constructors) throws RepeatedCommandException, InvalidTypeException, InvalidFormatException, MissingOptionException, IOException {
        if (!commands.containsKey(command))
            return;
        List<Parameters> parameters = commands.get(command);
        if (parameters.size() > 1)
            throw new RepeatedCommandException(command);
        run(command, constructors, context, parameters.get(0));
    }

    public static <T extends Component<?>> void runAll(Commands commands, Context context, String command, HashMap<String, IConstructor<T>> constructors) throws MissingOptionException, InvalidFormatException, InvalidTypeException, IOException {
        if (!commands.containsKey(command))
            return;
        for (Parameters parameters : commands.get(command))
            run(command, constructors, context, parameters);
    }

    private static <T extends Component<?>> void run(String command, HashMap<String, IConstructor<T>> constructors, Context context, Parameters parameters) throws MissingOptionException, InvalidFormatException, InvalidTypeException, IOException {
        IConstructor<T> constructor = constructors.get(parameters.type);
        if (constructor == null)
            throw new InvalidTypeException(command, parameters.type);
        constructor.construct(context, parameters).run();
    }

    private <R> void read(Parameters parameters, String option, boolean input, Supplier<R> getter, Consumer<R> setter, IMapper<IReader<R>> mapper) throws MissingOptionException, InvalidFormatException, IOException {
        if (input) {
            boolean fromContext = getter.get() != null;
            boolean fromParameter = parameters.options.containsKey(option);
            if (!fromContext && !fromParameter)
                throw new MissingOptionException(parameters.type, option);
            if (fromParameter) {
                if (fromContext)
                    System.out.println("Warning: Overwriting '" + option + "' context value with parameter value...");
                File file = new File(parameters.options.get(option));
                setter.accept(mapper.get(file.format).read(file.location));
            }
        }
    }

    private void read() throws MissingOptionException, InvalidFormatException, IOException {
        read(parameters, "dataset", dataset, context::getDataset, context::setDataset, IDatasetFormatter::get);
        read(parameters, "matrix", matrix, context::getMatrix, context::setMatrix, IMatrixFormatter::get);
        read(parameters, "tree", tree, context::getTree, context::setTree, ITreeFormatter::get);
    }

    private void write(T result) throws InvalidFormatException, IOException {
        setter.accept(result);
        String out = parameters.options.get("out");
        if (out != null) {
            File file = new File(out);
            mapper.get(file.format).write(file.location, result);
        }
    }

    protected abstract T process();

    public final void run() throws MissingOptionException, InvalidFormatException, IOException {
        read();
        T result = process();
        write(result);
    }

}
