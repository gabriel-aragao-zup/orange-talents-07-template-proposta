package br.com.zup.orange.propostas.porposta;

import br.com.zup.orange.propostas.shared.config.validation.ValidationError;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(value = "/propostas")
public class ControllerProposta {

    @Autowired
    private APIDadosFinanceiros apiDadosFinanceiros;

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

        FormDadosFinanceiros formDadosFinanceiros = FormDadosFinanceiros.fromModel(proposta);
        try{
            ResponseEntity<DTOConsultaCliente> responseConsulta = apiDadosFinanceiros
                                                                    .consultaRestricao(formDadosFinanceiros);
            DTOConsultaCliente dtoConsultaCliente = responseConsulta.getBody();
            proposta.setStatus(dtoConsultaCliente.getStatusPropostas());

        } catch (FeignException.UnprocessableEntity e){
            proposta.setStatus(StatusProposta.NAO_ELEGIVEL);
        } catch (Exception e){
            proposta.setStatus(StatusProposta.FALHA_NA_VERIFICACAO);
        }

        repositoryProposta.save(proposta);


        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
