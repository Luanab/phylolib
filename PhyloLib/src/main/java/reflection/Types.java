package reflection;

import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Responsible for the use of reflection to search for types of a command or data.
 */
public class Types {

	/**
	 * Gets the names and constructors of the types inside specific packages that inherit from a specific type.
	 *
	 * @param type   the superclass the types should inherit from
	 *
	 * @return the names and constructors of the types that are inside packages starting with the given prefix and that inherit from the given type
	 */
	public static Map<String, Constructor<?>> get(Class<?> type) {
		return new Reflections("")
				.getSubTypesOf(type).stream()
				.filter(c -> !Modifier.isAbstract(c.getModifiers()))
				.collect(Collectors.toMap(c -> c.getSimpleName().toLowerCase(), c -> c.getConstructors()[0]));
	}

}
