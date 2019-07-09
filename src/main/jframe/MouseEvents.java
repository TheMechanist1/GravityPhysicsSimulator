package main.jframe;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseEvents implements MouseListener {
    WindowController globalControl;
    public MouseEvents(WindowController control) {
        globalControl = control;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        globalControl.displayElement("src\\resources\\circle.png", e.getXOnScreen(), e.getYOnScreen());
    }

    @Override
    public void mousePressed(MouseEvent e) {

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
