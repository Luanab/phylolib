package benchmark;

import cli.Data;
import data.dataset.Dataset;
import data.dataset.DatasetProcessor;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class BaseBenchmark {

	public static void main(Class<?> benchmark) throws RunnerException {
		new Runner(new OptionsBuilder()
				.include("\\." + benchmark.getSimpleName() + "\\.")
				.timeout(TimeValue.NONE)
				.warmupIterations(5)
				.measurementIterations(10)
				.shouldDoGC(true)
				.shouldFailOnError(true)
				.mode(Mode.AverageTime)
				.timeUnit(TimeUnit.MILLISECONDS)
				.addProfiler(GCProfiler.class)
				.build())
				.run();
	}

	@State(Scope.Benchmark)
	public static class BaseState {

		@Param({ "" })
		public String file;

		public Dataset dataset;

		@Setup(Level.Invocation)
		public void setup() {
			String[] values = file.split(":", 2);
			try (Stream<String> lines = Files.lines(Paths.get(BaseBenchmark.class.getClassLoader().getResource(values[1]).toURI()))) {
				this.dataset = ((DatasetProcessor) Data.DATASET.type(values[0]).newInstance()).parse(lines);
			} catch (IOException | URISyntaxException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}

	}

}
