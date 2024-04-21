package ro.ubb.fantastic3.common.service;

import org.springframework.stereotype.Service;
import ro.ubb.fantastic3.common.model.Client;

import java.util.List;
import java.util.Set;
public interface ClientService {
    Client addClient(Client client);
    Client getClient(Long id);
    Client updateClient(Client client);
    void deleteClient(Long id);
    List<Client> getClients();
}
