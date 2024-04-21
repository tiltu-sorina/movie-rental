package ro.ubb.fantastic3.server.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;
import ro.ubb.fantastic3.common.model.Movie;
import ro.ubb.fantastic3.common.model.validators.GenericValidator;
import ro.ubb.fantastic3.common.service.ClientService;
import ro.ubb.fantastic3.common.service.MovieService;
import ro.ubb.fantastic3.common.service.TransactionService;


@Configuration
@ComponentScan({"ro.ubb.fantastic3.server.service", "ro.ubb.fantastic3.common"})
@RequiredArgsConstructor
public class ServerConfig {
    //    @Autowired
    private final MovieService movieService;
    //    @Autowired
    private final ClientService clientService;
    //    @Autowired
    private final TransactionService transactionService;

    @Bean
    RmiServiceExporter rmiServiceExporterClient() {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("clientService");
        rmiServiceExporter.setServiceInterface(ClientService.class);
        rmiServiceExporter.setService(clientService);
        return rmiServiceExporter;
    }

    @Bean
    RmiServiceExporter rmiServiceExporterMovie() {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("movieService");
        rmiServiceExporter.setServiceInterface(MovieService.class);
        rmiServiceExporter.setService(movieService);
        return rmiServiceExporter;
    }

    @Bean
    RmiServiceExporter rmiServiceExporterTransaction() {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("transactionService");
        rmiServiceExporter.setServiceInterface(TransactionService.class);
        rmiServiceExporter.setService(transactionService);
        return rmiServiceExporter;
    }



}
