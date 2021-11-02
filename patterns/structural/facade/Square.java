package ch.gauthey.alain.patterns.structural.facade;

class Square implements Shape {

    @Override
    public void draw() {
        System.out.println("Square::draw()");
    }

}
