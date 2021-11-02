package ch.gauthey.alain.patterns.creational.abstractfactory;

class Green implements Color {

    @Override
    public void fill() {
        System.out.println("Inside Green::fill() method.");
    }

}
