package globalsolution.citizentech.trilhamagica.dto;

import globalsolution.citizentech.trilhamagica.enuns.TurmaEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProgressoRecebeDto {

    @NotNull(message = "O ID do aluno é obrigatório")
    private Long alunoId;

    @NotNull(message = "A turma é obrigatória")
    private TurmaEnum turma;

    private String progressoAluno;
}