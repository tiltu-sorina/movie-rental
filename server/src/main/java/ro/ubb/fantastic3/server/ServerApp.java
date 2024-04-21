package ro.ubb.fantastic3.server;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;



@Configuration
@ComponentScan
public class ServerApp {

    public static void main(String[] args) {

//        new AnnotationConfigApplicationContext("ro.ubb.fantastic3.server.config");
        new AnnotationConfigApplicationContext(ServerApp.class);
    }
}

