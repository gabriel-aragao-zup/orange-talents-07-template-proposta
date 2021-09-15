package br.com.zup.orange.propostas.porposta;

public class DTOConsultaCliente {
    private String documento;
    private String nome;
    private String resultadoSolicitacao;
    private String idProposta;

    public DTOConsultaCliente() {
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }

    public String getIdProposta() {
        return idProposta;
    }

    public StatusProposta getStatusPropostas(){
        if(resultadoSolicitacao.equals("COM_RESTRICAO")){
            return StatusProposta.NAO_ELEGIVEL;
        } else if(resultadoSolicitacao.equals("SEM_RESTRICAO")){
            return StatusProposta.ELEGIVEL;
        } else {
            return StatusProposta.FALHA_NA_VERIFICACAO;
        }
    }
}
