package ch.gauthey.alain.patterns.creational.abstractfactory;

class Square implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }

}
