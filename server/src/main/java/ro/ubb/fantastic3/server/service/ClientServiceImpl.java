package ro.ubb.fantastic3.server.service;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.fantastic3.common.model.Client;
import ro.ubb.fantastic3.common.model.validators.GenericValidator;
import ro.ubb.fantastic3.common.model.validators.exceptions.ClientNotFoundException;
import ro.ubb.fantastic3.common.model.validators.exceptions.ValidatorException;
import ro.ubb.fantastic3.common.service.ClientService;
import ro.ubb.fantastic3.server.repository.ClientRepository;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private static final Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private GenericValidator<Client> genericValidator;

    {

    }

    @Override
    public Client addClient(Client client) {
        log.trace("addClient: client={}", client);
        try {
            genericValidator.validate(client);
        } catch (ConstraintViolationException e) {
            log.warn("addClient: validation failure={}", Arrays.toString(e.getStackTrace()));
            throw new ValidatorException(e.getMessage());
        }
        Client result = this.clientRepository.save(client);
        log.trace("addClient: result={}", result);
        return result;
    }

    @Override
    public Client getClient(Long id) {
        log.trace("getClient: id={}", id);
        try {
            Client client = this.clientRepository.findById(id).orElseThrow();
            log.trace("getClient: result={}", client);
            return client;
        } catch (NoSuchElementException e) {
            log.warn("getClient: client with id={} not found", id);
            throw new ClientNotFoundException("Client with id " + id + " not found", e);
        }
    }

    @Transactional
    @Override
    public Client updateClient(Client client) {

        log.trace("updateClient: client={}", client);
        try {
            genericValidator.validate(client);
        } catch (ConstraintViolationException e) {
            log.warn("updateClient: validation failure={}", Arrays.toString(e.getStackTrace()));
            throw new ValidatorException(e.getMessage());
        }

        try {
            Client updateClient = this.clientRepository.findById(client.getId()).orElseThrow();
            updateClient.setFirstName(client.getFirstName());
            updateClient.setLastName(client.getLastName());
            updateClient.setEmail(client.getEmail());
            updateClient.setPhoneNumber(client.getPhoneNumber());
            log.trace("updateClient: result={}", updateClient);
            return updateClient;
        } catch (NoSuchElementException e) {
            log.warn("updateClient: client with id={} not found", client.getId());
            throw new ClientNotFoundException("Client with id " + client.getId() + " not found", e);
        }
    }


    @Override
    public void deleteClient(Long id) {
        log.trace("deleteClient: id={}", id);
        try {
            Client deleteClient = this.clientRepository.findById(id).orElseThrow();
            this.clientRepository.deleteById(id);
            log.trace("deleteClient: result={}", deleteClient);
        } catch (NoSuchElementException e) {
            log.warn("deleteClient: client with id={} not found", id);
            throw new ClientNotFoundException("Client with id " + id + " not found", e);
        }
    }

    @Override
    public List<Client> getClients() {
        log.trace("getAllClients --- method entered");

        List<Client> result = clientRepository.findAll();

        log.trace("getAllClients result={}", result);

        return result;
    }
}
