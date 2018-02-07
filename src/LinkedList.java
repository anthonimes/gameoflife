/**
 * Created by anthony on 07/02/2018.
 */
public class LinkedList<T extends Comparable<T>> {

    private Node<T> head;

    /**
     * Constructor: builds an empty list
     */
    public LinkedList() {
        head = null;
    }

    /**
     * Is the list empty?
     *
     * @return true if empty
     */
    public boolean is_empty() {

        return (this.head == null);

    }

    /**
     * Add a Node in the beginning of the list
     *
     * @param n the node to add
     */
    public void add_beginning(Node<T> n) {

        if (is_empty())
            head = n;
        else {
            n.set_next(head);
            head = n;
        }

    }

    /**
     * Adds a node at the end of the list
     *
     * @param n the node to add
     */
    public void add_end(Node<T> n) {

        Node<T> tmp = head;
        while (tmp.get_next() != null)
            tmp = tmp.get_next();
        tmp.set_next(n);

    }

    /**
     * Adds the node where it should be
     * T needs to implement Comparable
     *
     * @param n the node to add
     */
    public void add_in_place(Node<T> n) {

        Node<T> tmp = head;
        Node<T> prec = null;

        while (tmp != null) {
            System.out.println(tmp.get_value() + " " + n.get_value() + " " + n.compareTo(tmp));
            // If the value of tmp is greater than the value of n, we stop
            if (n.compareTo(tmp) < 0) {
                prec = tmp;
                break;
            }
            tmp = tmp.get_next();
        }

        // The head is greater than the node to add
        if (tmp == head)
            this.add_beginning(n);
            // The node is greater than all other values in list
        else if (prec == null)
            this.add_end(n);
        else {
            n.set_next(tmp);
            prec.set_next(n);
        }

    }

    @Override
    public String toString() {

        Node<T> tmp = head;
        String s = "";

        while (tmp != null) {
            s += tmp + " ";
            tmp = tmp.get_next();
        }

        return s;
    }


}
