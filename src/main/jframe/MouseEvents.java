package main.jframe;

import main.WindowElements.CircleElement;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseEvents implements MouseListener {
    WindowController globalControl;
    public MouseEvents(WindowController control) {
        globalControl = control;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        CircleElement circle = new CircleElement(e.getXOnScreen(), e.getY(), "src\\resources\\circle.png");
        globalControl.displayElement(circle.filename, circle.x, circle.y);

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
