package example.nestedClasses;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomLinkedList<T> implements Iterable<T> {
    private Node head;

    public class Node {
        T data;
        Node next;
        Node(T data) {
            this.data = data;
        }
    }

    public void add(T data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node currentNode = head;
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }

            currentNode.next = newNode;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    // Non-static Inner class
    private class ListIterator implements Iterator<T> {
        private Node current = head; // can access outer class instance variable

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            T data = current.data;
            current = current.next;
            return data;
        }
    }

    public static void main(String args[]) {
        CustomLinkedList<String> stringLinkedList = new CustomLinkedList<>();
        stringLinkedList.add("Hello World");
        stringLinkedList.add("Welcome to Java");
        stringLinkedList.add("Nested Classes");
        stringLinkedList.forEach(System.out::println);

        CustomLinkedList<Integer> integerLinkedList = new CustomLinkedList<>();
        integerLinkedList.add(123);
        integerLinkedList.add(345);
        integerLinkedList.add(987);
        integerLinkedList.forEach(System.out::println);
    }
}
