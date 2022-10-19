package io.github.cursodsouza.mscartoes.application;

import io.github.cursodsouza.mscartoes.domain.ClienteCartao;
import io.github.cursodsouza.mscartoes.infra.repository.ClienteCartaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteCartaoService {

    private final ClienteCartaoRepository repository;

    public List<ClienteCartao> listarCartoes(String cpf) {
        return repository.findByCpf(cpf);
    }
}
