package flow;

public interface Supplier<T> {
    T get(String type) throws Exception;
}
