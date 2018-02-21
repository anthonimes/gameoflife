package model;

import java.util.Objects;

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

    public LinkedList(LinkedList<T> l) {
        this.head = new Node<T>(l.get_head().get_value());
        Node<T> tmp = l.get_head().get_next();

        while (tmp != null) {
            this.add_end(new Node<T>(tmp.get_value()));
            tmp = tmp.get_next();
        }
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
     * Computes the size of the list
     * @result the size of the list
     */
    public int size() {

        if (is_empty())
            return 0;
        else {
            Node<T> node_tmp = this.head;
            int compteur = 1;

            while ((node_tmp = node_tmp.get_next()) != null)
                compteur++;

            return compteur;
        }

    }

    /**
     * Add a model.Node in the beginning of the list
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

        // Is the list empty?
        Node<T> tmp = head;

        if (tmp == null) {
            this.add_beginning(n);
        } else {
            while (tmp.get_next() != null)
                tmp = tmp.get_next();
            tmp.set_next(n);
        }

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
            // If the value of tmp is greater than the value of n, we stop
            if (n.compareTo(tmp) < 0) {
                break;
            }
            prec = tmp;
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

    public boolean remove(Node<T> n) {

        Node<T> tmp = this.head;

        while (!(tmp.get_next()).equals(n) && tmp != null) {

            tmp = tmp.get_next();

        }

        // We exit the while loop with tmp.get_next() == n or tmp == null
        if (tmp == null) {
            return false;
        } else {
            tmp.set_next(n.get_next());
            return true;
        }


    }

    /**
     * Tests if the list contains a given node
     *
     * @param n the node to search
     * @return a reference to the node, if found
     */
    public Node<T> find(Node<T> n) {

        Node<T> tmp = this.head;

        while (tmp != null && n.compareTo(tmp) >= 0) {

            if (n.equals(tmp)) {
                return tmp;
            }
            tmp = tmp.get_next();

        }

        return null;

    }

    /**
     * Tests if the list contains a given node *starting* from a given point
     *
     * @param n the node to search
     * @return a reference to the node, if found
     */
    public Node<T> find(Node<T> cursor, Node<T> n) {

        Node<T> tmp = cursor;

        while (tmp != null && n.compareTo(tmp) >= 0) {

            if (n.equals(tmp)) {
                return tmp;
            }
            tmp = tmp.get_next();

        }

        return null;

    }

    public Node<T> get_head() {
        return this.head;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkedList<?> that = (LinkedList<?>) o;

        if (this.size() != that.size())
            return false;

        Node<?> node_tmp = that.get_head();
        Node<?> node_tmp_bis = this.get_head();
        while (node_tmp != null) {
            if (!((node_tmp_bis.get_value()).equals(node_tmp.get_value())))
                return false;
            else {
                node_tmp = node_tmp.get_next();
                node_tmp_bis = node_tmp_bis.get_next();
            }
        }

        return true;

    }

    @Override
    public int hashCode() {

        return Objects.hash(head);
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
