package globalsolution.citizentech.trilhamagica.dto;

import globalsolution.citizentech.trilhamagica.enuns.MateriaEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AtividadeRecebeDto {

    @NotBlank(message = "O título é obrigatório")
    private String titulo;

    @NotBlank(message = "A descrição original é obrigatória")
    private String descricaoOriginal;

    private String conteudoAdaptado;

    @NotNull(message = "A matéria é obrigatória")
    private MateriaEnum materia;

    private boolean concluida;

    @NotNull(message = "O ID do aluno é obrigatório")
    private Long alunoId;
}