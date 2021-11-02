package ch.gauthey.alain.patterns.creational.abstractfactory;

public abstract class AbstractFactory {
    public abstract Color getColor(String color);

    public abstract Shape getShape(String shapeType);
}
