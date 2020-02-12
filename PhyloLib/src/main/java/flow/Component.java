package flow;

import cli.Arguments;
import cli.File;
import cli.Parameters;
import data.Context;
import data.IReader;
import data.IWriter;
import data.dataset.IDatasetFormatter;
import data.matrix.IMatrixFormatter;
import data.tree.ITreeFormatter;
import exception.ArgumentException;
import exception.InvalidTypeException;
import exception.MissingOptionException;
import exception.RepeatedCommandException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class Component<T> {

    protected static final byte DATASET = 0b001, MATRIX = 0b0010, TREE = 0b0100;

    protected final Context context;
    private final Consumer<T> setter;
    private final IMapper<IWriter<T>> mapper;
    private final Parameters parameters;

    protected byte input;

    protected Component(Context context, Consumer<T> setter, IMapper<IWriter<T>> mapper, Parameters parameters) {
        this.context = context;
        this.setter = setter;
        this.mapper = mapper;
        this.parameters = parameters;
        this.input = 0b0000;
    }

    public static <T extends Component<?>> void run(Arguments arguments, Context context, String command, boolean single,
                                                    HashMap<String, IConstructor<T>> constructors) throws ArgumentException, IOException {
        if (!arguments.containsKey(command))
            return;
        List<Parameters> commands = arguments.get(command);
        if (single && commands.size() > 1)
            throw new RepeatedCommandException(command);
        for (Parameters parameters : commands) {
            IConstructor<T> constructor = constructors.get(parameters.type);
            if (constructor == null)
                throw new InvalidTypeException(command, parameters.type);
            constructor.construct(context, parameters).run();
        }
    }

    private <R> void read(Parameters parameters, String option, byte mask, Supplier<R> getter, Consumer<R> setter,
                          IMapper<IReader<R>> mapper) throws ArgumentException, IOException {
        if ((input & mask) != 0) {
            boolean fromContext = getter.get() != null;
            boolean fromParameter = parameters.options.contains(option, option.charAt(0));
            if (!fromContext && !fromParameter)
                throw new MissingOptionException(parameters.type, option);
            if (fromParameter) {
                if (fromContext)
                    System.out.println("Warning: Overwriting '" + option + "' context value with parameter value...");
                File file = new File(parameters.options.get(option, option.charAt(0)));
                setter.accept(mapper.get(file.format).read(file.location));
            }
        }
    }

    protected abstract T process();

    public final void run() throws ArgumentException, IOException {
        read(parameters, "dataset", DATASET, context::getDataset, context::setDataset, IDatasetFormatter::get);
        read(parameters, "matrix", MATRIX, context::getMatrix, context::setMatrix, IMatrixFormatter::get);
        read(parameters, "tree", TREE, context::getTree, context::setTree, ITreeFormatter::get);
        T result = process();
        setter.accept(result);
        String out = parameters.options.get("out", 'o');
        if (out != null) {
            File file = new File(out);
            mapper.get(file.format).write(file.location, result);
        }
    }

}
