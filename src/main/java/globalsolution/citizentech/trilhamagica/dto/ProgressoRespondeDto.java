package globalsolution.citizentech.trilhamagica.dto;

import globalsolution.citizentech.trilhamagica.enuns.TurmaEnum;
import globalsolution.citizentech.trilhamagica.model.ProgressoModel;
import lombok.Data;

@Data
public class ProgressoRespondeDto {

    private Long id;
    private Long alunoId;
    private String nomeAluno;
    private TurmaEnum turma;
    private String progressoAluno;

    public ProgressoRespondeDto(ProgressoModel model) {
        this.id = model.getId();
        this.turma = model.getTurma();
        this.progressoAluno = model.getProgressoAluno();

        if (model.getProgressoAlunoId() != null) {
            this.alunoId = model.getProgressoAlunoId().getAlunoId();
            this.nomeAluno = model.getProgressoAlunoId().getNome();
        }
    }
}
