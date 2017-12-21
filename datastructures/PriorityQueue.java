package datastructures;

public interface PriorityQueue<T extends Comparable<T>> {

    public T extractMax();

    public T peek();

    public void insert(T value);
}
