package com.mechanist.gravityphysicssimulator.Math;

import com.mechanist.gravityphysicssimulator.WindowElements.BaseElement;

public class Gravity {
    private static float acceleration;

    public static void gravity(BaseElement element, float gravityStrength) {
        acceleration += gravityStrength;
        element.setPosition(Vector3f.add(element.getPosition(), new Vector3f(0, acceleration, 0)));
    }
}
