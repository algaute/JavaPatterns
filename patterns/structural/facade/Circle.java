package ch.gauthey.alain.patterns.structural.facade;

class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("Cirlce::draw()");
    }

}
