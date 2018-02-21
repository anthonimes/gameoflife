package model;

import java.util.Objects;

/**
 * Created by anthony on 07/02/2018.
 */
public class Cells implements Comparable<Cells> {

    public static final int _DEAD = 0;
    public static final int _ALIVE = 1;
    private long x;
    private long y;
    private int nb_neighbors;
    private int status;

    public Cells() {

        this.x = 0;
        this.y = 0;
        this.nb_neighbors = 0;
        this.status = _ALIVE;

    }

    public Cells(long _x, long _y, int _nb_neighbors, int _status) {

        this.x = _x;
        this.y = _y;
        this.nb_neighbors = _nb_neighbors;
        this.status = _status;

    }

    public long get_X() {
        return this.x;
    }

    public long get_Y() {
        return this.y;
    }

    public int get_nb_neighbors() {
        return this.nb_neighbors;
    }

    public void set_nb_neighbors(int _nb_neighbors) {
        this.nb_neighbors = _nb_neighbors;
    }

    public int get_status() {
        return this.status;
    }

    public void set_status(int _status) {
        this.status = _status;
    }

    @Override
    public int compareTo(Cells o) {
        if (this.x == o.get_X())
            return (int) (this.y - o.get_Y());
        return (int) (this.x - o.get_X());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cells cells = (Cells) o;
        return x == cells.x &&
                y == cells.y;
    }

    @Override
    public int hashCode() {

        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")" + "[" + this.nb_neighbors + "," + this.status + "]";
    }

}
