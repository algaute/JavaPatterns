package ch.gauthey.alain.demo;

import ch.gauthey.alain.patterns.behavioral.command.Broker;
import ch.gauthey.alain.patterns.behavioral.command.BuyStock;
import ch.gauthey.alain.patterns.behavioral.command.SellStock;
import ch.gauthey.alain.patterns.behavioral.command.Stock;

public class CommandPatternDemo {
    public static void main(String[] args) {
        Stock abcStock = new Stock();

        BuyStock buyStockOrder = new BuyStock(abcStock);
        SellStock sellStockOrder = new SellStock(abcStock);

        Broker broker = new Broker();
        broker.takeOrder(buyStockOrder);
        broker.takeOrder(sellStockOrder);

        broker.placeOrders();
    }
}
