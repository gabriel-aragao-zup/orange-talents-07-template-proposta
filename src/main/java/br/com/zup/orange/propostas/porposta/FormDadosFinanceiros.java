package br.com.zup.orange.propostas.porposta;

import com.fasterxml.jackson.databind.annotation.JsonAppend;

public class FormDadosFinanceiros {
    private String documento;
    private String nome;
    private String idProposta;

    @Deprecated
    public FormDadosFinanceiros() {
    }



    private FormDadosFinanceiros(String documento, String nome, Long idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta.toString();
    }

    public static FormDadosFinanceiros fromModel(Proposta proposta){
        return new FormDadosFinanceiros(proposta.getDocumento(), proposta.getNome(), proposta.getId());
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
