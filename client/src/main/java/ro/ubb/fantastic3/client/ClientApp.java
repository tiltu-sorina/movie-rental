package ro.ubb.fantastic3.client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.ubb.fantastic3.client.ui.AppConsole;

public class ClientApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("ro.ubb.fantastic3.client.config");
        context.getBean(AppConsole.class).runMenu();
    }
}
