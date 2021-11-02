package ch.gauthey.alain.patterns.creational.builder;

interface Item {
    public String name();

    public Packing packing();

    public float price();
}