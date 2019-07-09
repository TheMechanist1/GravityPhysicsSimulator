package main.jframe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class WindowController {
    JFrame frame = new JFrame();

    public WindowController(String s, Dimension size) {
        MouseEvents event = new MouseEvents(this);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(size);
        frame.setTitle(s);

        frame.addMouseListener(event);
        frame.setVisible(true);
        frame.pack();
    }

    public void displayElement(String file, int x, int y) {
        JPanel panel = new JPanel();
        ImageIcon icon = new ImageIcon(file);
        JLabel label = new JLabel(icon);
        label.setLocation(x,y);
        panel.add(label);
        frame.pack();
    }

}
