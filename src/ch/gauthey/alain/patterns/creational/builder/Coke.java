package ch.gauthey.alain.patterns.creational.builder;

class Coke extends ColdDrink {

    @Override
    public String name() {
        return "Pepsi";
    }

    @Override
    public float price() {
        return 35.0f;
    }

}
