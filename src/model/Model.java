package model;

import sun.awt.image.ImageWatched;

import javax.sound.sampled.Line;
import java.util.Objects;

/**
 * Created by anthony on 07/02/2018.
 */
public class Model {
    public final short _MORT = 4;
    public final short _STABLE = 5;
    public final short _OSCILLATEUR = 6;
    public final short _VAISSEAU = 7;
    public final short _INCONNU = 8;
    private LinkedList<Cells> initial;
    private LinkedList<Cells> current;
    private LinkedList<Cells> neighbors;
    private int period;
    private int queue;
    private int move;

    public Model(String fichier) {


        period = 0;
        queue = 0;
        move = 0;

    }

    public Model() {

        initial = new LinkedList<Cells>();
        current = new LinkedList<Cells>();
        neighbors = new LinkedList<Cells>();

        period = 0;
        queue = 0;
        move = 0;

    }

    public Model(LinkedList<Cells> l) {

        initial = new LinkedList<Cells>(l);
        current = new LinkedList<Cells>(l);
        neighbors = new LinkedList<>();

        period = 0;
        queue = 0;
        move = 0;

    }

    /**
     * Generates the number of neighbors for each cell from current generation
     *
     * @return nothing: the result is stored in current
     */
    public void compute() {

        // Traversal of the current generation
        Node<Cells> node_tmp = current.get_head();

        while (node_tmp != null) {

            update_neighbors(node_tmp);
            node_tmp = node_tmp.get_next();

        }

        this.current = next_generation();

    }

    // TODO : use iterable-like traversal
    public void update_neighbors(Node<Cells> c) {

        long x = c.get_value().get_X();
        long y = c.get_value().get_Y();
        int s = c.get_value().get_status();

        Node<Cells> find = neighbors.find(c);

        // Have we seen this cell already?
        if (find == null)
            neighbors.add_in_place(new Node(new Cells(x, y, 0, s)));
            // If we do, we update its status as _ALIVE
        else
            find.get_value().set_status(s);

        // We check all the neighbors of the living cell
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                if (i != 1 || j != 1) {
                    Node<Cells> tmp = new Node(new Cells(x - (i - 1), y - (j - 1), 1, 0));
                    Node<Cells> update = neighbors.find(tmp);
                    // Does this cell have a living neighbor already?
                    if (update != null) {
                        Cells to_update = update.get_value();
                        to_update.set_nb_neighbors(to_update.get_nb_neighbors() + 1);
                    } else {
                        neighbors.add_in_place(tmp);
                    }
                }
            }
        }
    }

    // TODO : start from the end, and add_beginning!
    // WAIT : no can do :) liste *simplement* chaînée
    public LinkedList<Cells> next_generation() {

        LinkedList<Cells> next = new LinkedList<>();
        Node<Cells> node_tmp = neighbors.get_head();

        while (node_tmp != null) {

            Cells cell_tmp = node_tmp.get_value();

            int status = cell_tmp.get_status();
            int nb_neighbors = cell_tmp.get_nb_neighbors();

            if ((status == Cells._ALIVE) && (nb_neighbors == 2 || nb_neighbors == 3)) {

                next.add_end(new Node(cell_tmp));
            }

            if ((status == Cells._DEAD) && (nb_neighbors == 3)) {

                cell_tmp.set_status(Cells._ALIVE);
                next.add_end(new Node(cell_tmp));

            }

            node_tmp = node_tmp.get_next();

        }

        neighbors = new LinkedList<>();
        return next;

    }

    public boolean detect_glider(Model m) {

        LinkedList<Cells> c = m.getCurrent();
        Node<Cells> node_tmp = (this.getCurrent()).get_head();
        Node<Cells> node_tmp_bis = c.get_head();

        if (c.size() != this.current.size())
            return false;

        // We compute the expected translation
        long d_x = Math.abs(node_tmp.get_value().get_X() - node_tmp_bis.get_value().get_X());
        long d_y = Math.abs(node_tmp.get_value().get_Y() - node_tmp_bis.get_value().get_Y());

        // We go through both lists and check equality for nodes modulo translation
        while ((node_tmp = node_tmp.get_next()) != null) {

            node_tmp_bis = node_tmp_bis.get_next();
            long n_x = node_tmp.get_value().get_X();
            long n_y = node_tmp.get_value().get_Y();

            long c_x = node_tmp_bis.get_value().get_X();
            long c_y = node_tmp_bis.get_value().get_Y();

            if ((c_x != n_x + d_x && n_x != c_x + d_x) || (c_y != n_y + d_y && n_y != c_y + d_y))
                return false;

        }

        return true;

    }

    public void type_configuration(int _iteration) {

        LinkedList<Cells> one = new LinkedList<>(current);
        Model m_one = new Model(one);
        LinkedList<Cells> two = new LinkedList<>(current);
        Model m_two = new Model(two);
        m_two.compute();
        two = m_two.getCurrent();
        if (one.equals(two)) {
            this.period = 1;
            this.move = _STABLE;
        } else {
            int iteration = _iteration;

            int cpt = 1;

            while (cpt < _iteration) {
                if (one.size() == 0) {
                    this.move = _MORT;
                    break;
                } else if (one.equals(two)) {
                    this.period = cpt;
                    this.move = _OSCILLATEUR;
                    break;
                } else if (m_one.detect_glider(m_two)) {
                    this.period = cpt;
                    this.move = _VAISSEAU;
                    break;
                } else {
                    cpt++;
                    m_one.compute();

                    if (one.equals(m_one.getCurrent())) {
                        this.period = cpt;
                        this.move = _STABLE;
                        break;
                    }

                    one = m_one.getCurrent();
                    m_two.compute();
                    m_two.compute();
                    two = m_two.getCurrent();
                }
            }

            if (cpt == _iteration) {
                this.move = _INCONNU;
                this.period = _iteration;
            }

        }

    }

    public long min_lignes() {
        Node<Cells> head = current.get_head();
        long min = 0;

        while (head != null) {
            long x = head.get_value().get_X();
            if (x < min) min = x;
            head = head.get_next();
        }

        return min;
    }

    public long max_lignes() {
        Node<Cells> head = current.get_head();
        long max = 0;

        while (head != null) {
            long x = head.get_value().get_X();
            if (x > max) max = x;
            head = head.get_next();
        }

        return max;
    }

    public long min_colonnes() {
        Node<Cells> head = current.get_head();
        long min = 0;

        while (head != null) {
            long y = head.get_value().get_Y();
            if (y < min) min = y;
            head = head.get_next();
        }

        return min;
    }

    public long max_colonnes() {
        Node<Cells> head = current.get_head();
        long max = 0;

        while (head != null) {
            long y = head.get_value().get_Y();
            if (y > max) max = y;
            head = head.get_next();
        }

        return max;
    }

    public LinkedList<Cells> getCurrent() {
        return current;
    }

    public void setCurrent(LinkedList<Cells> current) {
        this.current = current;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getQueue() {
        return queue;
    }

    public void setQueue(int queue) {
        this.queue = queue;
    }

    public int getMove() {
        return move;
    }

    public void setMove(int move) {
        this.move = move;
    }

    @Override
    public int hashCode() {

        return Objects.hash(initial, current, neighbors, _MORT, _STABLE, _OSCILLATEUR, _VAISSEAU, _INCONNU, period, queue, move);
    }

    public String toString() {
        Node<Cells> head = current.get_head();
        String s = "";

        long x = head.get_value().get_X();
        long y = head.get_value().get_Y();

        long min_lignes = this.min_lignes();
        long min_colonnes = this.min_colonnes();

        long max_lignes = this.max_lignes();
        long max_colonnes = this.max_colonnes();

        for (long i = min_lignes; i < max_lignes + 1; i++) {
            for (long j = min_colonnes; j < max_colonnes + 1; j++) {
                if (x == i && y == j) {
                    s += " * ";

                    if ((head = head.get_next()) != null) {

                        x = head.get_value().get_X();
                        y = head.get_value().get_Y();

                    }
                } else {
                    s += " . ";
                }
            }
            s += "\n";
        }

        return s;

    }
}
