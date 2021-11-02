package ch.gauthey.alain.web;

import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class InitialisationDaoFactory implements ServletContextListener {

    public InitialisationDaoFactory() {
        System.out.println("InitialisationDaoFactory");
    }

}
