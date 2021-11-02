package ch.gauthey.alain.patterns.creational.abstractfactory;

class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }

}
