package ch.gauthey.alain.demo;

import ch.gauthey.alain.patterns.structural.facade.ShapeMaker;

public class FacadePatternDemo {
    public static void main(String[] args) {
        ShapeMaker shapeMaker = new ShapeMaker();

        shapeMaker.drawCircle();
        shapeMaker.drawRectangle();
        shapeMaker.drawSquare();
    }
}