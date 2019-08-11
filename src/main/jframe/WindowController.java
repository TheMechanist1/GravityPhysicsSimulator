package main.jframe;

import javax.swing.*;
import java.awt.*;

public class WindowController {
    private JFrame frame = new JFrame();

    public WindowController(String s, Dimension size) {
        MouseEvents event = new MouseEvents(this);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(size);
        frame.setTitle(s);
        frame.setLayout(null);
        frame.addMouseListener(event);
        frame.setVisible(true);
        frame.pack();
    }

    public void displayElement(String file, int x, int y) {
        ImageIcon icon = new ImageIcon(file);
        JLabel label = new JLabel(icon);
        label.setBounds(x - icon.getIconWidth() / 2, y - icon.getIconHeight() / 2, icon.getIconWidth(), icon.getIconHeight());

        frame.add(label, 0);
        frame.pack();
        frame.repaint();
    }

}
