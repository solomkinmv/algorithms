package coding_problems.sedgewick.coursera;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] array;
    private int startPos;
    private int endPos;
    private int size;

    public RandomizedQueue() {
        array = createArrayOfSize(12);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = endPos; i < startPos; i++) {
            sb.append(array[i]).append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();

        if (startPos == array.length) {
            resize();
        }

        array[startPos++] = item;
        size++;
    }

    private void resize() {
        if (size == array.length) {
            array = moveToTheBeginning(createArrayOfSize(array.length * 2));
        } else {
            array = moveToTheBeginning(createArrayOfSize(array.length));
        }
    }

    private Item[] moveToTheBeginning(Item[] newArray) {
        System.arraycopy(array, endPos, newArray, 0, size);
        endPos = 0;
        startPos = size;

        return newArray;
    }

    private Item[] createArrayOfSize(int capacity) {
        return (Item[]) new Object[capacity];
    }

    public Item dequeue() {
        validateNotEmpty();

        int randomIndex = StdRandom.uniform(endPos, startPos);
        Item item = array[randomIndex];
        swap(randomIndex, --startPos);
        ifEmptyResetPositions();
        shrinkIfNecessary();

        size--;
        return item;
    }

    private void shrinkIfNecessary() {
        if (size > array.length / 4) return;
        Item[] newArray = createArrayOfSize(size);
        int index = 0;
        for (int i = endPos; i < startPos; i++) {
            newArray[index++] = array[i];
        }
        array = newArray;
    }

    private void swap(int pos1, int pos2) {
        Item tmp = array[pos1];
        array[pos1] = array[pos2];
        array[pos2] = tmp;
    }

    private void ifEmptyResetPositions() {
        if (endPos == startPos) {
            startPos = 0;
            endPos = 0;
        }
    }

    public Item sample() {
        validateNotEmpty();
        return array[StdRandom.uniform(endPos, startPos)];
    }

    private void validateNotEmpty() {
        if (size == 0) throw new NoSuchElementException();
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator<>(this.array, endPos, startPos);
    }

    private static class RandomizedQueueIterator<Item> implements Iterator<Item> {

        private final Item[] array;
        private int position;

        private RandomizedQueueIterator(Item[] array, int lowerPos, int higherPos) {
            this.array = Arrays.copyOfRange(array, lowerPos, higherPos);
            StdRandom.shuffle(this.array);
        }

        @Override
        public boolean hasNext() {
            return position < array.length;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return array[position++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
