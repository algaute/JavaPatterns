package ch.gauthey.alain.patterns.creational.abstractfactory;

class Red implements Color {

    @Override
    public void fill() {
        System.out.println("Inside Red::fill() method.");
    }

}
