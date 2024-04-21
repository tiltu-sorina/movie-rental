package ro.ubb.fantastic3.client.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ro.ubb.fantastic3.common.model.Client;
import ro.ubb.fantastic3.common.service.ClientService;

import java.util.List;
import java.util.Set;

@Service
@Primary
@NoArgsConstructor
public class ClientServiceClient implements ClientService {
    @Autowired
    private ClientService clientService;

    @Override
    public Client addClient(Client client) {
        return clientService.addClient(client);
    }

    @Override
    public Client getClient(Long id) {
        return this.clientService.getClient(id);
    }

    @Override
    public Client updateClient(Client client) {
        return this.clientService.updateClient(client);
    }

    @Override
    public void deleteClient(Long id) {
        this.clientService.deleteClient(id);
    }

    @Override
    public List<Client> getClients() {

        return this.clientService.getClients();
    }
}
