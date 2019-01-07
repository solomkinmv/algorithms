package coding_problems.sedgewick.coursera;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> first; // first -> last
    private Node<Item> last;
    private int size;

    public Deque() {
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        System.out.println();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<Item> node = first;
        while (node != null) {
            sb.append(node.item).append(", ");
            node = node.next;
        }
        sb.append("]");
        return sb.toString();
    }

    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();

        if (size == 0) {
            initFirstElement(item);
        } else {
            first = new Node<>(item, first);
            first.next.previous = first;
        }

        size++;
    }

    private void initFirstElement(Item item) {
        Node<Item> node = new Node<>(item);
        first = node;
        last = node;
    }

    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();

        if (size == 0) {
            initFirstElement(item);
        } else {
            last.next = new Node<>(item, null, last);
            last = last.next;
        }

        size++;
    }

    public Item removeFirst() {
        validateNotEmpty();

        Item item = first.item;
        if (size == 1) {
            first = null;
            last = null;
        } else {
            first = first.next;
            first.previous = null;
        }

        size--;
        return item;
    }

    public Item removeLast() {
        validateNotEmpty();

        Item item = last.item;
        if (size == 1) {
            first = null;
            last = null;
        } else {
            last = last.previous;
            last.next = null;
        }

        size--;
        return item;
    }

    private void validateNotEmpty() {
        if (size == 0) throw new NoSuchElementException();
    }

    public Iterator<Item> iterator() {
        return new ListIterator<>(first);
    }

    private static class Node<Item> {
        private final Item item;
        private Node<Item> next;
        private Node<Item> previous;

        private Node(Item item) {
            this.item = item;
        }

        private Node(Item item, Node<Item> next) {
            this.item = item;
            this.next = next;
        }

        private Node(Item item, Node<Item> next, Node<Item> previous) {
            this.item = item;
            this.next = next;
            this.previous = previous;
        }
    }

    private static class ListIterator<Item> implements Iterator<Item> {

        private Node<Item> node;

        private ListIterator(Node<Item> node) {
            this.node = node;
        }

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();


            Item item = node.item;
            node = node.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
