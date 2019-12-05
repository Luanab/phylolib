package flow;

import data.Context;
import data.IReader;
import data.IWriter;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public abstract class Component<T, R> {

    private final boolean hasInput;
    private final IReader<T> reader;
    private final String inputFile;
    private final BiConsumer<Context, T> input;

    private final boolean hasOutput;
    private final IWriter<R> writer;
    private final String outputFile;
    private final BiConsumer<Context, R> output;

    public Component(List<String> values, int mandatory, boolean previous, boolean next,
                     BiConsumer<Context, T> input, BiConsumer<Context, R> output,
                     Supplier<IReader<T>> reader, Supplier<IWriter<R>> writer) throws Exception {
        int rest = values.size() - mandatory;
        if (rest < 0)
            throw new Exception("Missing mandatory arguments...");
        this.hasInput = !previous;
        if (hasInput && (rest -= 2) < 0)
            throw new Exception("Missing input or previous component...");
        this.hasOutput = rest == 2;
        if (!next && rest < 2)
            throw new Exception("Missing output or next component...");
        if (rest > 2)
            throw new Exception("Too many arguments...");
        this.reader = hasInput ? reader.get(values.remove(mandatory)) : null;
        this.inputFile = hasInput ? values.remove(mandatory) : null;
        this.writer = hasOutput ? writer.get(values.remove(mandatory)) : null;
        this.outputFile = hasOutput ? values.remove(mandatory) : null;
        this.input = input;
        this.output = output;
    }

    public static <T> Optional<T> getSingle(Parameters parameters, String name, String[] previous, String[] next,
                                            HashMap<String, Constructor<T>> constructors) throws Exception {
        if (!parameters.contains(name))
            return Optional.empty();
        List<List<String>> params = parameters.get(name);
        if (params.size() > 1)
            throw new Exception("Repeated component...");
        List<String> values = params.get(0);
        return Optional.of(get(constructors, values, any(parameters, previous), any(parameters, next)));
    }

    public static <T> List<T> getAll(Parameters parameters, String name, String[] previous, String[] next,
                                     HashMap<String, Constructor<T>> constructors) throws Exception {
        if (!parameters.contains(name))
            return Collections.emptyList();
        List<List<String>> params = parameters.get(name);
        List<T> all = new ArrayList<>();
        boolean p = any(parameters, previous), n = any(parameters, next);
        int total = params.size();
        for (int i = 0; i < total; i++)
            all.add(get(constructors, params.get(i), p || i != 0, n || i != total - 1));
        return all;
    }

    private static <T> T get(HashMap<String, Constructor<T>> constructors, List<String> values, boolean previous, boolean next) throws Exception {
        String type = values.remove(0);
        Constructor<T> constructor = constructors.get(type);
        if (constructor == null)
            throw new Exception("Invalid component type " + type + "...");
        return constructor.construct(values, previous, next);
    }

    private static boolean any(Parameters parameters, String[] values) {
        return Stream.of(values).anyMatch(parameters::contains);
    }

    protected abstract R process(Context context);

    public final void run(Context context) throws Exception {
        if (hasInput)
            input.accept(context, reader.read(inputFile));
        R value = process(context);
        if (hasOutput)
            writer.write(outputFile, value);
        output.accept(context, value);
    }

}
