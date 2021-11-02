package ch.gauthey.alain.patterns.creational.abstractfactory;

class Blue implements Color {

    @Override
    public void fill() {
        System.out.println("Inside Blue::fill() method.");
    }

}
