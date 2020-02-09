package flow;

@FunctionalInterface
public interface IMapper<T> {

    T get(String format) throws Exception;

}
