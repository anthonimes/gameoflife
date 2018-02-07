import java.io.*;
import javax.swing.Timer;
import java.awt.event.*;
import java.awt.Event;

public class Main {

    public static void main(String[] args) {

        LinkedList<Integer> liste = new LinkedList<Integer>();

        liste.add_beginning(new Node(new Integer(8)));
        liste.add_in_place(new Node(new Integer(5)));
        liste.add_in_place(new Node(new Integer(10)));
        liste.add_in_place(new Node(new Integer(12)));
        liste.add_in_place(new Node(new Integer(4)));

        System.out.println(liste);
    }

    /*public static void main(String[] args) {
        final String message = args[0] + " ";
        System.out.print((char) Event.ESCAPE + "7");

        Timer t = new Timer(200, new ActionListener() {
            int i = 0;

            public void actionPerformed(ActionEvent e) {
                System.out.println(message.substring(i % message.length())
                        + message.substring(0, i % message.length()));
                i++;
            }
        });
        t.start();
        try {
            System.in.read();
        } catch (IOException e) {
        }

        t.stop();
    }*/

}