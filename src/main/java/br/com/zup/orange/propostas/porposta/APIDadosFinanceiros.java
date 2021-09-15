package br.com.zup.orange.propostas.porposta;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "consultaCliente", url = "http://localhost:9999/api")
public interface APIDadosFinanceiros {

    @PostMapping(value = "/solicitacao")
    ResponseEntity<DTOConsultaCliente> consultaRestricao(FormDadosFinanceiros formDadosFinanceiros);

}
