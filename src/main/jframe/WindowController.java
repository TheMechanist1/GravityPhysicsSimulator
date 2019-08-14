package main.jframe;

import main.GravityPhysicsSimulator;
import main.WindowElements.BaseElement;

import javax.swing.*;
import java.awt.*;

public class WindowController {
    private JFrame frame = new JFrame();

    String s;
    Dimension size;
    public WindowController(String s, Dimension size) {
        this.s = s;
        this.size = size;
    }

    public void setup(GravityPhysicsSimulator gps) {
        MouseEvents event = new MouseEvents(this, gps);
        frame.setPreferredSize(size);
        frame.setTitle(s);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.addMouseListener(event);
        frame.setVisible(true);
        frame.pack();
    }

    public void render(GravityPhysicsSimulator gps, String fps) {
        frame.setTitle(fps);
        for (int i = 0; i < gps.elements.size(); i++) {
            BaseElement b = gps.elements.get(i);
            this.displayElement(b.filename, b.x, b.y);
        }

    }

    public void displayElement(String file, double x, double y) {
        ImageIcon icon = new ImageIcon(file);
        JLabel label = new JLabel(icon);
        label.setBounds((int) x - icon.getIconWidth() / 2, (int) y - icon.getIconHeight() / 2, icon.getIconWidth(), icon.getIconHeight());

        frame.add(label, 0);
        frame.pack();
        frame.repaint();
    }

}
