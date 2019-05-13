package algorithms.data_structure;

import java.util.Optional;

public interface List<T> { // todo: create "immutable list" data structure

    List<T> add(T value);

    List<T> removeFirst(T value);

    Optional<T> head();

    List<T> tail();

}
