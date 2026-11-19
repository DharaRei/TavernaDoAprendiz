package globalsolution.citizentech.trilhamagica.dto;

import globalsolution.citizentech.trilhamagica.model.MatemagicamenteModel;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MatemagicamenteRespondeDto {

    private Long id;
    private String desafio;
    private int pontosObtidos;
    private LocalDateTime dataRealizacao;
    private Long atividadeId;
    private String tituloAtividade;
    private Long professorId;

    public MatemagicamenteRespondeDto(MatemagicamenteModel model) {
        this.id = model.getId();
        this.desafio = model.getDesafio();
        this.pontosObtidos = model.getPontosObtidos();
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