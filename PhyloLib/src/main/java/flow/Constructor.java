package flow;

import java.util.List;

public interface Constructor<T> {
    T construct(List<String> values, boolean previous, boolean next) throws Exception;
}
