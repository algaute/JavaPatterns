package ch.gauthey.alain.demo;

import ch.gauthey.alain.patterns.creational.singleton.SingleObject;

public class SingletonPatternDemo {
    public static void main(String[] args) {

        // illegal construct
        // Compile Time Error: The constructor SingleObject() is not visible
        // SingleObject object = new SingleObject();

        // Get the only object available
        SingleObject object = SingleObject.getInstance();

        // show the message
        object.showMessage();
    }
}
