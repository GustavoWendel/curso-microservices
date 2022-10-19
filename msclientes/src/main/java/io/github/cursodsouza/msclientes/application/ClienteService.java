package io.github.cursodsouza.msclientes.application;

import io.github.cursodsouza.msclientes.domain.Cliente;
import io.github.cursodsouza.msclientes.infra.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Cliente save(Cliente cliente) {
        return repository.save(cliente);
    }

    public Optional<Cliente> getByCPF(String cpf) {
        return this.repository.findByCpf(cpf);
    }
}
