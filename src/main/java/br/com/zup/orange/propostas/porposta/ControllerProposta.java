package br.com.zup.orange.propostas.porposta;

import br.com.zup.orange.propostas.shared.config.validation.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(value = "/propostas")
public class ControllerProposta {

    private RepositoryProposta repositoryProposta;


    public ControllerProposta(RepositoryProposta repositoryProposta) {
        this.repositoryProposta = repositoryProposta;
    }


    @PostMapping
    public ResponseEntity<?> createPropostas(@RequestBody @Valid FormProposta formProposta){

        Proposta proposta = formProposta.toModel();

        Optional<Proposta> possivelProposta = repositoryProposta.findByDocumento(formProposta.getDocumento());
        if(possivelProposta.isPresent()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ValidationError("documento", "Já existe uma porposta para o documento informado."));
        }

        repositoryProposta.save(proposta);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
