package io.github.cursodsouza.mscartoes.application;

import io.github.cursodsouza.mscartoes.application.representation.CartaoSaveRequest;
import io.github.cursodsouza.mscartoes.domain.Cartao;
import io.github.cursodsouza.mscartoes.application.representation.CartoesPorClienteResponse;
import io.github.cursodsouza.mscartoes.domain.ClienteCartao;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cartoes")
@RequiredArgsConstructor
public class CartoesResource {

    private final CartaoService cartaoService;
    private final ClienteCartaoService clienteCartaoService;


    @GetMapping
    public String status(){
        return "ok";
    }

    @PostMapping
    public ResponseEntity<CartaoSaveRequest> cadastra(@RequestBody CartaoSaveRequest request) {
        // Converte a entidade cartão na classe dto
        final var cartao = request.toModel();
        // chama o método save na camada service
        cartaoService.save(cartao);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "renda")
    public ResponseEntity<List<Cartao>> getCartoesRendaAteh(@RequestParam("renda") Long renda) {
        final List<Cartao> list = cartaoService.getCartoesRendaMenorIgual(renda);
        return ResponseEntity.ok(list);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CartoesPorClienteResponse>> getCartoesByCliente(@RequestParam("cpf") String cpf) {

        List<ClienteCartao> lista = clienteCartaoService.listarCartoes(cpf);

        List<CartoesPorClienteResponse> resultList = lista
                .stream()
                .map(CartoesPorClienteResponse::fromModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(resultList);
    }
}
