package datastructures;

import java.util.StringJoiner;

public class SinglyLinkedList<T> {

    private Node head = null;
    private int length = 0;

    private class Node {
        private T value;
        private Node next;

        public Node(T value) {
            this.value = value;
        }
    }

    public SinglyLinkedList<T> add(T value) {
        if (head == null) {
            head = new Node(value);
        } else {
            Node node = head;
            while (node.next != null) {
                node = node.next;
            }
            node.next = new Node(value);
        }
        length++;
        return this;
    }

    public void addAll(T[] values) {
        for (T value : values) {
            add(value);
        }
    }

    public void addTo(int index, T value) {
        if (index >= length) {
            throw new IndexOutOfBoundsException();
        }

        Node newNode = new Node(value);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node node = head;
            for (int i = 0; i < index - 1; i++) {
                node = node.next;
            }
            Node nextNode = node.next;
            node.next = newNode;
            newNode.next = nextNode;
        }
        length++;
    }

    public int size() {
        return length;
    }

    public T get(int index) {
        if (index >= length) {
            throw new IndexOutOfBoundsException();
        }

        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.value;
    }

    @Override
    public String toString() {
        StringJoiner output = new StringJoiner(", ", "[", "]");
        Node node = head;
        while (node != null) {
            output.add(String.valueOf(node.value));
            node = node.next;
        }
        return output.toString();
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.add(10).add(20).add(4).add(41);
        System.out.println(list);
        list.addTo(3, 12);
        System.out.println(list);
        System.out.println(list.size());
        System.out.println(list.get(4));
    }
}
