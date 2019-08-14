package main.jframe;

import main.GravityPhysicsSimulator;
import main.WindowElements.CircleElement;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseEvents implements MouseListener {
    WindowController globalControl;
    GravityPhysicsSimulator gps;

    public MouseEvents(WindowController control, GravityPhysicsSimulator gps) {
        globalControl = control;
        this.gps = gps;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        CircleElement circle = new CircleElement(e.getX(), e.getY(), "src\\resources\\circle.png");
        gps.elements.add(circle);
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
