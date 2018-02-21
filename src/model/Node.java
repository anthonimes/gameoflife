package model;

import java.util.Objects;

/**
 * Created by anthony on 07/02/2018.
 * Class Node for Linked List for generic types T
 * Nodes need to be compared so both Node and T must implement Comparable
 */
public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {

    private T value;
    private Node<T> next;

    public Node() {

        this.value = null;
        this.next = null;

    }

    public Node(T _value) {

        this.value = _value;
        this.next = null;

    }

    public T get_value() {
        return this.value;
    }

    public void set_value(T _value) {
        this.value = _value;
    }

    public Node<T> get_next() {
        return this.next;
    }

    public void set_next(Node<T> n) {
        this.next = n;

    }

    @Override
    public int compareTo(Node<T> o) {
        return value.compareTo(o.get_value());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node<?> node = (Node<?>) o;
        return Objects.equals(value, node.value);
    }

    @Override
    public int hashCode() {

        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}

