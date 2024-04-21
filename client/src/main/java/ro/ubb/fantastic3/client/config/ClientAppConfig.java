package ro.ubb.fantastic3.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import ro.ubb.fantastic3.common.service.ClientService;
import ro.ubb.fantastic3.common.service.MovieService;
import ro.ubb.fantastic3.common.service.TransactionService;

@Configuration
@ComponentScan("ro.ubb.fantastic3.client")
public class ClientAppConfig {

    @Bean
    RmiProxyFactoryBean rmiProxyFactoryBeanClient() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceInterface(ClientService.class);
        rmiProxyFactoryBean.setServiceUrl("rmi://localhost:1099/clientService");
        return rmiProxyFactoryBean;
    }

    @Bean
    RmiProxyFactoryBean rmiProxyFactoryBeanMovie() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceInterface(MovieService.class);
        rmiProxyFactoryBean.setServiceUrl("rmi://localhost:1099/movieService");
        return rmiProxyFactoryBean;
    }

    @Bean
    RmiProxyFactoryBean rmiProxyFactoryBeanTransaction() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceInterface(TransactionService.class);
        rmiProxyFactoryBean.setServiceUrl("rmi://localhost:1099/transactionService");
        return rmiProxyFactoryBean;
    }

//    @Bean
//    MovieService movieService(){
//        return new MovieServiceClient();
//    }

//    @Bean
//    AppConsole console(){
//        return new AppConsole();
//    }
//    @Bean
//    MovieConsole movieConsole(){
//        return new MovieConsole(new MovieServiceClient());
//    }
//    @Bean
//    ClientConsole clientConsole(){
//        return new ClientConsole(new ClientServiceClient());
//    }
//
//    @Bean
//    RentalConsole rentalConsole(){
//        return new RentalConsole(new TransactionServiceClient(), new MovieServiceClient(), new ClientServiceClient());
//    }


}
