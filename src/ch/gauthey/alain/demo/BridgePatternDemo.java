package ch.gauthey.alain.demo;

import ch.gauthey.alain.patterns.structural.bridge.Circle;
import ch.gauthey.alain.patterns.structural.bridge.GreenCircle;
import ch.gauthey.alain.patterns.structural.bridge.RedCircle;
import ch.gauthey.alain.patterns.structural.bridge.Shape;

public class BridgePatternDemo {
    public static void main(String[] args) {
        Shape redCircle = new Circle(100, 100, 10, new RedCircle());
        Shape greenCircle = new Circle(100, 50, 5, new GreenCircle());

        redCircle.draw();
        greenCircle.draw();
    }
}
