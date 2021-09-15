package br.com.zup.orange.propostas.porposta;

import br.com.zup.orange.propostas.shared.config.validation.beanvalidation.cpforcnpj.CPFOrCNPJ;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class FormProposta {
    @NotBlank
    @CPFOrCNPJ
    private String documento;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String nome;
    @NotBlank
    private String endereco;
    @NotNull
    @Positive
    private BigDecimal salario;

    public FormProposta(String documento, String email, String nome, String endereco, BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Proposta toModel(){
        return new Proposta(documento, email, nome, endereco, salario, StatusProposta.NAO_VERIFICADA);
    }

    public String getDocumento() {
        return documento;
    }
}
