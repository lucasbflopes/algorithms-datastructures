package datastructures;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class MaxHeap<T extends Comparable<T>> implements PriorityQueue<T>{

    private List<T> heap;
    private int heapsize;

    public MaxHeap(List<T> list) {
        this.heap = list;
        this.heapsize = list.size();
        buildMaxHeap();
    }
    
    public int parent(int index) {
        return (index - 1) >> 1 ;
    }
    
    public int left(int index) {
        return (index << 1) + 1;
    }
    
    public int right(int index) {
        return (index << 1) + 2;
    }
    
    private void maxHeapify(int index) {
        int l = left(index);
        int r = right(index);
        int largest = index;
        
        if (l < heapsize && heap.get(l).compareTo(heap.get(largest)) > 0) {
            largest = l;
        }
        
        if (r < heapsize && heap.get(r).compareTo(heap.get(largest)) > 0) {
            largest = r;
        }
        
        if (largest != index) {
            T temp = heap.get(largest);
            heap.set(largest, heap.get(index));
            heap.set(index, temp);
            maxHeapify(largest);
        }
    }
    
    private void buildMaxHeap() {
        for (int i = (heapsize >> 1); i >= 0; i--) {
            maxHeapify(i);
        }
    }
    
    public List<T> toSortedList() {
        List<T> newList = new ArrayList<>(heap);
        MaxHeap<T> newHeap = new MaxHeap<>(newList);
        for (int i = (newHeap.heap.size() - 1); i >= 0; i--) {
            T temp = newHeap.heap.get(i);
            newHeap.heap.set(i, newHeap.heap.get(0));
            newHeap.heap.set(0, temp);
            newHeap.heapsize--;
            newHeap.maxHeapify(0);
        }
        return newHeap.heap;
    }
    
    public boolean isEmpty() {
        return heapsize == 0;
    }

    @Override
    public String toString() {
        return heap.toString();
    }

    @Override
    public T extractMax() {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        
        T maxElement = heap.get(0);
        heap.set(0, heap.get(heapsize - 1));
        heap.remove(heapsize - 1);
        heapsize--;
        maxHeapify(0);
        return maxElement;
    }

    @Override
    public T peek() {
        return heap.get(0);
    }

    @Override
    public void insert(T value) {
        heap.add(value);
        heapsize++;
        buildMaxHeap();
    }
}
