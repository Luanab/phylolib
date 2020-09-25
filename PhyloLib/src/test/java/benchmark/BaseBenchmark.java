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
				           .forks(1)
				           .threads(1)
				           .jvmArgs("-Xms8G")
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

		@Param({
				"ml:14-bbacilliformis.txt",
				"ml:100-brucella.txt",
				"ml:484-achromobacter.txt",
				"ml:1110-ctropicalis.txt",
				"ml:3585-calbicans.txt",
				"ml:6261-saureus.txt",
				"ml:11884-campylobacter.txt",
				"ml:16301-spneumoniae.txt"
		})
		public String file;

		public Dataset dataset;

		@Setup(Level.Trial)
		public void setup() {
			String[] values = file.split(":", 2);
			try (Stream<String> lines = Files.lines(Paths.get(BaseBenchmark.class.getClassLoader().getResource(values[1]).toURI()))) {
				this.dataset = ((DatasetProcessor) Data.DATASET.type(values[0]).newInstance()).parse(lines);
			} catch (IOException | URISyntaxException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}

		@TearDown(Level.Invocation)
		public void tearDown() {
			System.gc();
		}

	}

}
