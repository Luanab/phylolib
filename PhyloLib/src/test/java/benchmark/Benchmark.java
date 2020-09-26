package benchmark;

import cli.Data;
import cli.Options;
import command.algorithm.Algorithm;
import command.distance.Hamming;
import data.Context;
import data.dataset.Dataset;
import data.dataset.DatasetProcessor;
import data.matrix.Matrix;
import reflection.Types;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Benchmark {

	private static final List<MemoryPoolMXBean> MEMORY = ManagementFactory.getMemoryPoolMXBeans() ;
	private static final int WARMUPS = 10;
	private static final int ITERATIONS = 20;
	private static final String[] FILES = new String[] {
			"ml:10.txt",
			"ml:100.txt",
			"ml:200.txt",
			"ml:300.txt",
			"ml:400.txt",
			"ml:500.txt",
			"ml:600.txt",
			"ml:700.txt",
			"ml:800.txt",
			"ml:900.txt",
			"ml:1000.txt"
	};

	public static void main(String[] args) throws Exception {
		for (String file : FILES) {
			Matrix matrix = new Hamming().process(read(file));
			for (Map.Entry<String, Constructor<?>> command : Types.get("command.algorithm", Algorithm.class).entrySet()) {
				Algorithm algorithm = (Algorithm) command.getValue().newInstance();
				algorithm.init(new Context(), new Options());
				run(command.getKey(), file, () -> algorithm.process(matrix));
			}
		}
	}

	private static Dataset read(String file) {
		String[] values = file.split(":", 2);
		try (Stream<String> lines = Files.lines(Paths.get(Benchmark.class.getClassLoader().getResource(values[1]).toURI()))) {
			return ((DatasetProcessor) Data.DATASET.type(values[0]).newInstance()).parse(lines);
		} catch (IOException | URISyntaxException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
	}

	private static void run(String method, String file, Runnable action) {
		for (int i = 1; i <= WARMUPS; i++)
			action.run();
		double totalTime = 0;
		double totalMemory = 0;
		for (int i = 1; i <= ITERATIONS; i++) {
			System.gc();
			MEMORY.forEach(MemoryPoolMXBean::resetPeakUsage);
			double start = System.nanoTime();
			action.run();
			totalTime += (System.nanoTime() - start) / 1000000;
			totalMemory += MEMORY.stream().mapToDouble(pool -> pool.getPeakUsage().getUsed() / 1024.0 / 1024.0).sum();
		}
		System.out.printf("Benchmarking %s with %s: %f ms, %f MB\n\n", method, file, totalTime / ITERATIONS, totalMemory / ITERATIONS);
	}

}
