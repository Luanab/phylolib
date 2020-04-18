package reflection;

import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.stream.Collectors;

public class Reflection {

	public static <T> Map<String, Constructor<?>> types(Class<T> type) {
		return new Reflections("command")
				.getSubTypesOf(type).stream()
				.filter(c -> !Modifier.isAbstract(c.getModifiers()))
				.collect(Collectors.toMap(c -> c.getSimpleName().toLowerCase(), c -> c.getConstructors()[0]));
	}

}
