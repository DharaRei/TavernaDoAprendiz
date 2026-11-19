package globalsolution.citizentech.trilhamagica.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FisicaLegalRecebeDto {

    @NotBlank(message = "O nome do circuito é obrigatório")
    private String nomeCircuito;

    private boolean sucesso;

    @NotNull(message = "O ID da atividade é obrigatório")
    private Long atividadeId;

    private Long professorId; // Opcional, caso queira vincular o professor
}

