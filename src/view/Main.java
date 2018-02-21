package view;

import model.*;

import java.io.IOException;
import javax.swing.Timer;

public class Main {

    public static void main(String[] args) {

        LinkedList<Cells> beehive = new LinkedList<Cells>();
        LinkedList<Cells> blinker = new LinkedList<Cells>();
        LinkedList<Cells> glider = new LinkedList<Cells>();
        LinkedList<Cells> pentamino = new LinkedList<Cells>();
        LinkedList<Cells> pentadecathlon = new LinkedList<Cells>();

        // Beehive
        beehive.add_in_place(new Node(new Cells(0, 0, 0, 1)));
        beehive.add_in_place(new Node(new Cells(-1, 1, 0, 1)));
        beehive.add_in_place(new Node(new Cells(-1, 2, 0, 1)));
        beehive.add_in_place(new Node(new Cells(0, 3, 0, 1)));
        beehive.add_in_place(new Node(new Cells(1, 1, 0, 1)));
        beehive.add_in_place(new Node(new Cells(1, 2, 0, 1)));

        // Blinker
        blinker.add_in_place(new Node(new Cells(0, 0, 0, 1)));
        blinker.add_in_place(new Node(new Cells(-1, 0, 0, 1)));
        blinker.add_in_place(new Node(new Cells(1, 0, 0, 1)));
        blinker.add_in_place(new Node(new Cells(8, 6, 0, 1)));
        blinker.add_in_place(new Node(new Cells(7, 6, 0, 1)));
        blinker.add_in_place(new Node(new Cells(9, 6, 0, 1)));

        // Glider
        glider.add_in_place(new Node(new Cells(0, 0, 0, 1)));
        glider.add_in_place(new Node(new Cells(1, 1, 0, 1)));
        glider.add_in_place(new Node(new Cells(1, 2, 0, 1)));
        glider.add_in_place(new Node(new Cells(0, 2, 0, 1)));
        glider.add_in_place(new Node(new Cells(-1, 2, 0, 1)));

        // R Pentamino
        pentamino.add_in_place(new Node(new Cells(0, 0, 0, 1)));
        pentamino.add_in_place(new Node(new Cells(0, 1, 0, 1)));
        pentamino.add_in_place(new Node(new Cells(1, 1, 0, 1)));
        pentamino.add_in_place(new Node(new Cells(-1, 1, 0, 1)));
        pentamino.add_in_place(new Node(new Cells(-1, 2, 0, 1)));

        // Pentadecathlon
        pentadecathlon.add_in_place(new Node(new Cells(0, 0, 0, 1)));
        pentadecathlon.add_in_place(new Node(new Cells(0, -1, 0, 1)));
        pentadecathlon.add_in_place(new Node(new Cells(0, 1, 0, 1)));
        pentadecathlon.add_in_place(new Node(new Cells(-1, 2, 0, 1)));
        pentadecathlon.add_in_place(new Node(new Cells(-1, -2, 0, 1)));
        pentadecathlon.add_in_place(new Node(new Cells(-2, 2, 0, 1)));
        pentadecathlon.add_in_place(new Node(new Cells(-2, -2, 0, 1)));
        pentadecathlon.add_in_place(new Node(new Cells(-3, 1, 0, 1)));
        pentadecathlon.add_in_place(new Node(new Cells(-3, 0, 0, 1)));
        pentadecathlon.add_in_place(new Node(new Cells(-3, -1, 0, 1)));

        // Pentadecathlon
        pentadecathlon.add_in_place(new Node(new Cells(5, 0, 0, 1)));
        pentadecathlon.add_in_place(new Node(new Cells(5, -1, 0, 1)));
        pentadecathlon.add_in_place(new Node(new Cells(5, 1, 0, 1)));
        pentadecathlon.add_in_place(new Node(new Cells(6, 2, 0, 1)));
        pentadecathlon.add_in_place(new Node(new Cells(6, -2, 0, 1)));
        pentadecathlon.add_in_place(new Node(new Cells(7, 2, 0, 1)));
        pentadecathlon.add_in_place(new Node(new Cells(7, -2, 0, 1)));
        pentadecathlon.add_in_place(new Node(new Cells(8, 1, 0, 1)));
        pentadecathlon.add_in_place(new Node(new Cells(8, 0, 0, 1)));
        pentadecathlon.add_in_place(new Node(new Cells(8, -1, 0, 1)));

        Model m = new Model(pentadecathlon);
        m.type_configuration(20);

        System.out.println("Configuration de type " + m.getMove() + " " + m.getPeriod());

        Timer t = new Timer(1000, ActionEvent -> {
            m.compute();
            System.out.println(m);
        });
        t.start();
        try {
            System.in.read();
        } catch (IOException e) {
        }

        t.stop();


    }

}