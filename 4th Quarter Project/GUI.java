
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI {
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Ballistics Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel emptyLabel = new JLabel("test");
        emptyLabel.setPreferredSize(new Dimension(800, 640));
        frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {createAndShowGUI();}
        });

    }
}