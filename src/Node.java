/**
 * Created by anthony on 07/02/2018.
 */
public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {

    private T value;
    private Node<T> next;

    public Node(T _value) {

        this.value = _value;
        next = null;

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
    public String toString() {
        return value.toString();
    }
}

