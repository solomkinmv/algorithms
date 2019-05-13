package algorithms.data_structure;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

public class PersistenceLinkedList<T> implements List<T>, Iterable<T> {

    private final Node<T> head;

    private PersistenceLinkedList(Node<T> head) {
        Objects.requireNonNull(head);
        this.head = head;
    }

    public static <T> List<T> empty() { // todo: make this idempotent
        return new PersistenceLinkedList<>(EmptyNode.get());
    }

    public static <T> List<T> of(T value) {
        return new PersistenceLinkedList<>(ValueNode.of(value));
    }

    @Override
    public List<T> add(T value) { // adds value to the beginning
        return new PersistenceLinkedList<>(ValueNode.of(value, head));
    }

    @Override
    public List<T> removeFirst(T value) {
        return null;
    }

    @Override
    public Optional<T> head() {
        return head.getValue();
    }

    @Override
    public List<T> tail() {
        return new PersistenceLinkedList<>(head.getNext());
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {

            private Node<T> iteratorNode = head;

            @Override
            public boolean hasNext() {
                return head.getValue().isPresent();
            }

            @Override
            public T next() {
                iteratorNode = head.getNext();
                return iteratorNode.getValue()
                                   .orElseThrow(NoSuchElementException::new);
            }
        };
    }

    private interface Node<T> {
        Optional<T> getValue();

        Node<T> getNext();
    }

    private static class ValueNode<T> implements Node<T> {
        private final T value;
        private final Node<T> next;

        private ValueNode(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }

        public static <T> Node<T> of(T value) {
            return new ValueNode<>(value, EmptyNode.get());
        }

        public static <T> Node<T> of(T value, Node<T> next) {
            return new ValueNode<>(value, next);
        }

        @Override
        public Optional<T> getValue() {
            return Optional.of(value);
        }

        @Override
        public Node<T> getNext() {
            return next;
        }
    }

    private static class EmptyNode<T> implements Node<T> {

        public static <T> Node<T> get() {
            return new EmptyNode<>();
        }


        @Override
        public Optional<T> getValue() {
            return Optional.empty();
        }

        @Override
        public Node<T> getNext() {
            return this;
        }
    }
}
