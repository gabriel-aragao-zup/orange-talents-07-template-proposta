package br.com.zup.orange.propostas.porposta;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/propostas")
public class ControllerProposta {

    private RepositoryProposta repositoryProposta;

    public ControllerProposta(RepositoryProposta repositoryProposta) {
        this.repositoryProposta = repositoryProposta;
    }

    @PostMapping
    public ResponseEntity<?> createPropostas(@RequestBody @Valid FormPropostas formPropostas){

        Proposta proposta = formPropostas.toModel();
        repositoryProposta.save(proposta);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
