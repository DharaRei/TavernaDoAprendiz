package globalsolution.citizentech.trilhamagica.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MatemagicamenteRecebeDto {

    @NotBlank(message = "O desafio é obrigatório")
    private String desafio;

    @NotNull(message = "Os pontos obtidos são obrigatórios")
    private Integer pontosObtidos;

    @NotNull(message = "O ID da atividade é obrigatório")
    private Long atividadeId;

    private Long professorId; // Opcional
}