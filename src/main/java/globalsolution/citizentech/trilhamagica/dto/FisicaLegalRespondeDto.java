package globalsolution.citizentech.trilhamagica.dto;

import globalsolution.citizentech.trilhamagica.model.FisicaLegalModel;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FisicaLegalRespondeDto {

    private Long id;
    private String nomeCircuito;
    private boolean sucesso;
    private LocalDateTime dataRealizacao;
    private Long atividadeId;
    private String tituloAtividade;
    private Long professorId;

    public FisicaLegalRespondeDto(FisicaLegalModel model) {
        this.id = model.getId();
        this.nomeCircuito = model.getNomeCircuito();
        this.sucesso = model.isSucesso();
        this.dataRealizacao = model.getDataRealizacao();

        if (model.getAtividade() != null) {
            this.atividadeId = model.getAtividade().getAtividadeId();
            this.tituloAtividade = model.getAtividade().getTitulo();
        }

        if (model.getProfessorCadastrou() != null) {
            this.professorId = model.getProfessorCadastrou().getProfessorId();
        }
    }
}