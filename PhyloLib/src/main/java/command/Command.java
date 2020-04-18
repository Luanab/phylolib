package command;

import command.algorithm.Algorithm;
import command.correction.Correction;
import command.distance.Distance;
import command.optimization.Optimization;
import reflection.Reflection;

import java.lang.reflect.Constructor;
import java.util.Map;

public enum Command {

	DISTANCE(false, Distance.class),
	CORRECTION(false, Correction.class),
	ALGORITHM(false, Algorithm.class),
	OPTIMIZATION(true, Optimization.class);

	private final boolean repeatable;
	private final Map<String, Constructor<?>> types;

	Command(boolean repeatable, Class<?> type) {
		this.repeatable = repeatable;
		this.types = Reflection.types("command." + getName(), type);
	}

	public static Command get(String command) {
		return valueOf(command.toUpperCase());
	}

	public String getName() {
		return name().toLowerCase();
	}

	public boolean isRepeatable() {
		return repeatable;
	}

	public boolean hasType(String type) {
		return types.containsKey(type);
	}

	public Constructor<?> getType(String type) {
		return types.get(type);
	}

}
