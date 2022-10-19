package io.github.cursodsouza.msclientes.application;

import io.github.cursodsouza.msclientes.application.representation.ClienteSaveRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("clientes")
@RequiredArgsConstructor
@Slf4j
public class ClientesResource {

    private final ClienteService service;

    @GetMapping
    public String status() {
        log.info("Obtendo o status do microservice de clientes");
        return "ok";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ClienteSaveRequest> save(@RequestBody ClienteSaveRequest request) {
        // Converte entidade na classe dto
        final var cliente = request.toModel();
        // chama o método save na camada service
        service.save(cliente);
        // monta a url
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest().query("cpf={cpf}").buildAndExpand(cliente.getCpf())
                .toUri();
        // Retorna o status 200 ok para cliente cadastrado no banco
        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping(params = "cpf")
    public ResponseEntity dadosCliente(@RequestParam("cpf") String cpf) {
        var cliente = this.service.getByCPF(cpf);
        if (cliente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }
}
